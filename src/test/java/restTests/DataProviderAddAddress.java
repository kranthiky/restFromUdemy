package restTests;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.DPAddAddressPayload;
import utilities.FormatStringToJSON;

import static io.restassured.RestAssured.*;

public class DataProviderAddAddress {
    @Test(priority = 1,dataProvider = "addressData")
    public void addAddresses(String name, String address, long phone ){
        baseURI ="https://rahulshettyacademy.com";
        String resp= given().
                log().all().header("Content-Type","application/json").
                body(DPAddAddressPayload.addingAddress(name,address,phone)).
        when().
                post("maps/api/place/add/json").
        then().log().all().
                assertThat().statusCode(200).
           //     body("Msg",equalTo("successfully added")).
                extract().asString();

        JsonPath js = FormatStringToJSON.StringToJSON(resp);
        String placeID = js.get("id");
        System.out.println(placeID);
    }
    @DataProvider(name="addressData")
    public Object[][] addresses(){
        return new Object[][] {{"John","1-46, kpd",4545424},{"Luke","9-129/1 CSI Town church",5782112},{"Matthew","007 Bond",45654675}};
    }
}
