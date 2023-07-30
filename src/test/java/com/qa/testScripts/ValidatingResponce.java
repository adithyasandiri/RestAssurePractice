package com.qa.testScripts;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidatingResponce {

	@Test
	void testResponceBody() {
		Response res = given()
							.contentType(ContentType.JSON)
						.when()
							.get("https://reqres.in/api/users");
		JSONObject jo = new JSONObject(res.asString());
		
		for(int i=0;i<jo.getJSONArray("data").length();i++) {
			String emailId = jo.getJSONArray("data").getJSONObject(i).get("email").toString();
			System.out.println(emailId);
		}
		
	}
	@Test
	void testStudentResponce() {
		Response res = given().contentType(ContentType.JSON)
				.when().get("http://localhost:3000/student");
		
		JSONObject jo = new JSONObject(res.toString());
		//JSONArray ja = new JSONArray(res.toString());
		
		for(int i=0;i<jo.getJSONArray("student").length();i++) {
			String name = jo.getJSONArray("student").getJSONObject(i).get("name").toString();
			System.out.println(name);
		}
	}
}
