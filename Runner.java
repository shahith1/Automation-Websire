package Testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/Feature",
    glue = {"stepdefinition", "hooks"},
    plugin = {
        "pretty",
        "html:target/CucumberReports/report.html",
        "json:target/CucumberReports/report.json"
    },
    monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests {
}
