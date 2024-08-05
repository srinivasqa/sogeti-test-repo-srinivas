package org.stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utilities.ApiUtilities;
import org.utilities.RandomDataGenerator;
import org.utilities.Utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;
import static org.utilities.Utilities.getPropertiesFileValue;

public class StepDefinitions {

    Utilities util = new Utilities();
    ApiUtilities apiUtilities = new ApiUtilities();

    private Response response;
    private String country;
    private String postalCode;

    @When("I open url {string}")
    public void i_open_the_url(String strUrl) {
        util.getUrl(strUrl);
    }

    @And("I mouse hover on {string}")
    public void i_MouseHover(String element){
        util.mouseHover(element);
    }

    @And("I click on {string}")
    public void i_click(String element){
        util.btnClick(element);
    }

    @Then("I verify if the element {string} contains text {string}")
    public void i_verify_expectedText_with_actualText(String actual, String expected){

        util.verifyText(expected,actual);
    }

    @Then("I verify if elements {string} and {string} are selected")
    public void i_verify_if_elements_are_selected(String element1, String element2){

        util.i_verify_color_of_elements(element1,element2);

        Assert.assertEquals("Red",util.colorName1);
        System.out.println("Services dropdown is found selected");
        Assert.assertEquals("Red",util.colorName2);
        System.out.println("Automation link is found selected");

    }

    @When("I scroll the page until element {string}")
    public void i_scrollDown_till_contactUs(String strPath){

        util.scrollDownUntilElement(strPath);

    }

    @Given("I enter random input in {string} field")
    public void i_enter_random_input_to_textbox(String element){

        if(element.equals("First Name")){
         String firstName = RandomDataGenerator.getRandomFirstName();
         util.sendKeys(util.getElement(element),firstName);
        }else if(element.equals("Last Name")){
            String lastName = RandomDataGenerator.getRandomLastName();
            util.sendKeys(util.getElement(element),lastName);
        }else if(element.equals("Email")){
            String email = RandomDataGenerator.generateRandomEmail(RandomDataGenerator.getRandomFirstName(),RandomDataGenerator.getRandomLastName());
            util.sendKeys(util.getElement(element),email);
        }else if(element.equals("Phone")){
            String phone = RandomDataGenerator.generateRandomPhoneNumber();
            util.sendKeys(util.getElement(element),phone);
        }else if(element.equals("Message")){
            String message = RandomDataGenerator.generateRandomMessage();
            util.sendKeys(util.getElement(element),message);
        }

    }

    @When("I switch to captcha frame and select captcha check box")
    public void clickCaptcha(){

        util.switchToCaptchaFrameandClick();
    }

    @Then("I open all links under {string} and check if links are working")
    public void i_check_all_sogeti_country_links_are_working(String element){

        util.checkAllLinks(element);
    }

    @When("I trigger a GET call to url {string} with endpoint {string}")
    public void i_trigger_get_call(String url, String endpoint){

        apiUtilities.getReqResBuilder(url,endpoint);

    }


    @Then("I verify if {string} field contains the value as {string}")
    public void i_verify_response(String field, String value){

        apiUtilities.extractResponse(field,value);

    }

    @Given("I send a GET request to the Zippopotam API for country {string} and postal code {string}")
    public void sendGetRequest(String country, String postalCode) {
        RestAssured.baseURI = "http://api.zippopotam.us";
        this.country = country;
        this.postalCode = postalCode;

        try {
            response =
                    given().
                            when().
                            get("/" + country + "/" + postalCode).
                            then().
                            extract().response();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());

    }

    @And("the response content type should be JSON")
    public void verifyContentType() {
        Assert.assertEquals(ContentType.JSON.toString(),response.getContentType());
        System.out.println("Content Type is as expected: "+ ContentType.JSON.toString()+ "Actual content Type: "+response.getContentType());
    }

    @And("the response time should be less than {int} second")
    public void verifyResponseTime(int seconds) {
        Assert.assertTrue(response.getTime() < 1000);
        System.out.println("Resposne expected time: <1000 ms " + "Response Actual time taken: "+response.getTime() + "ms");
    }

    @And("the response should contain place name {string}")
    public void verifyPlaceName(String expectedPlaceName) {

        String strResponse = response.asString();
        System.out.println(strResponse);
        List<String> placeNames = response.jsonPath().getList("places.'place name'");

        if (!placeNames.isEmpty()) {
            String actualPlaceName = placeNames.get(0);
            System.out.println();
            Assert.assertEquals(expectedPlaceName, actualPlaceName);
        } else {
            // If the list is empty, fail the test
            fail("No place names found for postal code " + postalCode);
        }


    }

}
