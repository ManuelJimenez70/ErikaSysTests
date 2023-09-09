package steps;

import com.github.javafaker.Faker;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ActionSteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    private Faker faker;
    private LoginSteps loginSteps;
    public ActionSteps(){
        this.faker = new Faker();
        this.loginSteps = new LoginSteps();
    }

    @Given("^El usuario empleado ya está logeado$")
    public void employeeLogin() {
        System.out.println("Logeado");
    }

    @When("^Se registra una accion con los atributos id_usuario: (.+), id_action: (.+), id_product: (.+), quantity: (.+), state: (.+)$")
    public void enterActionAtributes(int id_usuario, int id_action, int id_product, int quantity, String state) {
        request = given().log().all()
                .contentType("application/json")
                .body("{\n" +
                        "    \"id_user\" : \"" +id_usuario+"\",\n" +
                        "    \"id_action\" : \"" +id_action+"\",\n" +
                        "    \"id_product\" : \"" +id_product+"\",\n" +
                        "    \"quantity\" : \"" +quantity+"\",\n" +
                        "    \"state\": \""+state+"\"\n" +
                        "}");

        response = this.request.when().post(MyConstants.apiAdress + "Action/recordAction");
    }

    @Then("^el resultado de deberia ser (.+) al registrar accion$")
    public void validateRecordAction(String resultCriteria) {
        json = response.then().statusCode(200);
        String jsonResponse = response.jsonPath().get("state");
        if(jsonResponse!=null){
            Assert.assertEquals("Error al registrar accion.\n" + response.jsonPath().get(), resultCriteria.toLowerCase(), jsonResponse.toLowerCase());
        }else{
            Assert.fail("Error al consumir endpoint, respuesta vacia.");
        }
    }

}
