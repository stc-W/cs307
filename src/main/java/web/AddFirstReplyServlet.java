package web;

import pojo.FirstReply;
import pojo.Image;
import service.FirstReplyService;
import service.ImageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/addFirstReplyServlet")
public class AddFirstReplyServlet extends HttpServlet {
  FirstReplyService firstReplyService = new FirstReplyService();
  ImageService imageService = new ImageService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    String postID = req.getParameter("postID");
    req.setAttribute("postID", postID);
    HttpSession session = req.getSession();
    System.out.println(session);
    String authorName = (String) session.getAttribute("username");
    System.out.println("first name"+authorName+session);
    String content = req.getParameter("content");
    FirstReply firstReply = new FirstReply();
    firstReply.setFirstReplyID(authorName);
    firstReply.setContents(content);
    firstReply.setPostID(Integer.parseInt(postID));
    firstReplyService.add(firstReply);
    req.getRequestDispatcher("/postDetailServlet?postID=" + postID).forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
