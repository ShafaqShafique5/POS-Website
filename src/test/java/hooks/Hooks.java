package hooks;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignupPage;
import utils.ConfigReader;

public class Hooks {

    public static WebDriver driver;
    private static boolean isUserSignedUp = false;

    // --- 1. Initialize driver first ---
    @Before(order = 0)
    public void initializeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies(); // clear session
    }


    // --- 2. Mandatory signup for the first scenario only ---
    @Before(order = 1)
    public void ensureUserIsSignedUp() {
        if (!isUserSignedUp) {
            System.out.println("Running mandatory signup precondition...");

            ConfigReader config = new ConfigReader();
            SignupPage signupPage = new SignupPage(driver);

            String dynamicEmail = "auto" + System.currentTimeMillis() + "@test.com";
            String password = config.getProperty("signupPassword");

            // Navigate to URL
            driver.get(config.getProperty("url"));

            // Perform signup
            signupPage.clickSignInButton();
            signupPage.clickCreateAccountLink();
            signupPage.setEmail(dynamicEmail);
            signupPage.clickGetStartedButton();
            signupPage.setPassword(password);
            signupPage.reEnterPassword(password);
            signupPage.clickCheckbox();
            signupPage.clickNextButton();
            signupPage.clickGender();
            signupPage.clickAge();
            signupPage.clickSkipAndStartWatching();

            isUserSignedUp = true;
            System.out.println("Signup precondition completed successfully.");
        }
    }

    // --- 3. Teardown after all scenarios ---
    @AfterAll
    public static void tearDownAll() {
        if (driver != null) {
            driver.quit();
            driver = null;
            isUserSignedUp = false;
            System.out.println("Browser closed successfully at the end of all scenarios.");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
