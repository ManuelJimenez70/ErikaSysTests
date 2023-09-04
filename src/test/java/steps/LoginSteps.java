package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import static io.restassured.RestAssured.*;
public class LoginSteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^El usuario administrador ya está logeado$")
    public void login() {
        System.out.println("Logeado");
    }

    @When("^busco un usuario por el id (.+)$")
    public void enterSearchCriteria(int id) {
        request = given().log().all();
        response = this.request.when().get("http://localhost:7289/api/User/getUserById/" + id);
    }

    @Then("^el resultado del nombre deberia ser (.+)$")
    public void validateResults(String resultCriteria) {
        json = response.then().statusCode(200);
        String jsonResponse = response.jsonPath().get("data.name.value");
        if(jsonResponse==null){
            jsonResponse = response.jsonPath().get("message");
            Assert.assertEquals("El usuario si existe", resultCriteria.toLowerCase(), jsonResponse.toLowerCase());
        }else{
            Assert.assertEquals("El nombre no coincide con el id del usuario", resultCriteria.toLowerCase(), jsonResponse.toLowerCase());
        }
    }
}
