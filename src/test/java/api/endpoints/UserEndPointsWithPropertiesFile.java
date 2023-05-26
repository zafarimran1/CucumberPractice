
package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import  io.restassured.http.ContentType;
import io.restassured.response.Response;


// to perform CRUD (Create, Read, Edit/Update, Delete)

public class UserEndPointsWithPropertiesFile {
	
	// Method to get URLs from properties file
	
	static ResourceBundle getUrl(){
		
		 ResourceBundle routes = ResourceBundle.getBundle("routes");
		 
		 return routes;
	
	}
	
	

	public static Response createUser (User payload) {
		
		String postUrl = getUrl().getString("postUrl");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(postUrl);
		return response;
	}
	
	
	public static Response readUser (String userName) {
		
		String getUrl = getUrl().getString("getUrl");
		
		Response response = given()
			.pathParam("username", userName)
		.when()
			.get(getUrl);
		return response;
	}
	
	public static Response updateUser (String userName , User payload) {
		
		String updateUrl = getUrl().getString("updateUrl");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
			
		.when()
			.put(updateUrl);
		return response;
	}
	
	public static Response deleteUser (String userName) {
		
		String deleteUrl = getUrl().getString("deleteUrl");
		
		Response response = given()
			
			.pathParam("username", userName)
		.when()
			.delete(deleteUrl);
		return response;
	}
}
