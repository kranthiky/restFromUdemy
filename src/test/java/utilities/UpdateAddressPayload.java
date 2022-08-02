package utilities;

public class UpdateAddressPayload {

    public static String updateAddress(String placeID, String newAddress){
        return "{\n" +
                "\"place_id\":\""+placeID+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
      }
}
