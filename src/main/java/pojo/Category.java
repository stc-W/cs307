package pojo;

public class Category {
  private int postID;
  private String category;
  
  public Category(int postID, String category) {
    this.postID = postID;
    this.category = category;
  }
  
  @Override
  public String toString() {
    return "Category{" +
      "postID='" + postID + '\'' +
      ", category='" + category + '\'' +
      '}';
  }
  
  public int getPostID() {
    return postID;
  }
  
  public void setPostID(int postID) {
    this.postID = postID;
  }
  
  public String getCategory() {
    return category;
  }
  
  public void setCategory(String category) {
    this.category = category;
  }
}
