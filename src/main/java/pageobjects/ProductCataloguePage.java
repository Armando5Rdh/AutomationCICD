package pageobjects;

import SeleniumCourse.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePage extends AbstractComponents {

    WebDriver driver;
    public ProductCataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    @FindBy(xpath="//div[contains(@class,'col-sm-10')]")
    List<WebElement> products;

    @FindBy(className = "ng-animating")
    WebElement loading;

    By productsBy = By.xpath("//div[contains(@class,'col-sm-10')]");
    By addToCart = By.xpath("//button[@class='btn w-10 rounded']");
    By addedConfirmMessage = By.id("toast-container");

    public List<WebElement> getProductsList() {
        WaitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String desiredProduct) {
        WebElement prod = getProductsList().stream().filter(product ->
                product.findElement(By.tagName("b")).getText().equals(desiredProduct)).findFirst().orElse(null);
        return prod;

    }

    public void addProductToCart(String desiredProduct) throws InterruptedException {
        WebElement prod = getProductByName(desiredProduct);
        prod.findElement(addToCart).click();
        WaitForElementToDisappear(loading);
//        WaitForElementToAppear(addedConfirmMessage);
    }
}
