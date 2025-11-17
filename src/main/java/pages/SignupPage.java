package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    // Constructor
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    // Locators (unchanged)
    By signInButton = By.xpath("//span[normalize-space(text())='Sign in']");
    By createAnAccount = By.xpath("//p[contains(.,'New to Zap?')]//a[text()='Create an Account']");
    By emailLocator = By.xpath("//input[@id='formEmail' and @type='email' and @placeholder='Enter Email']");
    By getStartedButton = By.xpath("//button[text()='Get Started']");
    By passwordField = By.xpath("//input[@id='formEmail' and @type='password' and @placeholder='Add a password']");
    By reEnterPasswordField = By.xpath("//input[@id='formEmail' and @type='password' and @placeholder='Re-enter password']");
    By checkboxButton = By.id("termsCheckbox");
    By nextButton = By.xpath("//button[text()='Next']");
    By genderButton = By.xpath("//button[text()='Next']");
    By ageButton = By.xpath("//button[text()='24-34']");
    By skipAndStartButton = By.xpath("//a[text()='Skip and start watching']");

    // ------------------- Utility Methods -------------------

    private void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

                // Scroll into view and click using JS for reliability
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                js.executeScript("arguments[0].click();", element);
                Thread.sleep(300); // optional short pause for animations
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element detected, retrying click... Attempt " + (attempts + 1));
                attempts++;
            } catch (Exception e) {
                System.out.println("Click attempt failed: " + e.getMessage());
                attempts++;
            }
        }
        throw new RuntimeException("Failed to click element after multiple attempts: " + locator.toString());
    }

    private void safeType(By locator, String text) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement input = wait.until(ExpectedConditions.elementToBeClickable(locator));
                input.clear();
                input.sendKeys(text);
                // Trigger blur for validation
                js.executeScript("arguments[0].dispatchEvent(new Event('change'))", input);
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            } catch (Exception e) {
                System.out.println("Typing failed: " + e.getMessage());
                attempts++;
            }
        }
        throw new RuntimeException("Failed to type in element: " + locator.toString());
    }

    // ------------------- Actions -------------------

    public void clickSignInButton() {
        safeClick(signInButton);
    }

    public void clickCreateAccountLink() {
        safeClick(createAnAccount);
    }

    public void setEmail(String email) {
        safeType(emailLocator, email);
    }

    public void clickGetStartedButton() {
        safeClick(getStartedButton);
    }

    public void setPassword(String password) {
        safeType(passwordField, password);
    }

    public void reEnterPassword(String reEnter) {
        safeType(reEnterPasswordField, reEnter);
    }

    public void clickCheckbox() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkboxButton));
        if (!checkbox.isSelected()) {
            safeClick(checkboxButton);
        }
    }

    public void clickNextButton() {
        safeClick(nextButton);
    }

    public void clickGender() {
        safeClick(genderButton);
    }

    public void clickAge() {
        safeClick(ageButton);
    }

    public void clickSkipAndStartWatching() {
        safeClick(skipAndStartButton);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
