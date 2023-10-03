package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;

import io.restassured.response.Response;

public class DDTest {

	@Test(priority=1, dataProvider="dp_all", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String firstName, String lastName,
							 String emailAddress, String password, String phoneNumber) {
		
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(emailAddress);
		userPayload.setPassword(password);
		userPayload.setPhone(phoneNumber);
		
		Response res = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="dp_usernames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String username) {
		
		Response res = UserEndPoints.deleteUser(username);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
} //class
