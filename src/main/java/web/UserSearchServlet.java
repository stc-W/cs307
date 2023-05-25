package web;

import pojo.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/userSearchServlet")
public class UserSearchServlet extends HttpServlet {
  UserService userService = new UserService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("searchname");
    HttpSession session = req.getSession();
    System.out.println(session);
  
    req.setAttribute("username",username);
    List<String> usernames = userService.selectMohu(username);
    req.setAttribute("usernames", usernames);
    req.getRequestDispatcher("/userSearch.jsp").forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
