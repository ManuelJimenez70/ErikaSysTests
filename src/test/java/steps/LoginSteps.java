package steps;

import com.github.javafaker.Faker;
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

    private Faker faker;

    public LoginSteps(){
        this.faker = new Faker();
    }

    @Given("^El usuario administrador ya está logeado$")
    public void login() {
        System.out.println("Logeado");
    }




    @When("^busco un usuario por el id (.+)$")
    public void enterSearchCriteria(int id) {
        request = given().log().all();
        response = this.request.when().get(MyConstants.apiAdress + "User/getUserById/" + id);
    }

    @Then("^el resultado del nombre deberia ser (.+)$")
    public void validateResults(String resultCriteria) {
        json = response.then().statusCode(200);
        String jsonResponse = "";
        try{
            jsonResponse = response.jsonPath().get("data.name.value");
            Assert.assertEquals("El nombre no coincide con el id del usuario", resultCriteria.toLowerCase(), jsonResponse.toLowerCase());
        }catch (Exception e){
            jsonResponse = response.jsonPath().get("message");
            Assert.assertEquals("El usuario si existe", resultCriteria.toLowerCase(), jsonResponse.toLowerCase());
        }
    }

    @When("^Creo un usuario con los atributos: (.+),  (.+),  (.+),  (.+), (.+),  (.+), (.+), (.+), (.+)$")
    //<email>,  <name>,  <lastName>,  <typeDocument>,  <document_number>,  <direction>, <password>, <roles>
    public void enterUserAtributes(String email, String name, String lastName, String typeDocument, String document_number, String direction, String password, int roles, String state) {
       // request = given().log().all().param(email, name, lastName, typeDocument, document_number, direction, password, new int[roles], state);
        //response = this.request.when().post("http://localhost:7289/api/User/createUser");

        email = (email==null||email.isEmpty()||email.equals("<random_email>"))?faker.internet().emailAddress():email;
        request = given().log().all()
                .contentType("application/json")
                .body("{\n" +
                        "    \"email\" : \"" +email+"\",\n" +
                        "    \"name\": \""+name+"\",\n" +
                        "    \"lastName\" : \""+lastName+"\",\n" +
                        "    \"typeDocument\": \""+typeDocument+"\",\n" +
                        "    \"document_number\": \""+document_number+"\",\n" +
                        "    \"direction\": \""+direction+"\",\n" +
                        "    \"password\": \""+password+"\",\n" +
                        "    \"roles\": [\n" +
                        "        "+roles+"\n" +
                        "    ],\n" +
                        "    \"state\": \""+state+"\"\n" +
                        "}");
        response = this.request.when().post(MyConstants.apiAdress +  "User/createUser");
    }

    @Then("^el resultado de la accion deberia ser (.+) al crear usuario$")
    public void validateCreateUser(String resultCriteria) {
        json = response.then().statusCode(200);
        String jsonResponse = response.jsonPath().get("state");
        if(jsonResponse!=null){
            Assert.assertEquals("Error al crear usuario.", resultCriteria.toLowerCase(), jsonResponse.toLowerCase());
        }else{
            Assert.fail("Error al consumir endpoint, respuesta vacia.");
        }
    }

    @When("^ingreso las credenciales email: (.+), contraseña: (.+)$")
    public void enterCredentials(String email, String password) {
        request = given().log().all()
                .contentType("application/json")
                .body("{\n" +
                        "    \"email\" : \"" +email+"\",\n" +
                        "    \"password\": \""+password+"\"\n" +
                        "}");

        response = this.request.when().post(MyConstants.apiAdress + "User/login");
    }

    @Then("^el resultado de la accion deberia ser (.+) al logearse$")
    public void validateLogin(String resultCriteria) {
        json = response.then().statusCode(200);
        String jsonResponse = response.jsonPath().get("state");
        if(jsonResponse!=null){
            Assert.assertEquals("Error al logearse.", resultCriteria.toLowerCase(), jsonResponse.toLowerCase());
        }else{
            Assert.fail("Error al consumir endpoint, respuesta vacia.");
        }
    }
}
