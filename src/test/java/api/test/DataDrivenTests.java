package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserId, String UserName, String fname, String lname, String userEmail, String pwd, String phone ) {
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(UserId));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
	@Test(priority=1, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String UserName) {
		
		Response response = UserEndPoints.deleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
