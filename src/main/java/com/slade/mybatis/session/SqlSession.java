package com.slade.mybatis.session;

import java.util.List;

public interface SqlSession {

    <E> List<E> selectList(String statementId, Object... params) throws Exception;

    <E> E selectOne(String statementId, Object... params) throws Exception;

    <E> E insert(String statementId, Object... params) throws Exception;

    <E> E update(String statementId, Object... params) throws Exception;

    <E> E delete(String statementId, Object... params) throws Exception;

    <T> T getMapper(Class<?> mapperClass) throws Exception;

}
