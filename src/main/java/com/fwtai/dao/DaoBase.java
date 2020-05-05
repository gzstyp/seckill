package com.fwtai.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * dao底层操作处理工具类
 */
@Repository
public class DaoBase{

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSession;

    public Integer execute(final String sqlMapId,final Object objParam){
        return sqlSession.update(sqlMapId,objParam);
    }

    /**
     * 用于查询返回String
     */
    public String queryForString(final String sqlMapId){
        return sqlSession.selectOne(sqlMapId);
    }

    /**
     * 用于查询返回String
     */
    public String queryForString(final String sqlMapId,final Object objParam){
        return sqlSession.selectOne(sqlMapId,objParam);
    }

    public Integer queryForInteger(final String sqlMapId,final Object objParam){
        return sqlSession.selectOne(sqlMapId,objParam);
    }

    public Integer queryForInteger(final String sqlMapId){
        return sqlSession.selectOne(sqlMapId);
    }

    public Long queryForLong(final String sqlMapId){
        return sqlSession.selectOne(sqlMapId);
    }

    /**
     * 查询返回List《HashMap<String,String>》-ok
     */
    public List<HashMap<String,String>> queryForListHashMapString(final String sqlMapId,final Object objParam){
        return sqlSession.selectList(sqlMapId,objParam);
    }

    /**
     * 查询返回List《HashMap<String,Object>》-ok
     */
    public List<HashMap<String,Object>> queryForListHashMap(final String sqlMapId,final Object objParam){
        return sqlSession.selectList(sqlMapId,objParam);
    }

    /**
     * 查询返回List《HashMap<String,Object>》-ok
     */
    public <T> List<T> queryForListBean(final String sqlMapId,final Object objParam){
        return sqlSession.selectList(sqlMapId,objParam);
    }
}