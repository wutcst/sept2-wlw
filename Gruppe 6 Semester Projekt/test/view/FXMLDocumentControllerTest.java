package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Logic.LogicFacade;
import model.Logic.NPC;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.xml.soap.Text;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import org.mockito.Mockito.*;
import org.mockito.plugins.MockMaker;

public class FXMLDocumentControllerTest {

    private ByteArrayOutputStream outContent;
    private LogicFacade logicFacade;

    @Mock
    TextArea textOutArea;

    @Mock
    NPC monster;

    @InjectMocks
    FXMLDocumentController fxmlDocumentController;
    private static final String GAME_WELCOME = "Test:This is the text of gameWelcome() ";

    public void start(Stage stage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("././src/view/FXMLDocument.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void initialize() throws IOException {
       System.out.println(GAME_WELCOME);

 }

    @Test
    public void monsterAttack() {
        List<NPC> npcList = new ArrayList<>();
        npcList.add(monster);
//        when(monster.getHostility()).thenReturn(true);
//        when(monster.getDefeated()).thenReturn(true);
//        when(logicFacade.getCurrentRoomNPC("monster")).thenReturn(monster);

        System.out.println("Test:The monster attacks you for 12 damage.");

    }
}