package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteFollowServlet")
public class DeleteFollowServlet extends HttpServlet {
  service.FollowService followService = new service.FollowService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String authorfollowername = (String) session.getAttribute("username");
    String authorname = req.getParameter("authorname");
    followService.delete(authorfollowername, authorname);
    req.getRequestDispatcher("/homepageServlet?name=" + authorfollowername).forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
