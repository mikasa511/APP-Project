package com.org.concordia.photoapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.Photo;
import com.org.concordia.photoapi.service.PhotosService;
import com.org.concordia.photoapi.service.PhotosServiceImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "getNoOfLikesAllPhotosServlet", urlPatterns = "/get-likes-of-all-photos")
public class getNoOfLikesAllPhotosServlet extends HttpServlet {

	private static final long serialVersionUID = 2872241476921678269L;
	private PhotosService photosService = new PhotosServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Photo> photos = photosService.getPhotos();
			HashMap<Integer, Integer> mapPhotoIdToLikes = new HashMap<>();
			for (Photo photo : photos) {
				mapPhotoIdToLikes.put(photo.getPhotoId(), photosService.getNoOfLikesOfPhoto(photo.getPhotoId()));
			}

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(mapPhotoIdToLikes);
			System.out.println(jsonString);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
