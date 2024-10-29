package base;

import hooks.Hooks;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class Base {
    protected WebDriver driver = Hooks.driver; // Access the shared WebDriver
    protected ConfigReader config = Hooks.config; // Access the shared ConfigReader
}
