package service;

import mapper.LikerMapper;
import mapper.ShareMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Liker;
import pojo.Share;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class ShareService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public void add(Share share){
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    ShareMapper shareMapper = sqlSession.getMapper(ShareMapper.class);
    //2.4 调用方法
    shareMapper.add(share);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public List<Share> selectByAuthor(String name) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    ShareMapper shareMapper = sqlSession.getMapper(ShareMapper.class);
    //2.4 调用方法
    List<Share> Shares = shareMapper.selectByAuthor(name);
    //2.5 释放资源
    sqlSession.close();
    return Shares;
  }
}
