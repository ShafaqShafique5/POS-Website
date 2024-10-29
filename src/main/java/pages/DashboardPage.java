package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    // Locators
    By dashboardLocator = By.id("dashboard");
    By salesTrendLocator = By.xpath("//span[text()='Total Sales & Orders(Last Month)']");
    By orderTrendLocator = By.xpath("//div[text()='Orders Trend']");
    By branchLocator = By.id("sale_branch");
    By dayLocator = By.id("sale_day");
    By monthLocator = By.id("sale_month");
    By yearLocator = By.id("sale_year");
    By salesGraphLocator = By.id("canvas1");
    By orderGraphLocator = By.id("canvas2");


    public void clickDashboard() {
        // Now wait for the Create New Order button to be clickable
        WebElement dashboardButton = wait.until(ExpectedConditions.elementToBeClickable(dashboardLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dashboardButton);
    }
    public void salesTrend() {
        // Now wait for the Create New Order button to be clickable
        WebElement salesTrend = wait.until(ExpectedConditions.visibilityOfElementLocated(salesTrendLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", salesTrend);

    }
    public void orderTrend() {
        // Now wait for the Create New Order button to be clickable
        WebElement orderTrend = wait.until(ExpectedConditions.visibilityOfElementLocated(orderTrendLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderTrend);

    }
    public void selectAllBranches(String dashboardAllBranches) {
        WebElement selectAllBranches = wait.until(ExpectedConditions.elementToBeClickable(branchLocator));
        Select select = new Select(selectAllBranches);
        // Select the desired branch by visible text
        select.selectByVisibleText(dashboardAllBranches);
    }
    public void selectBranch(String dashboardBranch) {
        WebElement selectBranch = wait.until(ExpectedConditions.elementToBeClickable(branchLocator));
        Select select = new Select(selectBranch);
        // Select the desired branch by visible text
        select.selectByVisibleText(dashboardBranch);
    }
    public void selectDay(String dashboardDay) {
        WebElement selectDay = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        Select select = new Select(selectDay);
        // Select the desired branch by visible text
        select.selectByVisibleText(dashboardDay);
    }
    public void selectMonth(String dashboardMonth) {
        WebElement selectMonth = wait.until(ExpectedConditions.elementToBeClickable(monthLocator));
        Select select = new Select(selectMonth);
        // Select the desired branch by visible text
        select.selectByVisibleText(dashboardMonth);
    }
    public void selectYear(String dashboardYear) {
        WebElement selectYear = wait.until(ExpectedConditions.elementToBeClickable(yearLocator));
        Select select = new Select(selectYear);
        // Select the desired branch by visible text
        select.selectByVisibleText(dashboardYear);
    }
    public WebElement getSalesGraph() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(salesGraphLocator));
    }
    public WebElement getOrderTrend() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderGraphLocator));
    }

}
