package com.qa.testScripts;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonResponceBody {
	
	@Test
	public void testResponseBody() {
		Response res = 
				given().contentType(ContentType.JSON)
				.when()
				.get("http://localhost:3000/book");
				
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
		String bookname =res.jsonPath().get("book[3].title");
		Assert.assertEquals(bookname, "The Lord of the Rings");

	}
	@Test
	void getTitles() {
	Response res = given()
						.contentType(ContentType.JSON)
					.when()
						.get("http://localhost:3000/book");
	
	
	//using JSONObject class
	JSONObject jo = new JSONObject(res.asString());
	
	boolean status = false;
	for(int i=1;i<jo.getJSONArray("book").length();i++) {
		String title =jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		System.out.println(title);
		if(title.equals("Moby Dick")) {
			status =true;
			break;
		}
		
	}
	Assert.assertEquals(status, true);
	}
}
