package web;

import pojo.LikeNode;
import pojo.Posts;
import service.PostsService;
import util.Heap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/hotLikePostServlet")
public class HotLikePostServlet extends HttpServlet {
  PostsService postsService = new PostsService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    Heap heap = (Heap) session.getAttribute("heap");
    System.out.println(heap);
    Heap heap1 = new Heap(20);
    for (int i = 1; i < 11; i++) {
      heap1.insert2(heap.heap[i]);
    }
    LikeNode[] a = new LikeNode[11];
    ArrayList<LikeNode> hotLikePost = new ArrayList<>();
    for (int i = 9; i >= 0; i--) {
     a[i] = heap1.delete2();
    }
    for (int i = 0; i < 10; i++) {
      hotLikePost.add(a[i]);
    }
    ArrayList<Posts> posts = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      posts.add(postsService.selectByID(hotLikePost.get(i).postID));
    }
    req.setAttribute("hotLikePost", hotLikePost);
    
    req.setAttribute("posts", posts);
    req.getRequestDispatcher("hotlikepost.jsp").forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doGet(req, resp);
  }
}
