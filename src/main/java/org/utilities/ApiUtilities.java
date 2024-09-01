package org.utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import org.junit.Assert;
import static io.restassured.RestAssured.given;


public class ApiUtilities {


    public static Response response;
    public void getReqResBuilder(String url, String endpoint){

        RestAssured.baseURI = url;

        try {
            response =
                    given().
                            when().
                            get(endpoint).
                            then().
                            extract().response();

        } catch (AssertionError e) {
            System.err.println("Assertion Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }


    }



    public void extractResponse(String field, String expectedValue){

        try {

           String actualValue = response.jsonPath().getString(field);

           Assert.assertEquals(expectedValue, actualValue);
            System.out.println("Result: Expected: "+ expectedValue +" | Actual: "+actualValue   );

        } catch (JsonPathException e) {
            System.err.println("JSON Path Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

    }

    public void extractPlaceName(String expectedPostCode, String expectedPlaceName) {

        try {
            String strResponseBody = response.getBody().asString();
            JsonPath js = new JsonPath(strResponseBody);

            int count = js.getInt("places.'post code'.size()");

            String actualPlaceName = null;
            for (int i = 0; i < count; i++) {
                String actualPostCode = js.getString("places.'post code'[" + i + "]");

                if (actualPostCode.equals(expectedPostCode)) {

                    actualPlaceName = js.getString("places.'place name'[" + i + "]");

                    if (actualPlaceName.equals(expectedPlaceName)) {

                        System.out.println("place name : " + actualPlaceName + " found for postCode " + actualPostCode);
                        break;
                    }

                }
            }
            Assert.assertEquals(expectedPlaceName, actualPlaceName);

        } catch (JsonPathException e) {
            System.err.println("JSON Path Exception: " + e.getMessage());
        }  catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

    }

}
