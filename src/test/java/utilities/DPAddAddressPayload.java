package utilities;

public class DPAddAddressPayload {
    public static String addingAddress(String name, String address, long phone){
           return "{\r\n" +
                    "  \"location\": {\r\n" +
                    "    \"lat\": -38.383494,\r\n" +
                    "    \"lng\": 33.427362\r\n" +
                    "  },\r\n" +
                    "  \"accuracy\": 50,\r\n" +
                    "  \"name\": \""+name+"\",\r\n" +
                    "  \"phone_number\": \""+phone+"\",\r\n" +
                    "  \"address\": \""+address+"\",\r\n" +
                    "  \"types\": [\r\n" +
                    "    \"shoe park\",\r\n" +
                    "    \"shop\"\r\n" +
                    "  ],\r\n" +
                    "  \"website\": \"http://rahulshettyacademy.com\",\r\n" +
                    "  \"language\": \"French-IN\"\r\n" +
                    "}\r\n" +
                    "";
        }
}
