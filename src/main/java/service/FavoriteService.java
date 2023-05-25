package service;

import mapper.FavoriteMapper;
import mapper.ShareMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Favorite;
import pojo.Share;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class FavoriteService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public void add(Favorite favorite){
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FavoriteMapper favoriteMapper = sqlSession.getMapper(FavoriteMapper.class);
    //2.4 调用方法
    favoriteMapper.add(favorite);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public List<Favorite> selectByAuthor(String name) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FavoriteMapper favoriteMapper = sqlSession.getMapper(FavoriteMapper.class);
    //2.4 调用方法
    List<Favorite> favorites = favoriteMapper.selectByAuthor(name);
    //2.5 释放资源
    sqlSession.close();
    return favorites;
  }
}
