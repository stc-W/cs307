package web;

import pojo.*;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/homepageServlet")
public class HomepageServlet extends HttpServlet {
  PostsService postsService = new PostsService();
  UserService userService = new UserService();
  FollowService followService = new FollowService();
  LikerService likerService = new LikerService();
  ShareService shareService = new ShareService();
  FavoriteService favoriteService = new FavoriteService();
  FirstReplyService firstReplyService = new FirstReplyService();
  SecondReplyService secondReplyService = new SecondReplyService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    System.out.println(session);
    String username = (String) session.getAttribute("username");
    String others = req.getParameter("name");
    User user = null;
    if (others != null) {
       user = userService.selectByUsername(others);
    } else {
      user = userService.selectByUsername(username);
    }
    System.out.println(username);
    List<FirstReply> firstReplyList = firstReplyService.selectByIdFirstReplies(user.getUsername());
    List<SecondReply> secondReplyList = secondReplyService.selectByIdSecondReplies(user.getUsername());
    
    List<Favorite> favoriteidList = favoriteService.selectByAuthor(user.getUsername());
    List<Share> shareidList = shareService.selectByAuthor(user.getUsername());
    List<Liker> likeridList = likerService.selectByAuthor(user.getUsername());
    List<Posts> likerList = new ArrayList<>();
    List<Posts> shareList = new ArrayList<>();
    List<Posts> favoriteList = new ArrayList<>();
    List<Posts> postsList = postsService.selectByName(user.getUsername());
    List<Follow> IFollowList = followService.selectIFollow(user.getUsername());
    List<Follow> FollowMeList = followService.selectFollowI(user.getUsername());
    for (int i = 0; i< likeridList.size(); i++) {
      Posts posts = postsService.selectByID(likeridList.get(i).getPostID());
      likerList.add(posts);
    }
    for (int i = 0; i< shareidList.size(); i++) {
      Posts posts = postsService.selectByID(shareidList.get(i).getPostID());
      shareList.add(posts);
    }
    for (int i = 0; i< favoriteidList.size(); i++) {
      Posts posts = postsService.selectByID(favoriteidList.get(i).getPostID());
      favoriteList.add(posts);
    }
    req.setAttribute("firstReplyList", firstReplyList);
    req.setAttribute("secondReplyList", secondReplyList);
    req.setAttribute("favoriteList", favoriteList);
    req.setAttribute("shareList", shareList);
    req.setAttribute("likerList", likerList);
    req.setAttribute("FollowMeList", FollowMeList);
    req.setAttribute("IFollowList", IFollowList);
    req.setAttribute("postsList", postsList);
    req.setAttribute("user", user);
    req.setAttribute("username",username);
    System.out.println(user);
    req.getRequestDispatcher("/homepage.jsp").forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
