package cucumberOption;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Feature/Input.feature",glue={"stepDefination"}
,tags = "@AddPlaceAPI", plugin = "json:target/jsonReports/cucumber-report.json")

public class TestRunner {
	
}
