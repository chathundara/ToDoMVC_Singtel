package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
	(
			features="./Features/ToDoMVC.feature",
			glue={"stepDefinitions"},
					//tags="@verify",
			dryRun=false,
			monochrome=true, 
					plugin = {"pretty", "html:report/cucumber.html"}
	
			)

public class TestRunner {

	
}
