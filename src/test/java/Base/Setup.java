package Base;

import Utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.Duration;

public class Setup {
    public WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void screenShot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Utils util = new Utils();
                util.takeScreenShot(driver);
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
    }

//    @AfterTest
//    public void closeDriver() {
//        driver.quit();
//    }
}
