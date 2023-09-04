package steps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.*;

import pages.GooglePage;

public class GoogleSteps {

    GooglePage google = new GooglePage();

    @Given("^I am on the Google search page$")
    public void navigateToGoogle() {
        google.navigateToGoogle();

    }

    @When("^I search (.+)$")
    public void enterSearchCriteria(String searchCriteria) {
        google.writeSearchCriteria(searchCriteria);
    }

    @And("^click on the seach button$")
    public void clickSearchButton() {
        google.clickSearchButton();
    }

    @Then("^the results match the criteria (.+)$")
    public void validateResults(String resultCriteria) {
        List<WebElement> elements = google.getSubElemetsByClassName(
                "//body[1]/div[7]/div[1]/div[13]/div[1]/div[2]/div[2]/div[1]/div[1]", "MjjYud");
        int counter = 0;
        for (WebElement webElement : elements) {
            WebElement divElement = webElement.findElement(By.tagName("div")).findElement(By.tagName("div"))
                    .findElement(By.tagName("div")).findElement(By.tagName("div"));
            if (divElement.getAttribute("class").equals("yuRUbf")) {
                String h3Text = divElement.findElement(By.tagName("a")).findElement(By.tagName("h3")).getText();
                System.out.println(h3Text);
                try {
                    Assert.assertTrue("Las busquedas no contienen la palabra criterio de busqueda",
                            (h3Text.toLowerCase().contains(resultCriteria.toLowerCase())));
                } catch (AssertionError e) {
                    counter++;
                    if(counter>5){
                        Assert.fail();
                    }
                    System.out.println(
                            "\nEl resultado: '" + (h3Text.length() > 15 ? h3Text.substring(0, 14) + "..." : h3Text)
                                    + "' no contiene la palabra criterio de busqueda");
                    System.out.println(e.getMessage() + "\n");
                    ;
                }
            }
        }
    }
}
