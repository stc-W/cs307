package web;

import pojo.FirstReply;
import pojo.SecondReply;
import service.FirstReplyService;
import service.SecondReplyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/addSecondReplyServlet")
public class AddSecondReplyServlet extends HttpServlet {
  SecondReplyService secondReplyService = new SecondReplyService();
  FirstReplyService firstReplyService = new FirstReplyService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    int id = Integer.parseInt(req.getParameter("fatherid"));
    List<SecondReply> secondReplyList = secondReplyService.selectByid(id);
    FirstReply firstReply = firstReplyService.selectById(id);
    req.setAttribute("secondReplyList", secondReplyList);
    req.setAttribute("id", id);
    req.setAttribute("firstReply", firstReply);
    req.getRequestDispatcher("/secondReply.jsp").forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
