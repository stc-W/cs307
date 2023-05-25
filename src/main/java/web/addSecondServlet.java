package web;

import pojo.SecondReply;
import service.SecondReplyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addSecondServlet")
public class addSecondServlet extends HttpServlet {
  SecondReplyService secondReplyService = new SecondReplyService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
   
    HttpSession session = req.getSession();
    System.out.println(session);
    String name = (String) session.getAttribute("username");
    System.out.println("second name"+name);
    String content = req.getParameter("content");
    int id = Integer.parseInt(req.getParameter("fatherid"));
    SecondReply secondReply = new SecondReply();
    secondReply.setSecondReplyID(name);
    secondReply.setFatherID(id);
    secondReply.setContents(content);
    secondReplyService.add(secondReply);
    req.getRequestDispatcher("addSecondReplyServlet?fatherid="+id).forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
