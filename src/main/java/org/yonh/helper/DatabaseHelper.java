package org.yonh.helper;


//import org.slf4j.Logger;
//import org.yonh.util.StringUtil;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yonh.util.CollectionUtil;
import org.yonh.util.PropertiesUtil;

/**
 * 数据库操作助手类
 */

public final class DatabaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>();
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties properties = PropertiesUtil.loadProperties("jdbc.properties");
        DRIVER = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        USERNAME = properties.getProperty("jdbc.username");
        PASSWORD = properties.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver");
        }
    }

    /**
     * 获取Connection对象
     * <p>
     * 每次获取Connection对象的时候,首先在ThreadLocal寻找,若不存在,则创建,并将其放入ThreadLocal中
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = CONNECTION_THREAD_LOCAL.get();
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(conn);
            }
        }

        return conn;
    }

    /**
     * 关闭Connection对象
     * <p>
     * 使用完毕connection对象,需要将其从ThreadLocal中移除
     */
    public static void closeConnection() {
        Connection conn = CONNECTION_THREAD_LOCAL.get();

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }

    /**
     * 查询实体列表
     *
     * @param entityClass 实体
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {

        List<T> entityList;
        Connection conn = null;
        try {
            conn = getConnection();
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return entityList;
    }

    /**
     * 查询实体
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;
        try {

            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return entity;
    }

    /**
     * 执行查询语句
     *
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result;
        try {
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            LOGGER.error("execute query failure", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 执行更新语句 (update, insert, delete)
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException("execute update failure", e);
        } finally {
            closeConnection();
        }

        return rows;
    }


}
