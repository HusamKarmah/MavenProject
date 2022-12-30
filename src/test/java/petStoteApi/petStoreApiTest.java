package petStoteApi;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class petStoreApiTest {
	int fishId;
	int catId;

	@BeforeTest
	public void setUp() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";

	}

	@Test(dependsOnMethods = "postACat")
	public void getPetbyId() {
		RestAssured.given().accept(ContentType.JSON).when().get("/pet/1982").then().statusCode(200);

	}

	@Test
	public void getPetByStatus() {
		RestAssured.given().accept(ContentType.JSON).contentType("application/json").param("status", "available").when()
				.get("/pet/findByStatus").then().statusCode(200).contentType("application/json");
	}

	@Test(dependsOnMethods = { "postACat", "updateCat" })
	public void getAssertById() {
		Response myResponse = RestAssured

				.given().accept(ContentType.JSON).when().get("/pet/1982");
		myResponse.prettyPrint();
		// verifying the status Code
		myResponse.then().assertThat().statusCode(200).and().contentType("application/json");

		// verifying the body
		String petName = myResponse.path("tags[0].name");
		System.out.println("The Pet name is: " + petName);
		Assert.assertEquals(petName, "persian");

		String petNanme1 = myResponse.path("tags[1].name");
		System.out.println("The Pet name is: " + petNanme1);
		Assert.assertEquals(petNanme1, "Tina");

		int petId = myResponse.body().path("tags[0].id");
		System.out.println("The pet id is: " + petId);

		int petId1 = myResponse.body().path("tags[1].id");
		System.out.println("The pet id is: " + petId1);

		int categId = myResponse.path("category.id");
		System.out.println("Category ID is: " + categId);
		Assert.assertEquals(categId, 22);

		String categName = myResponse.path("category.name");
		System.out.println("Categotry Name is: " + categName);
		Assert.assertEquals(categName, "Cat");

		String CatStatus = myResponse.body().jsonPath().get("status");
		Assert.assertEquals(CatStatus, "pending");
	}

	@Test
	public void addPet() {

		String requestBody = "{\n" + "    \"id\": 2010,\n" + "    \"category\": {\n" + "        \"id\": 22,\n"
				+ "        \"name\": \"Dog\"\n" + "    },\n" + "    \"name\": \"Blacky\",\n" + "    \"photoUrls\": [\n"
				+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n" + "        {\n"
				+ "            \"id\": 909,\n" + "            \"name\": \"German Sheprad\"\n" + "        },\n"
				+ "         {\n" + "            \"id\": 808,\n" + "            \"name\": \"Pit Bull\"\n" + "        }\n"
				+ "    ],\n" + "    \"status\": \"available\"\n" + "}";

		Response myResponse = RestAssured.given().accept(ContentType.JSON).body(requestBody)
				.contentType("application/json").when().post("/pet");

		myResponse.then().statusCode(200).and().contentType("application/json");

	}

	@Test
	public void postACat() {

		String requestBody = "{\n" + "    \"id\": 1982,\n" + "    \"category\": {\n" + "        \"id\": 22,\n"
				+ "        \"name\": \"Cat\"\n" + "    },\n" + "    \"name\": \"Fullah\",\n" + "    \"photoUrls\": [\n"
				+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n" + "        {\n"
				+ "            \"id\": 15,\n" + "            \"name\": \"persian\"\n" + "        },\n" + "         {\n"
				+ "            \"id\": 16,\n" + "            \"name\": \"Tina\"\n" + "        }\n" + "    ],\n"
				+ "    \"status\": \"available\"\n" + "}";

		Response myResponse = RestAssured.given().accept(ContentType.JSON).body(requestBody)
				.contentType("application/json").when().post("/pet");

		myResponse.then().statusCode(200).and().contentType("application/json");

		catId = myResponse.jsonPath().get("id");

	}

	// Update the cat status to pending using PUT method
	@Test(dependsOnMethods = "postACat")
	public void updateCat() {
		String catRequestBody = "{\n" + "    \"id\": 1982,\n" + "    \"category\": {\n" + "        \"id\": 22,\n"
				+ "        \"name\": \"Cat\"\n" + "    },\n" + "    \"name\": \"Fullah\",\n" + "    \"photoUrls\": [\n"
				+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n" + "        {\n"
				+ "            \"id\": 15,\n" + "            \"name\": \"persian\"\n" + "        },\n" + "         {\n"
				+ "            \"id\": 16,\n" + "            \"name\": \"Tina\"\n" + "        }\n" + "    ],\n"
				+ "    \"status\": \"pending\"\n" + "}";

		Response catResponse = RestAssured.given().contentType(ContentType.JSON).contentType("application/json")
				.body(catRequestBody).when().put("/pet");

		// Basic validation for any API test
		catResponse.then().statusCode(200).and().contentType("application/json");

		// validate the status

		Assert.assertEquals(catResponse.body().jsonPath().get("status"), "pending");

	}

	@Test
	public void postAfish() {
		String requstFishBody = "{\n" + "    \"id\": 2016,\n" + "    \"category\": {\n" + "        \"id\": 22,\n"
				+ "        \"name\": \"Fish\"\n" + "    },\n" + "    \"name\": \"Nemo\",\n" + "    \"photoUrls\": [\n"
				+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n" + "        {\n"
				+ "            \"id\": 15,\n" + "            \"name\": \"Sord Fish\"\n" + "        },\n"
				+ "         {\n" + "            \"id\": 16,\n" + "            \"name\": \"Mezzo\"\n" + "        }\n"
				+ "    ],\n" + "    \"status\": \"available\"\n" + "}";

		Response myFishResponse = RestAssured.given().accept(ContentType.JSON).body(requstFishBody)
				.contentType("application/json").when().post("/pet");

		// Validation
		myFishResponse.then().statusCode(200).and().contentType("application/json");

		// Assertion

		Assert.assertEquals(myFishResponse.body().jsonPath().get("status"), "available");
		myFishResponse.prettyPrint();

		fishId = myFishResponse.jsonPath().get("id");

	};

	public void deleteAfish() {
		Response deleteRes = RestAssured.given().accept(ContentType.JSON).contentType("application/json").when()
				.delete("/pet/" + fishId);

		deleteRes.then().statusCode(200).and().contentType("application/json");

		Assert.assertEquals(deleteRes.body().jsonPath().get("message"), String.valueOf(fishId));
	}

	public void deleteAcat() {
		Response deleteRes = RestAssured.given().accept(ContentType.JSON).contentType("application/json").when()
				.delete("/pet/" + catId);

		deleteRes.then().statusCode(200).and().contentType("application/json");

		Assert.assertEquals(deleteRes.body().jsonPath().get("message"), String.valueOf(catId));
	}

	// create a cat with a request body in json file

	@Test
	public void postCatJson() {
		File requestBodyFile = new File("./src/test/resources/JsonTestData/petStore.json");

		Response myRes = RestAssured.given().accept(ContentType.JSON).body(requestBodyFile)
				.contentType("application/json").when().post("/pet");

		myRes.then().statusCode(200).and().contentType("application/json");

		catId = myRes.jsonPath().get("id");

	}

	@AfterTest
	public void cleanUp() {
		postAfish();
		deleteAcat();

	}

	// Negative test case
	@Test
	public void invalidI() {

		String catRes = "{\n" + "    \"id\": '1982',\n" + "    \"category\": {\n" + "        \"id\": 22,\n"
				+ "        \"name\": \"Cat\"\n" + "    },\n" + "    \"name\": \"Fullah\",\n" + "    \"photoUrls\": [\n"
				+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n" + "        {\n"
				+ "            \"id\": 15,\n" + "            \"name\": \"persian\"\n" + "        },\n" + "         {\n"
				+ "            \"id\": 16,\n" + "            \"name\": \"Tina\"\n" + "        }\n" + "    ],\n"
				+ "    \"status\": \"pending\"\n" + "}";

		Response myRes = RestAssured.given().accept(ContentType.JSON).body(catRes).contentType("application/json")
				.when().put("/pet");

		// Validation
		myRes.then().statusCode(400).and().contentType("application/json");
		myRes.prettyPrint();
		Assert.assertEquals(myRes.body().jsonPath().get("message"), "bad input");

	}

	// RestAssured chain Validation
	// to be add from Dec 15 - RestAssured & PetStore API Tests Continues lecture

}
