package web;


import pojo.LikeNode;
import pojo.Liker;
import pojo.User;
import service.LikerService;
import service.UserService;
import util.Heap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/loginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
  UserService userService = new UserService();
  LikerService likerService = new LikerService();
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    User user = userService.select(username, password);
    HttpSession session = req.getSession();
    List<Liker> likers = likerService.selectAll();
    HashMap<Integer, Integer> add = new HashMap<>();
    HashMap<Integer, Integer> likemap = new HashMap<>();
    for (int i = 0; i < likers.size(); i++) {
      if (likemap.containsKey(likers.get(i).getPostID())) {
        likemap.put(likers.get(i).getPostID(), likemap.get(likers.get(i).getPostID()) + 1);
      } else {
        likemap.put(likers.get(i).getPostID(), 1);
      }
    }
      Heap heap = new Heap(10);
      int cnt = 10;
      for (Map.Entry<Integer, Integer> entry : likemap.entrySet()) {
        if (cnt > 0) {
          LikeNode likeNode = new LikeNode();
          likeNode.postID = entry.getKey();
          likeNode.num = entry.getValue();
          heap.insert(likeNode, add);
          cnt--;
        } else {
          if (entry.getValue() > heap.heap[1].num) {
            heap.delete(add);
            LikeNode likeNode = new LikeNode();
            likeNode.postID = entry.getKey();
            likeNode.num = entry.getValue();
            heap.insert(likeNode, add);
          }
        }
      }
    
    
    //3. 判断user释放为null
    if(user != null){
      session.setAttribute("username", username);
  System.out.println(""+session);
      session.setAttribute("likemap", likemap);
      session.setAttribute("add", add);
      session.setAttribute("heap", heap);
      Heap heap1 = (Heap) session.getAttribute("heap");
      // 登陆成功
      req.getRequestDispatcher("loginac.html").forward(req, resp);
    } else {
      // 登陆失败
      req.getRequestDispatcher("loginer.html").forward(req, resp);
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}

