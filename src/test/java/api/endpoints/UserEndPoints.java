package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Created to perform CRUD operations on user APIs

public class UserEndPoints {
	
	//load properties file
	public static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(User payload) {
		
		String post_url = getURL().getString("post_url");
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(post_url)
		;
		return res;
	}
	
	public static Response readUser(String username) {
		
		String get_url = getURL().getString("get_url");
		
		Response res = given()
			.pathParam("username", username)
			
		.when()
			.get(get_url)
		;
		return res;
	}

	public static Response updateUser(User payload, String username) {
		
		String update_url = getURL().getString("update_url");
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username", username)
			
		.when()
			.put(update_url)
		;
		return res;
	}
	
	public static Response deleteUser(String username) {
		
		String delete_url = getURL().getString("delete_url");
		
		Response res = given()
				.pathParam("username", username)
				
			.when()
				.delete(delete_url);
			return res;
	}

} //class
