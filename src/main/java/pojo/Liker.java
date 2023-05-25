package pojo;

public class Liker {
  private int postID;
  private String authorLiked;
  
  public Liker(int postID, String s) {
    postID = postID;
    authorLiked = s;
  }
  
  public Liker() {
  
  }
  
  @Override
  public String toString() {
    return "Liker{" +
      "PostID='" + postID + '\'' +
      ", authorLiked='" + authorLiked + '\'' +
      '}';
  }
  
  public int getPostID() {
    return postID;
  }
  
  public void setPostID(int postID) {
    this.postID = postID;
  }
  
  public String getAuthorLiked() {
    return authorLiked;
  }
  
  public void setAuthorLiked(String authorLiked) {
    this.authorLiked = authorLiked;
  }
  
  
}
