package web;

import pojo.Image;
import pojo.Posts;
import service.ImageService;
import service.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@WebServlet("/changePostServlet")
@MultipartConfig
public class ChangePostServlet extends HttpServlet {
  PostsService service = new PostsService();
  ImageService imageService = new ImageService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    Date date = new Date(System.currentTimeMillis());
    HttpSession session = req.getSession();
    Part filePart = req.getPart("imageFile");
    InputStream fileContent = filePart.getInputStream();
    Image image = new Image();
    int id = Integer.parseInt(req.getParameter("id"));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = fileContent.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
    }
    byte[] imageDataBytes = outputStream.toByteArray();
    image.setImagedata(imageDataBytes);
    String username = (String) session.getAttribute("username");
    String title = req.getParameter("title");
    String content = req.getParameter("content");
    System.out.println(content);
    String category = req.getParameter("category");
    System.out.println(category);
    String country = req.getParameter("country");
    String city = req.getParameter("city");
    Posts posts = new Posts();
    posts.setAuthorName(username);
    posts.setTitle(title);
    posts.setContents(content);
    posts.setCategory(category);
    posts.setPostingCountry(country);
    posts.setPostingCity(city);
    posts.setPostingTime(date);
    posts.setPostID(id);
    service.updatePostID(posts);
    image.setPostid(id);
    imageService.updatePostID(image);
    System.out.println("add successs");
    req.getRequestDispatcher("/homepageServlet?name="+username).forward(req, resp);
    
    
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
