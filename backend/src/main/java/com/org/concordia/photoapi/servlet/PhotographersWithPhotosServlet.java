package com.org.concordia.photoapi.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.Photo;
import com.org.concordia.photoapi.model.Photographer;
import com.org.concordia.photoapi.model.PhotographerWithPhotos;
import com.org.concordia.photoapi.service.PhotographerService;
import com.org.concordia.photoapi.service.PhotographerServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
  name = "photographersWithPhotosServlet",
  urlPatterns = "/get-photographers-with-photos"
)
public class PhotographersWithPhotosServlet extends HttpServlet {

  private static final long serialVersionUID = 2872241476921678269L;
  private PhotographerService photographerService = new PhotographerServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    try {
      List<PhotographerWithPhotos> photographerWithPhotoslist = new ArrayList<PhotographerWithPhotos>();
      List<Photographer> photographers = photographerService.getPhotographers();

      for (Photographer photographer : photographers) {
        List<Photo> photos = photographerService.getPhotosByPhotographerId(
          photographer.getphotographerId()
        );
        photographerWithPhotoslist.add(
          new PhotographerWithPhotos(photographer, photos)
        );
      }

      ObjectMapper mapper = new ObjectMapper();
      String jsonString = mapper.writeValueAsString(photographerWithPhotoslist);
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