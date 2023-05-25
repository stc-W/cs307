package service;

import mapper.FirstReplyMapper;
import mapper.SecondReplyMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.FirstReply;
import pojo.SecondReply;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class SecondReplyService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public void add(SecondReply secondReply) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    SecondReplyMapper secondReplyMapper = sqlSession.getMapper(SecondReplyMapper.class);
    //2.4 调用方法
    secondReplyMapper.add(secondReply);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public List<SecondReply> selectByid(int id) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    SecondReplyMapper secondReplyMapper = sqlSession.getMapper(SecondReplyMapper.class);
    //2.4 调用方法
    List<SecondReply> s = secondReplyMapper.selectByFatherID(id);
    //2.5 释放资源
    sqlSession.close();
    return s;
  }
  
  public List<SecondReply> selectByIdSecondReplies(String name) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    SecondReplyMapper secondReplyMapper = sqlSession.getMapper(SecondReplyMapper.class);
    //2.4 调用方法
    List<SecondReply> s = secondReplyMapper.selectByIdSecondReplies(name);
    //2.5 释放资源
    sqlSession.close();
    return s;
  }
}
