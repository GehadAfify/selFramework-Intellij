package runner;

import io.cucumber.testng.CucumberOptions;
import tests.TestBase;

@CucumberOptions (features ={ "src/test/java/features/UserRegistration.feature","src/test/java/features/2.feature"},
        glue = {"steps"},
        plugin = {"pretty","html:target/cucumber-html-report"}
        )
public class TestRunner extends TestBase {
	
}
