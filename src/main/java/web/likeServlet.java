package web;

import pojo.LikeNode;
import pojo.Liker;
import service.LikerService;
import util.Heap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/likeServlet")
public class likeServlet extends HttpServlet {
  LikerService likerService = new LikerService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String username = (String) session.getAttribute("username");
    int postID = Integer.parseInt(req.getParameter("postID"));
    String page = req.getParameter("page");
    Liker liker = new Liker();
    liker.setAuthorLiked(username);
    liker.setPostID(postID);
    likerService.add(liker);
    
    HashMap<Integer, Integer> likemap = (HashMap<Integer, Integer>) session.getAttribute("likemap");
    HashMap<Integer, Integer> add = (HashMap<Integer, Integer>) session.getAttribute("add");
    Heap heap = (Heap) session.getAttribute("heap");
    if (likemap.containsKey(postID)) {
      likemap.put(postID, likemap.get(postID) + 1);
    } else {
      likemap.put(postID, 1);
    }
    if (add.containsKey(postID)) {
      heap.heap[add.get(postID)].num = likemap.get(postID);
      int i = add.get(postID);
      while (i < heap.size) {
        int left = 2 * i;
        int right = 2 * i + 1;
        if (left >= heap.size) {
          break;
        }
        if (right >= heap.size) {
          if (heap.heap[left].num < heap.heap[i].num) {
            add.put(heap.heap[left].postID, i);
            add.put(heap.heap[i].postID, left);
            LikeNode temp = heap.heap[left];
            heap.heap[left] = heap.heap[i];
            heap.heap[i] = temp;
            i = left;
          } else {
            break;
          }
        }
        if (heap.heap[left].num < heap.heap[right].num) {
          if (heap.heap[left].num < heap.heap[i].num) {
            add.put(heap.heap[left].postID, i);
            add.put(heap.heap[i].postID, left);
            LikeNode temp = heap.heap[left];
            heap.heap[left] = heap.heap[i];
            heap.heap[i] = temp;
            i = left;
          } else {
            break;
          }
        } else {
          if (heap.heap[right].num < heap.heap[i].num) {
            add.put(heap.heap[right].postID, i);
            add.put(heap.heap[i].postID, right);
            LikeNode temp = heap.heap[right];
            heap.heap[right] = heap.heap[i];
            heap.heap[i] = temp;
            i = right;
          } else {
            break;
          }
        }
      }
  } else if (likemap.get(postID) >= heap.heap[1].num) {
      heap.delete(add);
      LikeNode likeNode = new LikeNode();
      likeNode.postID = postID;
      likeNode.num = likemap.get(postID);
      heap.insert(likeNode, add);
    }
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
