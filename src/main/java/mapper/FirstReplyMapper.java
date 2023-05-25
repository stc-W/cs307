package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import pojo.FirstReply;

import java.util.List;

public interface FirstReplyMapper {

  @Insert("insert into first_reply  (contents, author, post_id) values (#{contents}, #{firstReplyID}, #{postID})")
  void add(FirstReply firstReply);
  
  @Select("select * from first_reply where post_id = #{id}")
  @ResultMap("firstReplyResultMap")
  List<FirstReply> selectByPostID(@Param("id") int id);
  
  @Select("select * from first_reply where author = #{name}")
  @ResultMap("firstReplyResultMap")
  List<FirstReply> selectByIdFirstReplies(@Param("name") String name);
  
  @Select("select * from first_reply where first_reply_id = #{id}")
  @ResultMap("firstReplyResultMap")
  FirstReply selectById(@Param("id") int id);
}
