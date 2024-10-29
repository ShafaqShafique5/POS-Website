package stepsDefinition;

import hooks.Hooks;
import base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import pages.LoginPage;
import static org.junit.Assert.assertEquals;

public class LoginSteps extends Base {

    public LoginSteps() {
        this.driver = Hooks.driver; // Access the WebDriver instance from Hooks
        this.config = Hooks.config; // Access the ConfigReader instance from Hooks
    }

    @Step
    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
//        String loginUrl = config.getProperty("loginUrl");
//        driver.get(loginUrl);
    }

    @Step
    @When("User enter valid credentials")
    public void userEnterValidCredentials() {
    }

    @And("User click on the login button")
    public void userClickOnTheLoginButton() {
    }

    @Then("User should be navigated to the home page")
    public void userShouldBeNavigatedToTheHomePage() {
        String expectedUrl = config.getProperty("homeUrl");
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

}
