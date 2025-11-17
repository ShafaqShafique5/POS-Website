package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PlayVideoPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    // Constructor
    public PlayVideoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.js = (JavascriptExecutor) driver;
    }

    // Locators
    private final By searchIcon = By.xpath("//img[contains(@src,'search.svg')]");
    private final By searchInput = By.xpath("//input[@placeholder='Search...']");

    // dynamic thumbnail locator
    private By showThumbnail(String showName) {
        return By.xpath("//div[contains(@class,'img-box')]//img[@alt='" + showName + "']");

    }

    // video player element
    private final By videoElement = By.tagName("video");

    // 🔹 Click Search icon
    public void clickSearchIcon() {
        WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
        try {
            icon.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", icon);
        }
    }

    // 🔹 Enter search text and press Enter
    public void enterSearchQuery(String query) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        input.clear();
        input.sendKeys(query);
        input.sendKeys(Keys.ENTER);
    }

    // 🔹 Click directly on the show thumbnail instead of hover
    public void clickShowThumbnail(String showName) {
        By thumbLocator = showThumbnail(showName);
        WebElement showThumb = wait.until(ExpectedConditions.visibilityOfElementLocated(thumbLocator));

        // Scroll into view
        js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", showThumb);
        js.executeScript("window.scrollBy(0, -100);"); // adjust position slightly

        // Click the thumbnail safely
        try {
            wait.until(ExpectedConditions.elementToBeClickable(showThumb)).click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", showThumb);
        }
    }

    public boolean isVideoPlaying() {
        try {
            // Wait up to 10 seconds for the video to start playing
            WebDriverWait videoWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Boolean playing = videoWait.until(driver -> {
                WebElement video = driver.findElement(videoElement);
                if (!video.isDisplayed()) return false;
                Object result = js.executeScript(
                        "return arguments[0] && !arguments[0].paused && !arguments[0].ended && arguments[0].readyState >= 3;",
                        video
                );
                return Boolean.TRUE.equals(result);
            });
            return playing;
        } catch (Exception e) {
            System.out.println("Video playback verification failed: " + e.getMessage());
            return false;
        }
    }


}
