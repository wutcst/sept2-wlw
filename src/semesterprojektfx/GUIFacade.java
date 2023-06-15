package semesterprojektfx;

import Acquaintance.IData;
import Acquaintance.IGUI;
import Acquaintance.ILogic;
import Logic.LogicFacade;
import Persistens.PersistenceFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 *
 * @author goope
 */
public class GUIFacade extends Application implements IGUI {

    static GUIFacade gui;
    ILogic logic;

    public static GUIFacade getInstance() {
        return gui;
    }

    @Override
    public void injectLogic(ILogic logiclayer) {
        this.logic = logiclayer;
    }

    ILogic getLogic() {
        return logic;
    }

    @Override
    public void start(Stage stage) throws Exception {
        
//        logic.Play();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void startApplication(String[] args) {
        System.out.println("Is in UI startApplication");
        gui = this;
        launch(args);
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        //Launches the GUI 
//        launch(args);
//        //Closes the rest of the program, after you have exited the GUI.
//        System.exit(0);
//    }
}
