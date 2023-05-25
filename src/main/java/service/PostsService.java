package service;

import mapper.PostsMapper;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Posts;
import pojo.User;
import util.SqlSessionFactoryUtils;

import java.util.ArrayList;
import java.util.List;

public class PostsService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public List<Posts> selectByName(String username) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
    //2.4 调用方法
    List<Posts> postsArrayList = postsMapper.selectByauthorName(username);
    //2.5 释放资源
    sqlSession.close();
    return postsArrayList;
  }
  public List<Posts> selectMohu(String title) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
    //2.4 调用方法
    List<Posts> postsArrayList = postsMapper.selectMoHu(title);
    //2.5 释放资源
    sqlSession.close();
    return postsArrayList;
  }
  public Posts selectByID(int id) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
    //2.4 调用方法
    Posts postsArrayList = postsMapper.selectByID(id);
    //2.5 释放资源
    sqlSession.close();
    return postsArrayList;
  }
  public void add(Posts posts) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
    //2.4 调用方法
    postsMapper.add(posts);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public List<Posts> selectAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
    //2.4 调用方法
    List<Posts> postsArrayList = postsMapper.selectAll();
    //2.5 释放资源
    sqlSession.close();
    return postsArrayList;
  }
  public void delete(int id) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
    //2.4 调用方法
    postsMapper.delete(id);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public void updatePostID(Posts posts) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
    //2.4 调用方法
    postsMapper.updatePostID(posts);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
}
