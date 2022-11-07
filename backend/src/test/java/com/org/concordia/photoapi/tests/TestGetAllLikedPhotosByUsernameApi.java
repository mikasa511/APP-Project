package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.Photo;

public class TestGetAllLikedPhotosByUsernameApi extends BaseSetup
{
	public static final String user = "testuser";
	public static final String photoId = "1234567";
	
	@Test(priority=0)
	public void addPhotosLikedByUser() throws JsonParseException, JsonMappingException, IOException
	{
	
	    given().
		when().
		queryParam("username", user).
		queryParams("photoId",photoId).
		post("/add-liked-photos").
		then().
		assertThat().
		statusCode(200);

	}
	
	@Test(priority=1)
	public void getLikedPhotos() throws JsonParseException, JsonMappingException, IOException
	{
	
		String getAllLikedPhotosInResponse = given().
		when().
		queryParam("username", user).
		get("/get-liked-photos").
		then().
		assertThat().
		statusCode(200).extract().response().asPrettyString();
		
		System.out.println(getAllLikedPhotosInResponse);
		
		ObjectMapper mapper = new ObjectMapper();
		List<Photo> photos = mapper.readValue(getAllLikedPhotosInResponse, new TypeReference<List<Photo>>() {});
		
		//assert values in json response
		Assert.assertEquals(photos.get(0).getPhotoId(),1234567);
		Assert.assertEquals(photos.get(0).getPhotographerId(),1234);
		Assert.assertEquals(photos.get(0).getAvgColor(),"#515149");
		Assert.assertEquals(photos.get(0).getTitle(),"Its Test Data");
		Assert.assertEquals(photos.get(0).getImageMediumSize(),"photo-1234567-md.jpeg");
		Assert.assertEquals(photos.get(0).getImageLargeSize(),"photo-1234567-lg.jpeg");
		Assert.assertEquals(photos.get(0).getImageOrignalSize(),"photo-1234567-o.jpeg");
	}
}
