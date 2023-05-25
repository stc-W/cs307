package web;

import service.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deletePostServlet")
public class DeletePostServlet extends HttpServlet {
  PostsService postsService = new PostsService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      int id = Integer.parseInt(req.getParameter("postID"));
      postsService.delete(id);
      HttpSession session = req.getSession();
      String name = (String) session.getAttribute("username");
      req.getRequestDispatcher("homepageServlet?name="+name).forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}