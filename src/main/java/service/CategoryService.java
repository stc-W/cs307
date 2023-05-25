package service;

import mapper.CategoryMapper;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Category;
import pojo.User;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class CategoryService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public List<Category> selectByS(String s) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
    //2.4 调用方法
    List<Category> category = categoryMapper.selectByCate(s);
    //2.5 释放资源
    sqlSession.close();
    return category;
  }
}
