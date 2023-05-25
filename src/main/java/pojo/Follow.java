package pojo;

public class Follow {
  private String authorName;
  private String authorFollowedBy;
  
  public Follow(String authorName, String s) {
    this.authorName = authorName;
    this.authorFollowedBy = s;
  }
  
  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
  
  @Override
  public String toString() {
    return "Follow{" +
      "AuthorID='" + authorName + '\'' +
      ", authorFollowedBy='" + authorFollowedBy + '\'' +
      '}';
  }
  
  public String getAuthorName() {
    return authorName;
  }
  
  public void setAuthorID(String authorID) {
    this.authorName = authorID;
  }
  
  public String getAuthorFollowedBy() {
    return authorFollowedBy;
  }
  
  public void setAuthorFollowedBy(String authorFollowedBy) {
    this.authorFollowedBy = authorFollowedBy;
  }
}
