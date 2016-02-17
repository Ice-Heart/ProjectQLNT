/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lehoang
 */
public class RoomTableModel extends DefaultTableModel{
    
    ArrayList<Room> data = new ArrayList<>();
    
    public RoomTableModel(ArrayList<Room> data) {
        this.data = data;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object value = null;
        Room room = data.get(row);
        switch(column){
            case 0:
                value = row+1;
                break;
            case 1:
                value = room.getRoomName();
                break;
            case 2:
                value = convertRoomType(room.getIdRoomType());
                break;
            case 3:
                value = room.getDescription();
                break;
            case 4:
                value = translateRoomStatus(room.getStatus());
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";
        switch(column){
            case 0:
                name = "STT";
                break;
            case 1:
                name = "Tên phòng";
                break;
            case 2:
                name = "Loại phòng";
                break;
            case 3:
                name = "Mô tả";
                break;
            case 4:
                name = "Trạng thái";
                break;
        }
        return name;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public int getRowCount() {
        if(data!=null)
            return data.size();
        return 0;
    }
    
    public String convertRoomType(int idType){
        String type = "";
        RoomType rType = new RoomType();
        ArrayList<RoomType> dataRType = null;
        try {
            dataRType = rType.getRoomType();
        } catch (Exception ex) {
            Logger.getLogger(RoomTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Iterator<RoomType> iterator = dataRType.iterator(); iterator.hasNext();) {
            RoomType next = iterator.next();
            if(next.getId() == idType){
                type = next.getType();
                break;
            }
            
        }
        
        return type;
    }
    
    public String translateRoomStatus(String enStatus){
        String viStatus = "";
        switch(enStatus.toLowerCase()){
            case "empty":
                viStatus = "Trống";
                break;
            case "reservation":
                viStatus = "Đã đặt cọc";
                break;
            case "lease":
                viStatus = "Đang thuê";
                break;
        }
        
        return viStatus;
    }
    
    
}
