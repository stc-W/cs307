package pojo;

public class SecondReply {
  public SecondReply() {
  
  }
  
  @Override
  public String toString() {
    return "SecondReply{" +
      "fatherID='" + fatherID + '\'' +
      ", secondReplyID='" + secondReplyID + '\'' +
      ", stars='" + stars + '\'' +
      ", contents='" + contents + '\'' +
      '}';
  }
  
  public int getFatherID() {
    return fatherID;
  }
  
  public void setFatherID(int fatherID) {
    this.fatherID = fatherID;
  }
  
  public String getSecondReplyID() {
    return secondReplyID;
  }
  
  public void setSecondReplyID(String secondReplyID) {
    this.secondReplyID = secondReplyID;
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
  
  public SecondReply(String secondReplyID, int fatherID, int stars, String contents) {
    this.secondReplyID = secondReplyID;
    this.fatherID = fatherID;
    this.stars = stars;
    this.contents = contents;
  }
  
  private int fatherID;
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  private String secondReplyID;
  private int id;
  private int stars;
  private String contents;
}
