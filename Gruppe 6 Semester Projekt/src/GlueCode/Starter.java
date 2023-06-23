/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GlueCode;

import model.Acquaintance.ILogic;
import model.Logic.Game;
import view.GUIFacade;

import java.io.IOException;

/**
 *
 * @author Rasmus
 */
//初始化
public class Starter {

    public static void main(String[] args) {
        IData data = new PersistenceFacade();
        ILogic logic = new LogicFacade();
        logic.InjectData(data);
        IGUI gui = new GUIFacade();
        gui.injectLogic(logic);
        System.out.println("Ready to launch");
        try {       // we are trying to catch the ioexeption trown from InputHasmap from persistens.

            Game game1 = new Game();
            logic.InjectGame(game1);
            gui.startApplication(args);
            
        } catch (IOException e) {       // this is the catch where if an error from reading from the file shuld occur we want an errormessage printed
            System.err.println("Caught IOException: " + e.getMessage());    // the printline for the error messeage
        }
        System.exit(0);
    }

}
