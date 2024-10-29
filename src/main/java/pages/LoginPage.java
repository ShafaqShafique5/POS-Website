package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
    By emailLocator = By.id("email");
    By passwordLocator = By.name("password");
    By loginButtonLocator = By.cssSelector("form button");

    public void setEmail(String userEmail) {
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(emailLocator));
        emailInput.click();
        emailInput.sendKeys(userEmail);
    }

//    public String getEmail() {
//        return driver.findElement(emailLocator).getAttribute("value");
//    }

    public void setPassword(String userPassword) {
        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(passwordLocator));
        passwordInput.click();
        passwordInput.sendKeys(userPassword);
    }
    public void setIncorrectPassword(String incorrectPassword) {
        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(passwordLocator));
        passwordInput.click();
        passwordInput.sendKeys(incorrectPassword);
    }

    public void clickLogin() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        loginButton.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
