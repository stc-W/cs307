package service;

import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.User;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class UserService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public User select(String username, String password) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    //2.4 调用方法
    User user = userMapper.select(username, password);
    //2.5 释放资源
    sqlSession.close();
    return user;
  }
  public User selectByUsername(String username) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    //2.4 调用方法
    User user = userMapper.selectByUsername(username);
    //2.5 释放资源
    sqlSession.close();
    return user;
  }
  public void add(User user) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    //2.4 调用方法
    userMapper.add(user);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public List<String> selectMohu(String username) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    //2.4 调用方法
    List<String> users = userMapper.selectMohu(username);
    //2.5 释放资源
    sqlSession.close();
    return users;
  }
}
