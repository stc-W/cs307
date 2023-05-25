package pojo;

import java.util.Date;

public class Posts {
  private int postID;
  private String title;
  private String contents;
  private Date postingTime;
  private String postingCity;
  private String postingCountry;
  private String authorName;
  private String category;
  
  public String getCategory() {
    return category;
  }
  
  public void setCategory(String category) {
    this.category = category;
  }
  
  public String getAuthorName() {
    return authorName;
  }
  
  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
  
  public void setAuthorID(String authorName) {
    this.authorName = authorName;
  }
  
  public String getPostingCountry() {
    return postingCountry;
  }
  
  public void setPostingCountry(String postingCountry) {
    this.postingCountry = postingCountry;
  }
  
  public Posts() {
  }
  
  public Posts(String postingCountry, int postID, String title, String contents, Date postingTime, String postingCity, String authorName) {
    this.postID = postID;
    this.title = title;
    this.contents = contents;
    this.postingTime = postingTime;
    this.postingCity = postingCity;
    this.authorName = authorName;
    this.postingCountry = postingCountry;
  }
  
  @Override
  public String toString() {
    return "Posts{" +
      "postID=" + postID +
      ", title='" + title + '\'' +
      ", contents='" + contents + '\'' +
      ", postingTime=" + postingTime +
      ", postingCity='" + postingCity + '\'' +
      ", postingCountry='" + postingCountry + '\'' +
      ", authorID='" + authorName + '\'' +
      '}';
  }
  
  public int getPostID() {
    return postID;
  }
  
  public void setPostID(int postID) {
    this.postID = postID;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getContents() {
    return contents;
  }
  
  public void setContents(String contents) {
    this.contents = contents;
  }
  
  public Date getPostingTime() {
    return postingTime;
  }
  
  public void setPostingTime(Date postingTime) {
    this.postingTime = postingTime;
  }
  
  public String getPostingCity() {
    return postingCity;
  }
  
  public void setPostingCity(String postingCity) {
    this.postingCity = postingCity;
  }
}
