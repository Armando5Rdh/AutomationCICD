package pageobjects;

import SeleniumCourse.AbstractComponents;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponents {

    WebDriver driver;
    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    @FindBy(xpath="//tr/td[2]")
    List<WebElement> productNames;

    public Boolean VerifyProductNames(String desiredProduct) {
        return productNames.stream().anyMatch(s -> s.getText().equalsIgnoreCase(desiredProduct));
    }



}
