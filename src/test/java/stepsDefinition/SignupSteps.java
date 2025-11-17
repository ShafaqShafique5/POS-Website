package stepsDefinition;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.SignupPage;
import utils.ConfigReader;

/**
 * Uses Page Object Model (SignupPage) and ConfigReader for dynamic configuration.
 */
public class SignupSteps {

    // Page object for Signup actions
    private final SignupPage signupPage = new SignupPage(Hooks.getDriver());

    // ConfigReader instance to fetch values from config.properties
    private final ConfigReader config = new ConfigReader();

    // Config values
    private final String url = config.getProperty("url");                    // Homepage URL
    private final String signupPassword = config.getProperty("signupPassword");  // Password for signup
    private final String expectedUrl = config.getProperty("signupSuccessUrl");    // Expected URL after successful signup

    /**
     * Navigate to ZAP OTT homepage.
     */
    @Given("user is on the ZAP OTT homepage")
    public void open_homepage() {
        Hooks.getDriver().get(url);  // Open the homepage URL from config
    }

    /**
     * Perform signup with valid credentials.
     */
    @When("user signs up with valid credentials")
    public void signup_user() {
        // Create a dynamic email to avoid duplicate signup issues
        String dynamicEmail = "test" + System.currentTimeMillis() + "@gmail.com";

        signupPage.clickSignInButton();       // Click 'Login' button
        signupPage.clickCreateAccountLink();  // Click 'Create an Account'
        signupPage.setEmail(dynamicEmail);    // Enter email
        System.out.println("Using dynamic email: " + dynamicEmail);
        signupPage.clickGetStartedButton();   // Click 'Next'
        signupPage.setPassword(signupPassword);  // Enter password from config
        signupPage.reEnterPassword(signupPassword);  // Enter password from config
        signupPage.clickCheckbox();           // Accept terms & conditions
        signupPage.clickNextButton();           // Click 'Next' button
        signupPage.clickGender();         // Click 'Gender' button
        signupPage.clickAge();         // Click 'Age' button
        signupPage.clickNextButton();           // Click 'Next' button
        signupPage.clickSkipAndStartWatching();           // Click 'Skip and Start Watching' button
    }

    /**
     * Verify that user is redirected to homepage after successful signup.
     */
    @Then("signup should be successful")
    public void verify_signup_redirect() {
        // Get the current URL from browser
        String actualUrl = signupPage.getCurrentUrl();

        // Assert that the current URL exactly matches the expected URL
        Assert.assertEquals(actualUrl, expectedUrl, "Signup redirect URL mismatch!");

        // Print confirmation
        System.out.println("Signup successful. Redirected to: " + actualUrl);
    }
}
