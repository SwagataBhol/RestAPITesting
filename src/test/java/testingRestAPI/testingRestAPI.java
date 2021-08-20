package testingRestAPI;

//import io.restassured.http.Method;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.junit.*;
import org.junit.jupiter.api.Assertions;

public class testingRestAPI {

	String token="7d5ccc8f338bafa05beafdb50cf40cc6637d6227";
	@Test
	public void FetchData()
	{   
		
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.

		
		Response response = httpRequest.header("Authorization","Bearer "+token)
										.header("Content-Type","application/json")
										.get("https://api.todoist.com/rest/v1/tasks");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode , 200 );
		System.out.println("correct status code returned");
		System.out.println("Response Body is =>  " + responseBody);
		
		

	}
	@Test
	public void ContentCheck()
	{   
//		 5082155710 complete task
//		"id": 5082155710,
//		"assigner": 0,
//		"project_id": 2271781717,
//		"section_id": 0,
//		"order": 1,
//		"content": "complete project",
//		"description": "",
//		"completed": false,
//		"label_ids": [],
//		"priority": 1,
//		"comment_count": 0,
//		"creator": 35160461,
//		"created": "2021-08-19T12:04:06Z",
//		"url": "https://todoist.com/showTask?id=5082155710"
//	},
		RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";

		RequestSpecification httpRequest = RestAssured.given();
		
		
		
		
		Response response = httpRequest.header("Authorization","Bearer "+token)
										.header("Content-Type","application/json")
										.get("/5082155710");
										
		
		String responseBody = response.getBody().asString();
		String content="perform on the rest assured";
		
		
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
		System.out.println(response.getStatusCode());
		Assert.assertEquals(204,response.getStatusCode());
		Assert.assertEquals(404,getResponse.getStatusCode());		
	}

}