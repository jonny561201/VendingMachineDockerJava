package acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/acceptance",
        glue = {"acceptance/steps", "com.foreach.cuke"},
        snippets = SnippetType.CAMELCASE
)
public class RunCukesIT {
}
