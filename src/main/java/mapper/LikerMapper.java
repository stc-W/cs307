package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import pojo.Liker;

import java.util.List;

public interface LikerMapper {
  @Insert("insert into liker (post_id, author_liked) values (#{postID}, #{authorLiked})  ON DUPLICATE KEY UPDATE NOTHING")
  void add(Liker liker);
  
  @Select("select * from liker where author_liked = #{name}")
  @ResultMap("likerResultMap")
  List<Liker> selectByAuthor(@Param("name") String name);
  
  @Select("select * from liker")
  @ResultMap("likerResultMap")
  List<Liker> selectAll();
}
