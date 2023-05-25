package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pojo.Image;

public interface ImageMapper {
  @Select("select * from images where postid = #{id}")
  Image selectByID(@Param("id") int id);
  
  @Insert("insert into images (postid, imagedata) values (#{postid},#{imagedata})")
  void add(Image image);
  
  @Update("update images set imagedata = #{imagedata} where postid = #{postid}")
  void updatePostID(Image image);
}
