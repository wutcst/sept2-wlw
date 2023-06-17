package controller;

import model.Acquaintance.IPersonalScore;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 用于处理将 IpersonalScore 对象的数组列表转换为文件和从文件返回的操作的类。
 */
public class DataHighscore {

    private ArrayList<IPersonalScore> scoreList = new ArrayList();

    public DataHighscore() {

    }

    /**
     * 接收 IPersonalScore 对象数组列表并使用 Jackson 映射器将其写入 json 文件的方法。
     * @param highscoreList
     * @return  确保保存成功
     */
    boolean saveHigscore(ArrayList highscoreList){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ScoreSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(DataPS.class, new ScoreSerializer());
        mapper.registerModule(module);

        try {
            mapper.writeValue(new File("Gruppe 6 Semester Projekt/files/highscore.json"), highscoreList);
        } catch (IOException ex) {
            Logger.getLogger(DataHighscore.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
/**
用以一个map方法
 */
    ArrayList loadHighscore() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ScoreSerializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(DataPS.class, new ScoreDeserializer());
        mapper.registerModule(module);


        try {
            scoreList = mapper.readValue(new File("Gruppe 6 Semester Projekt/files/highscore.json"), new TypeReference<ArrayList<DataPS>>(){});
            
        } catch (IOException ex) {
            Logger.getLogger(DataHighscore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scoreList;
    }

}
