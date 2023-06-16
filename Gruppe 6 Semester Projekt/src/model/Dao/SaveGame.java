package model.Dao;

import model.Acquaintance.ISaveGameObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class SaveGame implements ISaveGameObject {

    //定义数据库连接
    private Connection connection;

    /**
     * 通过构造函数定义数据库连接
     *
     * @param connection
     */
    public SaveGame(Connection connection) {
        this.connection = connection;
    }

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