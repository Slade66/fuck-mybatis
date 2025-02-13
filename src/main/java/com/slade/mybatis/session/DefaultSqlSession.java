package com.slade.mybatis.session;

import com.slade.mybatis.config.Configuration;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        return List.of();
    }

    @Override
    public <E> E selectOne(String statementId, Object... params) throws Exception {
        return null;
    }

    @Override
    public <E> E insert(String statementId, Object... params) throws Exception {
        return null;
    }

    @Override
    public <E> E update(String statementId, Object... params) throws Exception {
        return null;
    }

    @Override
    public <E> E delete(String statementId, Object... params) throws Exception {
        return null;
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) throws Exception {
        Object instance = Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." + methodName;
                Type returnType = method.getGenericReturnType();

                if (methodName.contains(CommandType.INSERT.toString())) {
                    return insert(statementId, args);
                } else if (methodName.contains(CommandType.DELETE.toString())) {
                    return delete(statementId, args);
                } else if (statementId.contains(CommandType.UPDATE.toString())) {
                    return update(statementId, args);
                }

                if (returnType instanceof ParameterizedType) {
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                } else {
                    return selectOne(statementId, args);
                }
            }
        });
        return (T) instance;
    }

}
