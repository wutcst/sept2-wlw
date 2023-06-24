package model.Dao;

import model.Acquaintance.ISaveGameObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class SaveGameDao implements ISaveGameObject {

    //定义数据库连接
    private Connection connection;


    /**
     * 插入操作，保存文件
     *
     * @param playerName
     * @param filename
     */
    @Override
    public int insert(String playerName, String filename) {
            String playerID =UUID.randomUUID().toString().replaceAll("-","");
            String sql = "insert into game_cache values(?, ?, ?)";

            Object[] params = {
                    playerID,
                    playerName,
                    filename
                    };

            return BaseDao.exectuIUD(sql, params);
    }
}