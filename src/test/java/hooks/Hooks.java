package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.ConfigReader;
import java.time.Duration;

public class Hooks {

    public static WebDriver driver; // Make WebDriver static
    public static ConfigReader config; // Make ConfigReader static
    public static LoginPage loginPage;

    @Before
    public void setUp(Scenario scenario) throws Exception {
        // Initialize WebDriver for each test
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize ConfigReader (only once)
        if (config == null) {
            String environment = System.getProperty("env", "stg");
            config = new ConfigReader(environment);
        }

        // Fetch URLs and credentials from the configuration file based on the environment
        String loginUrl = config.getProperty("loginUrl"); // Get the application login URL from properties
        String expectedUrl = config.getProperty("homeUrl"); // Get the expected URL from properties
        String userEmail = config.getProperty("userEmail");
        String userPassword = config.getProperty("userPassword");

        // Initialize the LoginPage
        loginPage = new LoginPage(driver);

        // Perform login only for positive scenarios
        if (!scenario.getSourceTagNames().contains("@negative")) {
            driver.get(loginUrl); // Application's URL
            loginPage.setEmail(userEmail); // user's email
            loginPage.setPassword(userPassword); // user's password
            loginPage.clickLogin(); // login button, click it

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(expectedUrl)); // Wait until the URL matches the expected URL
        } else {
            // If it's a negative case, you may want to simply navigate to the login page
            driver.get(loginUrl);
        }
    }

    @After
    public void tearDown() {
        // Explicitly clear browser storage and cookies
        if (driver != null) {
            driver.manage().deleteAllCookies();  // Deletes cookies
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("window.localStorage.clear();");
            jsExecutor.executeScript("window.sessionStorage.clear();");

            driver.quit();  // Quit the browser
            driver = null;  // Nullify WebDriver to ensure no reuse
        }
    }
}
