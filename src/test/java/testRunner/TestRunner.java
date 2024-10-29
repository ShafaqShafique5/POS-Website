package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = {"stepsDefinition", "base", "hooks"},  // Packages containing step definitions, base, and hooks
        plugin = {
                "pretty", // Format the output
                "html:target/cucumber-reports.html", // Generate HTML report
//                "json:target/cucumber-reports.json", // Generate JSON report
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"


        },

        monochrome = true, // Set to true for more readable output
        dryRun = false // Set to true to check for undefined steps without running tests
)
public class TestRunner {

}
