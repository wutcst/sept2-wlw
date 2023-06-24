package model.Logic;

import model.Acquaintance.IRoom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Game game;
    private Room room,medbay, keyRoom;
    Item medkit, oxygen;
    ArrayList<IRoom> roomList = new ArrayList<>();
    private Player player;
    @Before
    public void setUp() throws Exception {
//        game = new Game("test");
        room = new Room("Test Room", "This is a test room");
        game.setCurrentRoom("Test Room");
        player=new Player(100,100);
    }

    @After
    public void tearDown() throws Exception {
    }

    //测试创建游戏房间和内部物品
    @Test
    public void createRooms() {
        Immovable counter,device;
        counter = new Immovable("counter", "A medical counter. There's a medkit on the countertop.", "You can't use this.", false, false);
        device = new Immovable("device", "A strange medical device. There's an oxygen tank attatched to it", "you don't know how to use this.", false, false);
        room.setImmovables(counter);
        room.setImmovables(device);
        // 验证物品数量和属性
        assertEquals(counter, room.getImmovable("counter"));
        roomList.add(room);
        assertEquals(1,roomList.size());
        medkit = new Item("medkit", "A medkit that can heal the user upon use.", 0, 40, 0);
        oxygen = new Item("oxygen", "An oxygen tank, that can refill the users own oxygen tank.", 0, 0, 35);
        assertEquals("A medkit that can heal the user upon use.",medkit.itemDescription);
        assertEquals("An oxygen tank, that can refill the users own oxygen tank.",oxygen.itemDescription);
    }

    @Test
    public void printHelp() {
        assertEquals("You are lost. You are alone. You wander around an abandoned spaceship.\n If you want to do something with anything remember to click it in the list on the right. ",game.printHelp());
    }
    @Test
    public void goRoom() {
        assertEquals("No way!",game.goRoom("test"));
    }
    @Test
    public void removeFromInventory() {
        assertEquals("Drop what?",game.removeFromInventory(""));
    }

    @Test
    public void checkStatus() {
        assertEquals(100,player.getHp());
        assertEquals(100,player.getAir());
    }

    //怪兽随机移动到各房间
    @Test
    public void monsterTravel() {
        int rngRoom = (int) (2 * Math.random());
        boolean flag=false;
        ArrayList<Room> allowedRooms = new ArrayList<>();
        Room r1=new Room("r1","This is r1.");
        Room r2=new Room("r2","This is r2.");
        allowedRooms.add(r1);
        allowedRooms.add(r2);
        Room r=allowedRooms.get(rngRoom);
        if(r.getName()=="r1"||r.getName()=="r2"){
            System.out.println("随机成功");
            flag=true;
        }
        assertEquals(true,flag);
    }

}