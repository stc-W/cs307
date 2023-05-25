package util;

import pojo.LikeNode;

import java.util.HashMap;

public class Heap {
   public LikeNode[] heap;
  public int size;
  public Heap(int capacity) {
    heap = new LikeNode[capacity + 1];
    size = 0;
  }
  
  
  
  public void insert(LikeNode likeNode, HashMap<Integer, Integer> add) {
    size++;
    heap[size] = likeNode;
    add.put(likeNode.postID, size);
    int i = size;
    while (i > 1) {
      int parent = i / 2;
      if (heap[parent].num > heap[i].num) {
        add.put(heap[parent].postID, i);
        add.put(heap[i].postID, parent);
        LikeNode temp = heap[parent];
        heap[parent] = heap[i];
        heap[i] = temp;
        i = parent;
      } else {
        break;
      }
    }
  }
  public void insert2(LikeNode likeNode) {
    size++;
    heap[size] = likeNode;
    int i = size;
    while (i > 1) {
      int parent = i / 2;
      if (heap[parent].num > heap[i].num) {
        LikeNode temp = heap[parent];
        heap[parent] = heap[i];
        heap[i] = temp;
        i = parent;
      } else {
        break;
      }
    }
  }
  public LikeNode delete(HashMap<Integer, Integer> add) {
    LikeNode result = heap[1];
    add.remove(heap[1].postID);
    heap[1] = heap[size];
    size--;
    int i = 1;
    while (i < size) {
        int left = 2 * i;
        int right = 2 * i + 1;
        if (left >= size) {
          break;
        }
        if (right >= size) {
        if (heap[left].num < heap[i].num) {
          add.put(heap[left].postID, i);
          add.put(heap[i].postID, left);
          LikeNode temp = heap[left];
          heap[left] = heap[i];
          heap[i] = temp;
          i = left;
        } else {
          break;
        }
      }
      if (heap[left].num < heap[right].num) {
        if (heap[left].num < heap[i].num) {
          add.put(heap[left].postID, i);
          add.put(heap[i].postID, left);
          LikeNode temp = heap[left];
          heap[left] = heap[i];
          heap[i] = temp;
          i = left;
        } else {
          break;
        }
      } else {
        if (heap[right].num < heap[i].num) {
          add.put(heap[right].postID, i);
          add.put(heap[i].postID, right);
          LikeNode temp = heap[right];
          heap[right] = heap[i];
          heap[i] = temp;
          i = right;
        } else {
          break;
        }
      }
    }
    return result;
  }
  
  public LikeNode delete2() {
    LikeNode result = heap[1];
  
    heap[1] = heap[size];
    size--;
    int i = 1;
    while (i < size) {
      int left = 2 * i;
      int right = 2 * i + 1;
      if (left >= size) {
        break;
      }
      if (right >= size) {
        if (heap[left].num < heap[i].num) {
          
          LikeNode temp = heap[left];
          heap[left] = heap[i];
          heap[i] = temp;
          i = left;
        } else {
          break;
        }
      }
      if (heap[left].num < heap[right].num) {
        if (heap[left].num < heap[i].num) {
          
          LikeNode temp = heap[left];
          heap[left] = heap[i];
          heap[i] = temp;
          i = left;
        } else {
          break;
        }
      } else {
        if (heap[right].num < heap[i].num) {
          
          LikeNode temp = heap[right];
          heap[right] = heap[i];
          heap[i] = temp;
          i = right;
        } else {
          break;
        }
      }
    }
    return result;
  }
}
