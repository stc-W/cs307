package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import pojo.FirstReply;
import pojo.SecondReply;

import java.util.List;

public interface SecondReplyMapper {
  @Insert("insert into second_reply  (contents, author, father_id) values (#{contents}, #{secondReplyID}, #{fatherID})")
  void add(SecondReply secondReply);
  
  @Select("select * from second_reply where father_id = #{id}")
  @ResultMap("secondReplyResultMap")
  List<SecondReply> selectByFatherID(@Param("id") int id);
  
  @Select("select * from second_reply where author = #{name}")
  @ResultMap("secondReplyResultMap")
  List<SecondReply> selectByIdSecondReplies(@Param("name") String name);
}
