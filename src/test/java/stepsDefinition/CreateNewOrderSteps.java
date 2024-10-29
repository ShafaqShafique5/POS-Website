package stepsDefinition;

import hooks.Hooks;
import base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CreateNewOrderPage;
import static org.junit.Assert.assertEquals;

public class CreateNewOrderSteps extends Base{
    CreateNewOrderPage createNewOrder;

    public CreateNewOrderSteps() {
        this.driver = Hooks.driver; // Access the WebDriver instance from Hooks
        this.config = Hooks.config; // Access the ConfigReader instance from Hooks
        // Initialize CreateNewOrderPage after the driver is initialized
        createNewOrder = new CreateNewOrderPage(driver);
    }

    @When("User clicks on create new order option")
    public void user_clicks_on_create_new_order_option() {
        createNewOrder.clickCreateNewOrder();
    }

    @And("User selects target branch")
    public void user_selects_target_branch()  {
        String branchName = config.getProperty("branchName");
        createNewOrder.selectDropdown(branchName);
    }

    @And("User clicks on next")
    public void user_clicks_on_next() {
        createNewOrder.clickNextButton();
    }

    @Then("User should be navigated to targeted branch")
    public void user_should_be_navigated_to_targeted_branch() {
        String expectedUrl = config.getProperty("branchUrl");
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }
}

