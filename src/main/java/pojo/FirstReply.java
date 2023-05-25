package pojo;

public class FirstReply {
  private int postID;
  private int stars;
  private String firstReplyID;
  private String contents;
  private int id;
  
  public String getFirstReplyID() {
    return firstReplyID;
  }
  
  public void setFirstReplyID(String firstReplyID) {
    this.firstReplyID = firstReplyID;
  }
  
  @Override
  public String toString() {
    return "FirstReply{" +
      "postID=" + postID +
      ", stars='" + stars + '\'' +
      ", contents='" + contents + '\'' +
      ", id=" + id +
      '}';
  }
  
  public FirstReply(int id, String firstReplyID,int postID, int stars, String contents) {
    this.id = id;
    this.firstReplyID = firstReplyID;
    this.postID = postID;
    this.stars = stars;
    this.contents = contents;
  }
  public FirstReply() {
  }
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getPostID() {
    return postID;
  }
  
  public void setPostID(int postID) {
    this.postID = postID;
  }
  
  public int getStars() {
    return stars;
  }
  
  public void setStars(int stars) {
    this.stars = stars;
  }
  
  public String getContents() {
    return contents;
  }
  
  public void setContents(String contents) {
    this.contents = contents;
  }
}
