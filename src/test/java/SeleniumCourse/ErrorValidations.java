package SeleniumCourse;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.ProductCataloguePage;

import java.io.IOException;

public class ErrorValidations extends BaseTest{

    @Test (groups = {"errorHandling"})
    public void loginErrorValidation() throws InterruptedException, IOException {

        String username = "armando@gmail.com";
        String password = "Password!1";

        landingPage.goTo();
        landingPage.loginApp(username, password+"e");
        Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
    }

    @Test
    public void productErrorValidation() throws InterruptedException {

        String desiredProduct = "ZARA COAT 3";
        String username = "armando2@gmail.com";
        String password = "Password!1";

        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);

        landingPage.goTo();
        landingPage.loginApp(username, password);
        productCataloguePage.addProductToCart(desiredProduct);
        productCataloguePage.clickCartButton();

        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.verifyProductsInCar(desiredProduct+"fe"));
    }
}
