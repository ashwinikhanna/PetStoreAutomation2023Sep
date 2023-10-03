package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Created to perform CRUD operations on user APIs

public class __UserEndPoints {
	
	public static Response createUser(User payload) {
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(__Routes.post_url)
		;
		return res;
	}
	
	public static Response readUser(String username) {
		
		Response res = given()
			.pathParam("username", username)
			
		.when()
			.get(__Routes.get_url)
		;
		return res;
	}

	public static Response updateUser(User payload, String username) {
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username", username)
			
		.when()
			.put(__Routes.put_url)
		;
		return res;
	}
	
	public static Response deleteUser(String username) {
		Response res = given()
				.pathParam("username", username)
				
			.when()
				.delete(__Routes.delete_url)
			;
			return res;
	}

} //class
