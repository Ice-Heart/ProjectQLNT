/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lehoang
 */
public class RoomTypeTableModel extends DefaultTableModel{
    ArrayList<RoomType> data = null;

    public RoomTypeTableModel(ArrayList<RoomType> data) {
        this.data = data;
    }
    

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Object value = null;
        RoomType rType = data.get(row);
        switch(column){
            case 0:
                value = row+1;
                break;
            case 1:
                value = rType.getType();
                break;
            case 2:
                value = rType.getPrice();
                break;
            case 3:
                value = rType.getAddPrice();
                break;
        }
        
        return value;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";
        switch(column){
            case 0:
                name = "STT";
                break;
            case 1:
                name = "Kiểu";
                break;
            case 2:
                name = "Giá";
                break;
            case 3:
                name = "Giá tăng / người";
                break;
        }
        
        return name;
    }
    
}
