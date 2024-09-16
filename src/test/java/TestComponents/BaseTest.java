package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties properties = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalData.properties");
        properties.load(file);
        String browserName = System.getProperty("browser") != null ?
                System.getProperty("browser") : properties.getProperty("browser");
        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();

            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));

        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();

        }
        else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }

        public List<HashMap<String, String>> getJsonDataToMap() throws IOException, ParseException {
            File file = new File("src/test/java//Data/PurchaseOrder.json");
//        Map<String, Object> employee = objectMapper.readValue(file, new TypeReference<>(){});

            Object o = new JSONParser().parse(new FileReader(file));

            List<HashMap<String, String>> data = (List<HashMap<String, String>>) o;


//        https://reflectoring.io/jackson/
//        FileUtils.readFileToString(new File("PurchaseOrder.json"));
            return data;
        }


    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() throws IOException {

        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }

    @AfterMethod(alwaysRun = true)
    public void tierDown() {
        driver.close();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }

    public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
        TakesScreenshot sc = (TakesScreenshot) driver;
        File source = sc.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//Reports//" + testcaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//Reports//" + testcaseName + ".png";
    }
}
