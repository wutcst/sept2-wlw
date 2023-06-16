package model.Dao;

import model.Acquaintance.ILoadGameObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class loadGameDao implements ILoadGameObject {

    @Override
    public List<String> query(String playerName) {
        //声明结果集
        ResultSet rs = null;

        List<String> list = new ArrayList<>();
        //获取连接对象
        Connection conn = BaseDao.getconn();
        //准备SQL语句
        PreparedStatement ps = null;
        //从用户表查询所有数据
        try {
            String sql = "select scene_file from game_cache where player_name = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, playerName);
            rs = ps.executeQuery();//执行查询
            while (rs.next()) {//放在列表里
                list.add(rs.getString("scene_file"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);//关闭资源
        }

        return list;

    }
}