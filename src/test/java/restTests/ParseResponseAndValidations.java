package restTests;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ComplexJsonPayload;

public class ParseResponseAndValidations {

    public static JsonPath js;
    @Test(priority = 1)   //Print All course titles and their respective Prices
    public void courseAndPrices(){
        js = new JsonPath(ComplexJsonPayload.payload()); //load the response and cont validate

        int totalCourses= js.get("courses.size()");

        for(int i=0;i<totalCourses;i++){
            System.out.println("Course:"+js.get("courses["+i+"].title")+" & its Price:"+js.get("courses["+i+"].price"));
        }
    }
    @Test(priority = 2) //Print no of copies sold by RPA Course
    public void copiesOfCourse(){
        js = new JsonPath(ComplexJsonPayload.payload());
        int totalCourses= js.get("courses.size()");

        for(int i=0;i<totalCourses;i++){
            if(js.get("courses["+i+"].title").toString().equalsIgnoreCase("RPA")){
                System.out.println("Course:"+js.get("courses["+i+"].title")+" & its Price:"+js.get("courses["+i+"].price"));
                break;
            }
        }
    }
    @Test(priority = 3) // Sum of all course prices equals to purchaseAmount in Dashboard ??
    public void sumOfCoursePrices(){
        js = new JsonPath(ComplexJsonPayload.payload());
        int totalCourses= js.get("courses.size()"), sum =0;

        for(int i=0;i<totalCourses;i++){
           sum = sum+ js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies");
        }
        System.out.println("Sum of sold copies:"+sum);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum,purchaseAmount,"Purchase amount is not equal to sum of sold copies");
        System.out.println("Purchase amount is equal to sum of sold copies");
    }
}
