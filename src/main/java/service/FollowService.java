package service;

import mapper.FollowMapper;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Follow;
import pojo.User;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class FollowService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public List<Follow> selectIFollow(String username) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FollowMapper followMapper = sqlSession.getMapper(FollowMapper.class);
    //2.4 调用方法
    List<Follow> follows = followMapper.selectIFollowedByName(username);
    //2.5 释放资源
    sqlSession.close();
    return follows;
  }
  public List<Follow> selectFollowI(String username) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FollowMapper followMapper = sqlSession.getMapper(FollowMapper.class);
    //2.4 调用方法
    List<Follow> follows = followMapper.selectFollowedIByName(username);
    //2.5 释放资源
    sqlSession.close();
    return follows;
  }
  public void add(Follow follow) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FollowMapper followMapper = sqlSession.getMapper(FollowMapper.class);
    //2.4 调用方法
    followMapper.add(follow);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public void delete(String name, String name1) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FollowMapper followMapper = sqlSession.getMapper(FollowMapper.class);
    //2.4 调用方法
    followMapper.delete(name, name1);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
}
