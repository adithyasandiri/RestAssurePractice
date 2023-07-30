package com.qa.testScripts;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentication {
	@Test
	void testBasicAuth() {
		given()
			.auth().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true));
		
		System.out.println("BasicAuthentaiocation");
		
	}

	@Test
	void testDigestcAuth() {
		given()
			.auth().digest("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true));
		System.out.println("DigestAuthentaiocation");
	}
	@Test
	void testPreemptiveAuth() {
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true));
		System.out.println("PreemptiveAuthentication");
		
	}
	
	@Test
	void testBearerToken() {
		
		String bearerToken = "5342590ce6d2771179779df771af4679ccded3413645cb02c3b487434e4e5713";
		
		given()
			.header("Authorization","Bearer "+bearerToken)
		.when()
			.get("https://simple-books-api.glitch.me/orders")
		.then()
			.statusCode(200)
			.log().all();


	}

	//synatx for validating through Oauth 1 key
	@Test
	void testOauth1() {
		given()
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
		.when()
			.get("URL")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	@Test
	void testOauth2() {
		given()
			.auth().oauth2("5342590ce6d2771179779df771af4679ccded3413645cb02c3b487434e4e5713")
		.when()
			.get("https://simple-books-api.glitch.me/orders")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test
	void testApiKeyAuth() {
		//method 1
		/*given()
			.queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c")//appid is API Key
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
		.log().all();*/
		
		given()
		.queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c")//appid is API Key
		.pathParam("mypath", "data/2.5/forecast/daily")
		.queryParam("q","Delhi")
		.queryParam("units","metric")
		.queryParam("cnt","7")
	.when()
		.get("https://api.openweathermap.org/{mypath}")
	.then()
	.log().all();
		
	}

}
