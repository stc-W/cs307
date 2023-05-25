package pojo;

public class Share {
  private int postID;
  private String authorShared;
  
  public Share(int postID, String authorShared) {
    this.postID = postID;
    this.authorShared = authorShared;
  }
  
  public Share() {
  
  }
  
  @Override
  public String toString() {
    return "Share{" +
      "PostID='" + postID + '\'' +
      ", authorShared='" + authorShared + '\'' +
      '}';
  }
  
  public int getPostID() {
    return postID;
  }
  
  public void setPostID(int postID) {
    this.postID = postID;
  }
  
  public String getAuthorShared() {
    return authorShared;
  }
  
  public void setAuthorShared(String authorShared) {
    this.authorShared = authorShared;
  }
}
