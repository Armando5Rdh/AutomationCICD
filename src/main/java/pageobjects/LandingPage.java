package pageobjects;

import SeleniumCourse.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordElement;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public void loginApp(String email, String password) {
        userEmail.sendKeys(email);
        passwordElement.sendKeys(password);
        submit.click();

    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage() {
        WaitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
