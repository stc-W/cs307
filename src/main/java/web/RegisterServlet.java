package web;

import pojo.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/registerservlet")
public class RegisterServlet extends HttpServlet {
  UserService userService = new UserService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //1. 接收用户数据
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String phone = req.getParameter("phonenumber");
    Date date = new Date(System.currentTimeMillis());
    //封装用户对象
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setPhoneNumber(phone);
    user.setRegistrationTime(date);
    User u = userService.selectByUsername(username);
  
    //3. 判断用户对象释放为null
    if (u == null) {
      userService.add(user);
    // 提交事务
    req.getRequestDispatcher("/registerjump.html").forward(req, resp);
    } else {
// 用户名存在，给出提示信息
    resp.setContentType("text/html;charset=utf-8");
    resp.getWriter().write("用户名已存在");
  }
  }
  @Override
  protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
