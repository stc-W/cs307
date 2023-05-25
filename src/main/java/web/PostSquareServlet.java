package web;

import pojo.Posts;
import service.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/postSquareServlet")
public class PostSquareServlet extends HttpServlet {
  PostsService postsService = new PostsService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Posts> postsList = postsService.selectAll();
    req.setAttribute("postsList", postsList);
    req.getRequestDispatcher("postlist.jsp").forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
