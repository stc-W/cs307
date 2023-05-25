package service;

import mapper.FirstReplyMapper;
import mapper.LikerMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.FirstReply;
import pojo.Liker;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class FirstReplyService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public void add(FirstReply firstReply) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FirstReplyMapper  firstReplyMapper = sqlSession.getMapper(FirstReplyMapper.class);
    //2.4 调用方法
    firstReplyMapper.add(firstReply);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public List<FirstReply> selectByid(int id) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FirstReplyMapper  firstReplyMapper = sqlSession.getMapper(FirstReplyMapper.class);
    //2.4 调用方法
    List<FirstReply> F = firstReplyMapper.selectByPostID(id);
    //2.5 释放资源
    sqlSession.close();
    return F;
  }
  
  public List<FirstReply> selectByIdFirstReplies(String name) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FirstReplyMapper  firstReplyMapper = sqlSession.getMapper(FirstReplyMapper.class);
    //2.4 调用方法
    List<FirstReply> F = firstReplyMapper.selectByIdFirstReplies(name);
    //2.5 释放资源
    sqlSession.close();
    return F;
  }
  
  public FirstReply selectById(int id) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    FirstReplyMapper  firstReplyMapper = sqlSession.getMapper(FirstReplyMapper.class);
    //2.4 调用方法
    FirstReply F = firstReplyMapper.selectById(id);
    //2.5 释放资源
    sqlSession.close();
    return F;
  }
}
