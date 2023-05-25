package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import pojo.Liker;
import pojo.Share;

import java.util.List;

public interface ShareMapper {
  @Insert("insert into share (post_id, author_share) values (#{postID}, #{authorShared})  ON DUPLICATE KEY UPDATE NOTHING")
  void add(Share share);
  
  @Select("select * from share where author_share = #{name}")
  @ResultMap("shareResultMap")
  List<Share> selectByAuthor(@Param("name") String name);
}
