package web;

import pojo.Favorite;
import pojo.Liker;
import service.FavoriteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/favoriteServlet")
public class FavoriteServlet extends HttpServlet {
  FavoriteService favoriteService = new FavoriteService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String username = (String) session.getAttribute("username");
    int postID = Integer.parseInt(req.getParameter("postID"));
    String page = req.getParameter("page");
    Favorite favorite = new Favorite();
    favorite.setAuthorFavorite(username);
    favorite.setPostID(postID);
    favoriteService.add(favorite);
    if (page.equals("homepage")) {
      String back =  req.getParameter("backname");
      req.getRequestDispatcher("/homepageServlet?name=" + back).forward(req, resp);
    } else if (page.equals("postSearch")) {
      String S = req.getParameter("S");
      req.getRequestDispatcher("/postSearchServlet?search="+S).forward(req, resp);
    } else if (page.equals("postlist")) {
      req.getRequestDispatcher("/postSquareServlet").forward(req, resp);
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   this.doGet(req, resp);
  }
}
