package SeleniumCourse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {

        String username = "armando@gmail.com";
        String password = "Password!1";
        String desiredProduct = "ZARA COAT 3";
        String confirmationMessage = "Thankyou for the order.";
        WebDriver driver = new FirefoxDriver();
        
        LandingPage landingPage = new LandingPage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys(username);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'col-sm-10')]")));
        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'col-sm-10')]"));

        WebElement prod = products.stream().filter(product ->
                product.findElement(By.tagName("b")).getText().equals(desiredProduct)).findFirst().orElse(null);

        Thread.sleep(1000);
        prod.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

        driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

        Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equals(desiredProduct));
        Assert.assertTrue(match);
        driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();


        //without Actions
        /*driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("mex");
        Thread.sleep(3000);
        */

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"Mex").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ng-star-inserted']")));

        List<WebElement> options = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));

        List<WebElement> country = options.stream().filter(opt -> opt.getText().equalsIgnoreCase("Mexico")).collect(Collectors.toList());
        country.get(0).click();
        driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

        boolean equals = confirmationMessage.equalsIgnoreCase(driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText());
        Assert.assertTrue(equals);

//        for (int i = 0; i < products.size(); i++) {
//            String productName = products.get(i).findElement(By.xpath("//h5")).getText();
//            if (productName == desiredProduct) {
//                products.get(i).findElement(By.className("btn w-10 rounded")).click();
//            }
//        }
    }
}
