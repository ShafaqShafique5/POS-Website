package stepsDefinition;

import base.Base;
import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.CreateNewOrderPage;
import pages.DashboardPage;

import static org.junit.Assert.assertTrue;

public class DashboardSteps extends Base {
    DashboardPage dashboardPage;

    public DashboardSteps() {
        this.driver = Hooks.driver; // Access the WebDriver instance from Hooks
        this.config = Hooks.config; // Access the ConfigReader instance from Hooks
        // Initialize CreateNewOrderPage after the driver is initialized
        dashboardPage = new DashboardPage(driver);
    }

    @When("User clicks on dashboard option")
    public void userClicksOnDashboardOption() {
        dashboardPage.clickDashboard();
    }

    @When("User selects all branches through dropdown")
    public void userSelectsAllBranchesThroughDropdown() {
        String allBranches = config.getProperty("dashboardAllBranches");
        dashboardPage.selectAllBranches(allBranches);
    }

    @And("User selects sales trend filters")
    public void userSelectsSalesTrendFilters() {
        dashboardPage.salesTrend();
        String day = config.getProperty("dashboardDay");
        dashboardPage.selectDay(day);
        String month = config.getProperty("dashboardMonth");
        dashboardPage.selectMonth(month);
    }

    @And("User selects order trend filters")
    public void userSelectsOrderTrendFilters() {
        dashboardPage.orderTrend();
        String day = config.getProperty("dashboardDay");
        dashboardPage.selectDay(day);
        String month = config.getProperty("dashboardMonth");
        dashboardPage.selectMonth(month);
        String year = config.getProperty("dashboardYear");
        dashboardPage.selectYear(year);
    }

    @Then("User will be able to view sales statistics of all branches")
    public void userWillBeAbleToViewSalesStatisticsOfAllBranches() {
        // Fetch the sales graph element
        WebElement salesGraph = dashboardPage.getSalesGraph();
        assertTrue("Sales graph should be visible.", salesGraph.isDisplayed());

        // Fetch the order trend element
        WebElement orderTrend = dashboardPage.getOrderTrend();
        assertTrue("Order trend should be visible.", orderTrend.isDisplayed());
    }

    @Then("User will be able to view sales statistics of specific branch")
    public void userWillBeAbleToViewSalesStatisticsOfSpecificBranch() {
        // Fetch the sales graph element
        WebElement salesGraph = dashboardPage.getSalesGraph();
        assertTrue("Sales graph should be visible.", salesGraph.isDisplayed());

        // Fetch the order trend element
        WebElement orderTrend = dashboardPage.getOrderTrend();
        assertTrue("Order trend should be visible.", orderTrend.isDisplayed());
    }
    @When("User selects specific branch through dropdown")
    public void userSelectsSpecificBranchThroughDropdown() {
        String branch = config.getProperty("dashboardBranch");
        dashboardPage.selectBranch(branch);
    }
}
