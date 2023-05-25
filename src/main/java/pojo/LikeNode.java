package pojo;

public class LikeNode {
  public int postID;
  public int num;
  
  @Override
  public String toString() {
    return "LikeNode{" +
      "postID=" + postID +
      ", num=" + num +
      '}';
  }
  
  public LikeNode() {
  }
  
  public int getPostID() {
    return postID;
  }
  
  public void setPostID(int postID) {
    this.postID = postID;
  }
  
  public int getNum() {
    return num;
  }
  
  public void setNum(int num) {
    this.num = num;
  }
}
