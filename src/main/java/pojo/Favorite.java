package pojo;

public class Favorite {
  private int postID;
  private String authorFavorite;
  
  public Favorite(int postID, String s) {
    this.postID = postID;
    this.authorFavorite = s;
  }
  
  public Favorite() {
  
  }
  
  @Override
  public String toString() {
    return "Favorite{" +
      "postID='" + postID + '\'' +
      ", authorFavorite='" + authorFavorite + '\'' +
      '}';
  }
  
  public int getPostID() {
    return postID;
  }
  
  public void setPostID(int postID) {
    this.postID = postID;
  }
  
  public String getAuthorFavorite() {
    return authorFavorite;
  }
  
  public void setAuthorFavorite(String authorFavorite) {
    this.authorFavorite = authorFavorite;
  }
}
