package dao;

import po.JDBC;

import java.sql.*;

public class BaseDao {
    /**
     * 驱动类的全限定名
     */
    private static String driver = "com.mysql.jdbc.Driver";
    /**
     * 数据库连接字符串
     */
    private String url = "jdbc:mysql://127.0.0.1/project?characterEncoding=utf-8&autoReconnect=true&useSSL=false";
    /**
     * 用户名
     */
    private String user = "root";
    /**
     * 密码
     */
    private String pwd = "abc123";

    /**
     * 静态代码块
     * 加载驱动
     */
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载驱动，与数据库建立连接
     * @return
     * @throws SQLException
     */
    private Connection getConn() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url,user,pwd);
        return conn;
    }

    /**
     * 设置参数
     * @param ps
     * @param param
     * @throws SQLException
     */
    private void setParam(PreparedStatement ps, Object...param) throws SQLException {
        for (int i = 0; i < param.length; i++) {
            ps.setObject(i+1,param[i]);
        }
    }

    /**
     * 关闭资源（先开后关闭）
     * @param ps
     * @param conn
     * @param rs
     */
    protected void close(PreparedStatement ps, Connection conn, ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 增删改
     * @param sql
     * @param param
     * @return
     */
    protected int executeUpdate(String sql,Object...param){
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            conn = getConn();
//            获取数据库操作对象
            ps = conn.prepareStatement(sql);
//            填充参数
            if (param!=null&&param.length>0){
                setParam(ps,param);
            }
//            执行SQL语句
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(ps,conn,null);
        }
        return result;
    }

    /**
     * 查询
     * @param sql
     * @param param
     * @return
     */
    protected JDBC executeQuery(String sql, Object...param){
        JDBC jdbc = new JDBC();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            jdbc.conn = conn;
            ps = conn.prepareStatement(sql);
            jdbc.ps = ps;
            if (param!=null&&param.length>0){
                setParam(ps,param);
            }
            rs = ps.executeQuery();
            jdbc.rs = rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jdbc;
    }

    /**
     * 执行添加语句并返回id
     * @param sql sql语句
     * @param param 参数列表
     * @return 返回当前数据的主键编号
     */
    public int executeUpdateGetId(String sql,Object...param){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs =null;
        int result = 0;
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            if(param!=null&&param.length>0){
                setParam(ps, param);
            }
            ps.executeUpdate();
            //获取主键结果集
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(ps,conn,null);
        }
        return result;
    }

}
