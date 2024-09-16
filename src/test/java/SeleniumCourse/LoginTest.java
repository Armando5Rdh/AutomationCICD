package SeleniumCourse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.LandingPage;
import pageobjects.ProductCataloguePage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class LoginTest {

    @Test
    public void main(String[] args) throws InterruptedException {

        String username = "armando@gmail.com";
        String password = "Password!1";
        String desiredProduct = "ZARA COAT 3";
        String confirmationMessage = "Thankyou for the order.";
        String country = "Mexico";

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LandingPage landingPage = new LandingPage(driver);
        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        CartPage cartPage = new CartPage(driver);

        landingPage.goTo();
        landingPage.loginApp(username, password);
        productCataloguePage.addProductToCart(desiredProduct);
        productCataloguePage.clickCartButton();

        Assert.assertTrue(cartPage.verifyProductsInCar(desiredProduct));
        cartPage.clickCheckout();
        cartPage.selectCountryOfResidence(country);
        cartPage.confirmCart();
        Assert.assertTrue(cartPage.verifyConfirmationMessage(confirmationMessage));

    }
}
