package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber", glue = "StepDefination", monochrome = true, plugin = {
		"html:target/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
