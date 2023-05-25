package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import pojo.Favorite;
import pojo.Liker;

import java.util.List;

public interface FavoriteMapper {
  @Insert("insert into favorite (post_id, author_favorite) values (#{postID}, #{authorFavorite})  ON DUPLICATE KEY UPDATE NOTHING")
  void add(Favorite favorite);
  
  @Select("select * from favorite where author_favorite = #{name}")
  @ResultMap("favoriteResultMap")
  List<Favorite> selectByAuthor(@Param("name") String name);
}
