package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Follow;

import java.util.List;

public interface FollowMapper {
  @Select("select * from follow where author_name = #{username}")
  @ResultMap("followResultMap")
  List<Follow> selectFollowedIByName(@Param("username") String username);
  @Select("select * from follow where author_followed = #{username}")
  @ResultMap("followResultMap")
  List<Follow> selectIFollowedByName(@Param("username") String username);
  
  @Insert("insert into follow(author_name, author_followed) values(#{authorName}, #{authorFollowedBy}) ON DUPLICATE KEY UPDATE NOTHING")
  void add(Follow follow);
  
  @Delete("delete from follow where author_followed = #{name} and author_name = #{name2}")
  void delete(@Param("name") String name, @Param("name2") String name2);
}
