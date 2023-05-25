package web;

import mapper.CategoryMapper;
import pojo.Category;
import pojo.Posts;
import service.CategoryService;
import service.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/postSearchServlet")
public class PostSearchServlet extends HttpServlet {
  PostsService postsService = new PostsService();
  CategoryService categoryService = new CategoryService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    String S = req.getParameter("search");
    HttpSession session = req.getSession();
    System.out.println(session);
    String username = (String) session.getAttribute("username");
    req.setAttribute("username",username);
    req.setAttribute("S",S);
    List<Posts> postsList = postsService.selectMohu(S);
    req.setAttribute("postsList",postsList);
    req.getRequestDispatcher("/postSearch.jsp").forward(req,resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
