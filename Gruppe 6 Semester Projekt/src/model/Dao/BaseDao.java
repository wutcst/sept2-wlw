package model.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
    static {
        //加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 创建连接对象函数
     * @return
     */
    public static  Connection getconn() {
        //创建一个连接对象
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world_of_zuul?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","root","123456");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;

    }

    public static int exectuIUD(String sql, Object[] params) {
        int count = 0;

        Connection conn = BaseDao.getconn();

        //准备SQL
        PreparedStatement ps = null;

        try {
            //准备SQL
            ps = conn.prepareStatement(sql);

            for(int i=0; i<params.length; i++) {
                ps.setObject(i+1, params[i]);
            }

            count = ps.executeUpdate();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            BaseDao.closeall(null, ps, conn);

        }

        return count;
    }

    public static void closeall(ResultSet rs, PreparedStatement ps, Connection conn) {

        try {
            if(rs!=null)

                rs.close();
            if(ps!=null)
                ps.close();

            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
