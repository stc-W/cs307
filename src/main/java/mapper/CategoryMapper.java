package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import pojo.Category;

import java.util.List;

public interface CategoryMapper {
  @Select("select * from category where category = '%' || #{s} || '%' ")
  @ResultMap("categoryResultMap")
  List<Category> selectByCate(@Param("s") String s);
}
