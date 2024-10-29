package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class CreateNewOrderPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public CreateNewOrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
    By createNewOrderLocator = By.id("invoice-create");
    By dropdownLocator = By.id("branch_selected");
    By nextButtonLocator = By.id("btnSave_qty");
    By shiftButtonOnOrderLocator = By.xpath("//a[text()=\"Start Shift\"]");
    By drawerLocator = By.cssSelector("div .swal2-input");
    By startButtonLocator = By.cssSelector("button.swal2-confirm");


    public void clickCreateNewOrder() {
        // Now wait for the Create New Order button to be clickable
        WebElement createNewOrderButton = wait.until(ExpectedConditions.elementToBeClickable(createNewOrderLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createNewOrderButton);
    }
    public void selectDropdown(String branchName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        Select select = new Select(dropdown);
        wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        // Select the desired branch by visible text
        select.selectByVisibleText(branchName);
    }
    public void clickNextButton() {
        WebElement nextButton = driver.findElement(nextButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
    }

    public void clickShiftButtonOnOrderScreen() {
        WebElement shiftButton = wait.until(ExpectedConditions.elementToBeClickable(shiftButtonOnOrderLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", shiftButton);
    }
    public void clickShiftButtonOnShiftScreen() {
        WebElement shiftButton = wait.until(ExpectedConditions.elementToBeClickable(shiftButtonOnOrderLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", shiftButton);
    }
    public void clickDrawerField(String shiftAmount) {
        WebElement drawer = wait.until(ExpectedConditions.elementToBeClickable(drawerLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", drawer);
        drawer.sendKeys(shiftAmount);
    }
    public void clickStartButtonOnShiftScreen() {
        WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(startButtonLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", startButton);
    }
}

