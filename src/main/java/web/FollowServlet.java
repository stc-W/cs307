package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/followServlet")
public class FollowServlet extends HttpServlet {
  service.FollowService followService = new service.FollowService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String authorname = req.getParameter("authorname");
      HttpSession session = req.getSession();
      String authorfollowername = (String) session.getAttribute("username");
      String page = req.getParameter("page");
      pojo.Follow follow = new pojo.Follow(authorname, authorfollowername);
      followService.add(follow);
      if (page.equals("homepage")) {
        req.getRequestDispatcher("/homepageServlet?name=" + authorname).forward(req, resp);
      } else if (page.equals("userSearch")) {
        String S = req.getParameter("S");
        req.getRequestDispatcher("/userSearchServlet?searchname="+S).forward(req, resp);
      }
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
