package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Posts;

import java.util.List;

public interface PostsMapper {
    
    @Select("select * from post where author_Name = #{authorName}")
    @ResultMap("postsResultMap")
    List<Posts> selectByauthorName(@Param("authorName") String authorName);
    @Select("select * from post where id = #{id}")
    @ResultMap("postsResultMap")
    Posts selectByID(@Param("id") int id);
    @Select("select * from post where title like '%' || #{title} || '%' or content like '%' || #{title} || '%' or category like '%' || #{title} || '%' or author_Name like '%' || #{title} || '%'")
    @ResultMap("postsResultMap")
    List<Posts> selectMoHu(@Param("title") String title);
    
    @Options(useGeneratedKeys = true, keyColumn ="id" ,keyProperty = "postID")
    @ResultMap("postsResultMap")
    @Insert("insert into post (title, content, posting_time, posting_country, posting_city, category, author_name) values (#{title},#{contents},#{postingTime},#{postingCountry},#{postingCity},#{category},#{authorName})")
    void add(Posts posts);
    
    @Select("select * from post")
    @ResultMap("postsResultMap")
    List<Posts> selectAll();
    
    @Delete("delete from post where id = #{id}")
    void delete(@Param("id") int id);
    
    @Update("update post set title = #{title}, content = #{contents}, posting_time = #{postingTime}, posting_country = #{postingCountry}, posting_city = #{postingCity}, category = #{category} where id = #{postID}")
    void updatePostID(Posts posts);
}
