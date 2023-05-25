package pojo;

import java.util.Arrays;

public class Image {
  
  private int postid;
  private byte[] imagedata;
  
  // 构造函数、getter 和 setter 方法
  
  // 可选：其他属性和方法
  
  @Override
  public String toString() {
    return "Image{" +
      "postid=" + postid +
      ", imagedata=" + Arrays.toString(imagedata) +
      '}';
  }
  
  public Image() {
  }
  
  public int getPostid() {
    return postid;
  }
  
  public void setPostid(int postid) {
    this.postid = postid;
  }
  
  public byte[] getImagedata() {
    return imagedata;
  }
  
  public void setImagedata(byte[] imagedata) {
    this.imagedata = imagedata;
  }
}