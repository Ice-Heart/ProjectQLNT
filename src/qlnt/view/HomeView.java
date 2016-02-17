/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import qlnt.model.Room;
import qlnt.model.RoomType;

/**
 *
 * @author lehoang
 */
public class HomeView extends JPanel{
    
    private RoomType rType;
    private Room room;
    private ArrayList<Room> dataRoom = null;
    private ArrayList<RoomType> dataRType = null;
    private JTabbedPane tpRoom;
    public HomeView(){
        room = new Room();
        rType = new RoomType();
        try {
            dataRType = rType.getRoomType();
        } catch (Exception ex) {
            Logger.getLogger(HomeView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            dataRoom = room.getRooms();
        } catch (Exception ex) {
            Logger.getLogger(QLNT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Iterator<Room> iterator = dataRoom.iterator(); iterator.hasNext();) {
            Room next = iterator.next();
            
            add(new RoomLabel(next, getColorRoom(next)));
        }
    }
    
    public String getColorRoom(Room room){
        String color = "";
        switch(room.getStatus().toLowerCase()){
            case "empty":
                color = "#ffffff";
                break;
            case "reservation":
                color = "#ff9900";
                break;
            case "lease":
                color = "#0ff000";
                break;
        }
        
        return color;
    }
}
