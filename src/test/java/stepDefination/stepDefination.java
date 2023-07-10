package stepDefination;

import io.restassured.path.json.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.PrintStream;

import PayLoad.Paloader;
import static io.restassured.RestAssured.*;
public class stepDefination {
	public static RequestSpecification RequestSpec;
	public static ResponseSpecification ResponseSpec;
	RequestSpecification request;
	RequestSpecification getreq;
	Response response;
	RequestSpecification getspecReq ;
	Response getres;
	static String placeid;
	RequestSpecification delrequest;
	Response delresponse;
	String delres;
	
	@Given("Add Place Payload with {string} {string}")
	public void add_place_payload_with(String name, String phone_number) throws Throwable {
		
		PrintStream save = new PrintStream(new FileOutputStream("log.txt"));
		RequestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(save))
		.addFilter(ResponseLoggingFilter.logResponseTo(save))
		.setContentType(ContentType.JSON).build();
		
		ResponseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		 request = given().spec(RequestSpec).body(Paloader.payload(name,phone_number));
	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) throws Throwable {
		 response = request.when().post("/maps/api/place/add/json").then().spec(ResponseSpec).extract().response();
	}

	@Then("the API call got success with ststus code {int}")
	public void the_api_call_got_success_with_ststus_code(Integer int1) throws Throwable {
		 assertEquals(response.getStatusCode(),200); 
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String ActualValue, String expectedValue) throws Throwable {
	
		String resp = response.asString();
		JsonPath jr = new JsonPath(resp);
		
		assertEquals(jr.get(ActualValue).toString(),expectedValue);
		
		placeid = jr.getString("place_id");
		System.out.println(placeid);
		
	}
	
	@Then("Verify place_Id created maps to {string} using getPlaceAPI")
	public void verify_place_id_created_maps_to_using_get_place_api(String Expectedname) throws Throwable {
	  
	   getreq = given().spec(RequestSpec).queryParam("place_id", placeid);
	   getres = getreq.when().get("/maps/api/place/get/json").then().spec(ResponseSpec).extract().response();
	   
	   String checkres = getres.asString();
	   JsonPath js = new JsonPath(checkres);
	   String Actualname = js.getString("name");
	   
	   assertEquals(Actualname,Expectedname);
	   
	   System.out.println("ActualName:"+" "+ Actualname);
	   System.out.println("Expectedname :"+" "+ Expectedname);
	   System.out.println("Asian 1st USER IST");
	 
	}
	
	@Given("Add Delete Place Payload")
	public void add_delete_place_payload() throws Throwable {
		delrequest = given().spec(RequestSpec).body(Paloader.delpayload(placeid));   
	}

	@When("user calls {string} with Delete http request")
	public void user_calls_with_delete_http_request(String string) throws Throwable  {
	   
		delresponse = delrequest.when().delete("/maps/api/place/delete/json").then().spec(ResponseSpec).extract().response();
	}
	
	@Then("the API call got success with ststus2 code {int}")
	public void the_api_call_got_success_with_ststus2_code(Integer int1) throws Throwable {
		assertEquals(delresponse.getStatusCode(),200);
	}

	@Then("{string} in response body of detele is {string}")
	public void in_response_body_of_detele_is(String ActualVal, String ExpectedVal) throws Throwable {
		
		delres = delresponse.asString();
		JsonPath jd = new JsonPath(delres);
		String Status = jd.getString("status");
		
		assertEquals(jd.get(ActualVal),ExpectedVal);
		
		System.out.println(Status);
	}

}
