package service;

import mapper.ImageMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Image;
import util.SqlSessionFactoryUtils;

public class ImageService {
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
  public void add(Image image) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    ImageMapper imageMapper = sqlSession.getMapper(ImageMapper.class);
    //2.4 调用方法
    imageMapper.add(image);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
  public Image selectByName(int ID) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    ImageMapper imageMapper = sqlSession.getMapper(ImageMapper.class);
    //2.4 调用方法
    Image image = imageMapper.selectByID(ID);
    //2.5 释放资源
    sqlSession.close();
    return image;
  }
  public void updatePostID(Image image) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //2.3 获取Mapper
    ImageMapper imageMapper = sqlSession.getMapper(ImageMapper.class);
    //2.4 调用方法
    imageMapper.updatePostID(image);
    sqlSession.commit();
    //2.5 释放资源
    sqlSession.close();
  }
}
