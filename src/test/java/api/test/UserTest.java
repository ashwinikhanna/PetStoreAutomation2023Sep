package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		//generate data to pass to the POJO class
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testCreateUser() {
		logger.info("******* creating user *********");
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("******* user created *********");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("******* reading user info *********");
		Response res = UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		logger.info("******* user info read and displayed *********");
	}
	
	@Test(priority=3)
	void testJsonSchemaValidation() {
		logger.info("******* validating json schema *********");
		Response res = UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json"));
		logger.info("******* json schema validated *********");
	}
	
	
	@Test(priority=4)
	public void testUpdteUserByName() {
		logger.info("******* updating user info *********");
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response res = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//checking data after updation
		res = UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().body();
		//
		String res_body = res.body().asString();
		Assert.assertTrue(res_body.contains(this.userPayload.getFirstName()) );
		Assert.assertTrue(res_body.contains(this.userPayload.getLastName()) );
		Assert.assertTrue(res_body.contains(this.userPayload.getEmail()) );
		
		Assert.assertEquals(res.getStatusCode(),200);
		logger.info("******* user info updated *********");
	}
	
	@Test(priority=5)
	public void testDeleteUserByName() {
		logger.info("******* deleting user info *********");
		Response res = UserEndPoints.deleteUser(this.userPayload.getUsername());
		res.then().statusCode(200);
		logger.info("******* user deleted *********");
	}
	
} //class
