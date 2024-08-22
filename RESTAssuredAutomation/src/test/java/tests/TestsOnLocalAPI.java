package tests;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;


import org.json.simple.JSONObject;

//Make sure JSON Server is running in Command Line, use input "json-server --watch db.json"
public class TestsOnLocalAPI {
	
	//@Test
	public void get() {
		
		baseURI = "http://localhost:3000";
		
		given().get("/users").then().statusCode(200).log().all();
		
	}
	
	//@Test
	public void post() {
		
		JSONObject request = new JSONObject();
		
		request.put("firstName", "Steve");
		request.put("lastName", "Jobs");
		request.put("subjectId", 1);

		baseURI = "http://localhost:3000";
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.post("/users")
		.then()
			.statusCode(201)
			.log().all();
	
	}
	
	//@Test
	public void put() {
		
		JSONObject request = new JSONObject();
		
		request.put("firstName", "Steffan");
		request.put("lastName", "Careers");
		request.put("subjectId", 1);

		baseURI = "http://localhost:3000";
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.put("/users/4")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test
	public void patch() {
		
		JSONObject request = new JSONObject();
		
		request.put("lastName", "Doe");

		baseURI = "http://localhost:3000";
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.patch("/users/4")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test
	public void delete() {
		
		baseURI = "http://localhost:3000";
		
		when().delete("/users/4").then().statusCode(200).log().all();
		
	}
}
