package utilities;

import io.restassured.path.json.JsonPath;

public class FormatStringToJSON {

    public static JsonPath StringToJSON(String resp){

        JsonPath js = new JsonPath(resp);
        return js;

    }
}
