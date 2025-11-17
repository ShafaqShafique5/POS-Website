package stepsDefinition;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PlayVideoPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class PlayVideoSteps {

    WebDriver driver;
    WebDriverWait wait;
    PlayVideoPage playVideoPage;
    String lastSearchedShow; // Store the show name dynamically

    public PlayVideoSteps() {
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.playVideoPage = new PlayVideoPage(driver);
    }

    @When("user searches for {string}")
    public void user_searches_for(String showName) {
        playVideoPage.clickSearchIcon();
        playVideoPage.enterSearchQuery(showName);
    }

    @When("user selects the show {string} from results")
    public void user_selects_the_show(String showName) {
        playVideoPage.clickShowThumbnail(showName);
    }

    @Then("the video should be playing")
    public void video_should_be_playing() {
        assertTrue("Video is not playing!", playVideoPage.isVideoPlaying());
    }

}
