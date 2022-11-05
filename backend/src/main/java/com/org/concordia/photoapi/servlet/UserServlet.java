package com.org.concordia.photoapi.servlet;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.concordia.photoapi.service.PhotosService;
import com.org.concordia.photoapi.service.PhotosServiceImpl;

@WebServlet(name = "userServlet", urlPatterns = "/getUser")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2872241476921678269L;
	private PhotosService photosService = new PhotosServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException{
		
		String username = req.getParameter("username");
		System.out.println(username);
		
		String password = req.getParameter("password");
		System.out.println(password);
		
		//Encoded password should be from UI, this code is for test purpose only
		Encoder encoder = Base64.getEncoder();
		String encodedString = encoder.encodeToString(password.getBytes());
		System.out.println("Encoded password:" + encodedString);
		
		//Decode password coming from UI to backend to validate authentication
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(encodedString);
		String decodedPassword = new String(bytes);
		System.out.println("Decrypted password:" + decodedPassword);
		
		try
		{
			int user_id = photosService.getUserIdByUsername(username);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			if(user_id!=-1)
			{
				//check password 
				String passwordFromDB = photosService.getPasswordByUsername(username);
				if(passwordFromDB.equals(decodedPassword))
				{
					String jsonString = "User Authenticated";
		            System.out.println(jsonString);
					resp.getWriter().write(jsonString);
				}
				else
				{
					String jsonString = "Please check the password entered";
		            System.out.println(jsonString);
					resp.getWriter().write(jsonString);
				}
			}	
			else {
				String jsonString = "User Authentication Failed";
	            System.out.println(jsonString);
				resp.getWriter().write(jsonString);
			}
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }		
	}
}
