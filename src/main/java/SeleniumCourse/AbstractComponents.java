package SeleniumCourse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.OrdersPage;

import java.time.Duration;

public class AbstractComponents {

    @FindBy(xpath="//i[@class='fa fa-shopping-cart']")
    WebElement cart;

    @FindBy(xpath = "//button[contains(@routerlink,'myorders')]")
    WebElement orderHeader;

    WebDriver driver;
    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void WaitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void WaitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));

    }
    public void WaitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public void WaitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void SendKeysWithActions(WebElement element, String keys) {
        Actions a = new Actions(driver);
        a.sendKeys(element,keys).build().perform();
//
    }

    public void clickCartButton() {
        WaitForElementToBeClickable(cart);
        cart.click();
    }

    public OrdersPage GoToOrdersPage() {
        orderHeader.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }
}
