package runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import pages.BasePage;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "steps",
        //tags = "@RecordAction", //especificar que test o feature a ejecutar, Ej: @Regression, @Smoke, @Hotfix
        //gradle test -Dcucumber.options="--tags @T, --tags @Smoke"
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-reports.json"}
)

public class Runner{

   /* @AfterClass
    public static void cleanDriver(){
        BasePage.closeDriver();
    }*/
}