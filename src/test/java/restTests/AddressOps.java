package restTests;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AddAddressPayload;
import utilities.FormatStringToJSON;
import utilities.UpdateAddressPayload;

public class AddressOps {

    public static String placeID;
    @Test(priority = 1)
    public void addAddress(){
        baseURI= "https://rahulshettyacademy.com";
        String response = given().
                log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
                body(AddAddressPayload.PayloadToAddAddress()).
        when().
                post("maps/api/place/add/json").
        then().
                assertThat().log().all().statusCode(200).
                header("server",equalTo("Apache/2.4.41 (Ubuntu)")).
                extract().response().asString();

        JsonPath js = FormatStringToJSON.StringToJSON(response);
        placeID = js.getString("place_id");
        System.out.println("Place ID: "+placeID);
    }
    @Test(priority = 2)
    public void updateAddress(){
        String newAddress ="1-46, CSI Town church road";
        given().
                log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
               body(UpdateAddressPayload.updateAddress(placeID,newAddress)).
//                body("{\r\n" +
//                        "\"place_id\":\""+placeID+"\",\r\n" +
//                        "\"address\":\""+newAddress+"\",\r\n" +
//                        "\"key\":\"qaclick123\"\r\n" +
//                        "}").
        when().
                put("maps/api/place/update/json").
        then().
                assertThat().statusCode(200).
                header("server","Apache/2.4.41 (Ubuntu)").
                body("msg",equalTo("Address successfully updated")).log().all();
    }
    @Test(priority = 3)
    public void getAddress(){
       String resp= given().
                log().all().queryParam("key","qaclick123").queryParam("place_id",placeID).
        when().
                get("maps/api/place/get/json").
        then().
                assertThat().log().all().statusCode(200).
                header("server","Apache/2.4.41 (Ubuntu)").extract().asString();

       JsonPath js = FormatStringToJSON.StringToJSON(resp);
       String actualAddress= js.getString("address");
      //  actualAddress.contains("1-46, CSI Town church road");
        Assert.assertEquals(actualAddress,"1-46, CSI Town church road");
    }
    @Test(priority = 4)
    public void deleteAddress(){
        given().
                log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
                body("{\n" +
                        "    \"place_id\":\""+placeID+"\"\n" +
                        "}").
        when().
                delete("maps/api/place/delete/json").
        then().
                assertThat().log().all().statusCode(200);
    }
    @Test(priority = 5)
    public void getDeletedAddress(){
        given().
                log().all().queryParam("key","qaclick123").queryParam("place_id",placeID).
        when().
                get("maps/api/place/get/json").
        then().
                assertThat().log().all().statusCode(404).
                body("msg",equalTo("Get operation failed, looks like place_id  doesn't exists"));
    }
}