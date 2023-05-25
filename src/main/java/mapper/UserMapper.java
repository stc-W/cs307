package mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import pojo.User;

import java.util.List;

public interface UserMapper {
  @Select("select * from author where name = #{username} and password = #{password}")
  User select(@Param("username") String username, @Param("password") String password);
  @Select("select * from author where name = #{username}")
  @ResultMap("userResultMap")
  User selectByUsername(@Param("username") String username);
  @Insert("insert into author (name, password, phone,registration_time) values(#{username},#{password},#{phoneNumber},#{registrationTime})")
   void add(User user);
  @Select("select name from author where name like '%' || #{username} || '%'")
  List<String> selectMohu(@Param("username") String username);
}
