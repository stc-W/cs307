package service;

import mapper.LikerMapper;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Liker;
import pojo.User;
import util.SqlSessionFactoryUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class LikerService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  
  public void add(Liker liker) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    LikerMapper userMapper = sqlSession.getMapper(LikerMapper.class);
    //2.4 调用方法
    userMapper.add(liker);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public List<Liker> selectByAuthor(String name) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    LikerMapper userMapper = sqlSession.getMapper(LikerMapper.class);
    //2.4 调用方法
    List<Liker> likers = userMapper.selectByAuthor(name);
    //2.5 释放资源
    sqlSession.close();
    return likers;
  }
  
  public List<Liker> selectAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    LikerMapper userMapper = sqlSession.getMapper(LikerMapper.class);
    //2.4 调用方法
    List<Liker> likers = userMapper.selectAll();
    //2.5 释放资源
    sqlSession.close();
    return likers;
  }
  
}
