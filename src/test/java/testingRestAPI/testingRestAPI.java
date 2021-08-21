package testingRestAPI;

//import io.restassured.http.Method;
import org.testng.annotations.Test;
import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.junit.*;
import org.junit.jupiter.api.Assertions;

public class testingRestAPI {

	String token="7d5ccc8f338bafa05beafdb50cf40cc6637d6227";
	
	@Test
	public void createTask() {
		
		JSONObject object=new JSONObject();
		
//		{"content": "Buy Milk", "due_string": "tomorrow at 12:00", "due_lang": "en", "priority": 4}
		object.put("content", "Buy Milk");
		object.put("due_string", "tomorrow at 12:00");
		object.put("due_lang", "en");
		object.put( "priority", 4);
		
		RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.header("Authorization","Bearer "+token)
				.header("Content-Type","application/json")
				.put("https://api.todoist.com/rest/v1/tasks");
		
		String responseBody = response.getBody().asString();
		
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(responseBody.contains("content"),"Buy Milk");
		
		
	}
	@Test
	public void FetchData()
	{   
		
		
		RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";

		RequestSpecification httpRequest = RestAssured.given();

		
		Response response = httpRequest.header("Authorization","Bearer "+token)
										.header("Content-Type","application/json")
										.get("https://api.todoist.com/rest/v1/tasks");

	
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode , 200 );
		System.out.println("correct status code returned");
		System.out.println("Response Body is =>  " + responseBody);
		
	
	}
	@Test
	public void ContentCheck()
	{   

		RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";

		RequestSpecification httpRequest = RestAssured.given();
		
		
		
		
		Response response = httpRequest.header("Authorization","Bearer "+token)
										.header("Content-Type","application/json")
										.get("/5086532140");
										
		
		String responseBody = response.getBody().asString();
		String content="complete api testing";
		
		
//		System.out.println(responseBody);
		Assert.assertEquals(content,responseBody.contains("content"),true);		
	}
	
	@Test
	
	public void IdCheck()
	{   
		
		RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";

		RequestSpecification httpRequest = RestAssured.given();
		
		
		
		
		Response response = httpRequest.header("Authorization","Bearer "+token)
										.header("Content-Type","application/json")
										.delete("/5084809003");
		
		Response getResponse=httpRequest.header("Authorization","Bearer "+token)
				.header("Content-Type","application/json")
				.get("/5084809003");
										
		
		String responseBody = getResponse.getBody().asString();
	
		
//		5084809003
		System.out.println("delete status"+response.getStatusCode());
		Assert.assertEquals(204,response.getStatusCode());
		Assert.assertEquals(404,getResponse.getStatusCode());		
	}
	
	@Test
	public void getActiveTask()
	{   
		
		
		RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";

		
		RequestSpecification httpRequest = RestAssured.given();

		
		Response response = httpRequest.header("Authorization","Bearer "+token)
										.header("Content-Type","application/json")
										.get("/5084063307");

		
		String responseBody = response.getBody().asString();
		int statusCode = response.statusCode();
		
		Assert.assertEquals("false",response.jsonPath().getString("completed"));
//		 Assert that correct status code is returned.
		Assert.assertEquals(statusCode , 200 );
		
				

	}
	@Test
	public void closeTask()
	{   
		
		
		RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";

		
		RequestSpecification httpRequest = RestAssured.given();

		
		Response response = httpRequest.header("Authorization","Bearer "+token)
										.header("Content-Type","application/json")
										.post("/5086589421/close");
										
		

		
		String responseBody = response.getBody().asString();
		int statusCode = response.statusCode();
		System.out.println(statusCode);
//		 Assert that correct status code is returned.
		Assert.assertEquals(statusCode , 204 );
		
			
	}
	@Test
	
	public void getInActiveTask()
	{   
		
		
		RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";

		
		RequestSpecification httpRequest = RestAssured.given();

		
		Response response = httpRequest.header("Authorization","Bearer "+token)
										.header("Content-Type","application/json")
										.get("/5086589421");

		
		String responseBody = response.getBody().asString();
		int statusCode = response.statusCode();
		
		Assert.assertEquals(statusCode , 404 );
		
				
	}
	

}