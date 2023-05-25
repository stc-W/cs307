package web;

import pojo.FirstReply;
import pojo.Image;
import pojo.Posts;
import pojo.SecondReply;
import service.FirstReplyService;
import service.ImageService;
import service.PostsService;
import service.SecondReplyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet("/postDetailServlet")
public class PostDetailServlet extends HttpServlet {
  FirstReplyService firstReplyService = new FirstReplyService();
  PostsService postsService = new PostsService();
  ImageService imageService = new ImageService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String postID = req.getParameter("postID");
    System.out.println(postID);
    Image image = imageService.selectByName(Integer.parseInt(postID));
    if (image != null) {
    String base64Image = Base64.getEncoder().encodeToString(image.getImagedata());
    req.setAttribute("image", base64Image);
    }
    else {
      System.out.println("null");
    }
    List<FirstReply> firstReplyList = firstReplyService.selectByid(Integer.parseInt(postID));
    Posts posts = postsService.selectByID(Integer.parseInt(postID));
    req.setAttribute("firstReplyList", firstReplyList);
    req.setAttribute("posts", posts);
    req.getRequestDispatcher("/postDetail.jsp").forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
