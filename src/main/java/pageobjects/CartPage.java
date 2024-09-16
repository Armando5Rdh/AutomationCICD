package pageobjects;

import SeleniumCourse.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends AbstractComponents {

    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//div[@class='cartSection']/h3")
    List<WebElement> cartProducts;

    @FindBy(xpath="//li[@class='totalRow']/button")
    WebElement checkoutButton;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement searchCountry;

    @FindBy(xpath = "//span[@class='ng-star-inserted']")
    List<WebElement> countryList;

    @FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
    WebElement placeOrder;

    @FindBy(xpath = "//h1[@class='hero-primary']")
    WebElement confirmMessage;

    public Boolean verifyProductsInCar(String desiredProduct) {
        Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equals(desiredProduct));
        return match;
    }

    public void clickCheckout() {
        checkoutButton.click();
    }

    public void selectCountryOfResidence(String country) {
        SendKeysWithActions(searchCountry, country);
        countryList.stream().filter(opt -> opt.getText().equalsIgnoreCase(country)).
                collect(Collectors.toList()).get(0).click();

    }

    public void confirmCart() {
        placeOrder.click();
    }

    public Boolean verifyConfirmationMessage(String message) {
        return message.equalsIgnoreCase(confirmMessage.getText());
    }

}
