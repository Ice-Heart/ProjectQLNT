/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import qlnt.connectdb.DBUtil;
import qlnt.util.CUDUtil;

/**
 *
 * @author lehoang
 */
public class RoomType {
    private String type;
    private float price, addPrice;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(float addPrice) {
        this.addPrice = addPrice;
    }

    public RoomType(String type, float price, float addPrice) {
        this.type = type;
        this.price = price;
        this.addPrice = addPrice;
    }

    public RoomType() {
    }

    @Override
    public String toString() {
        return type;
    }
    
    
    public void createRoomType(RoomType rType) throws Exception{
        String sql = "INSERT INTO tbl_Room_Type(Type, Price, Add_Price) VALUES(N'"+rType.getType()+"', "+rType.getPrice()+", "+rType.getAddPrice()+")";
        CUDUtil.statementCUD(sql);
    }
    
    public void updateRoomType(RoomType rType) throws Exception{
        String sql = "UPDATE tbl_Room_Type SET Type = N'"+rType.getType()+"', Price = "+rType.getPrice()+", Add_Price = "+rType.getAddPrice()+" WHERE ID_Room_Type = "+rType.getId();
        CUDUtil.statementCUD(sql);
    }
    
    public void deleteRoomType(RoomType rType) throws Exception{
        String sql = "DELETE FROM tbl_Room WHERE ID_Room_Type = "+rType.getId();
        CUDUtil.statementCUD(sql);
    }
    
    public RoomType getRoomTypeById(int id) throws Exception{
        RoomType rType = new RoomType();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.connectDB();
            String sql = "SELECT * FROM tbl_Room_Type WHERE ID_Room_Type = " + id;
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                rType.setId(rs.getInt("ID_Room_Type"));
                rType.setType(rs.getString("Type"));
                rType.setPrice(rs.getFloat("Price"));
                rType.setAddPrice(rs.getFloat("Add_Price"));
            }
            return rType;
            
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if(rs!=null){
                rs.close();
            }
            if(st!=null){
                st.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }
    
    public ArrayList<RoomType> getRoomType() throws Exception{
        ArrayList<RoomType> data = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT * FROM tbl_Room_Type";
            conn = DBUtil.connectDB();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                RoomType rType = new RoomType();
                rType.setId(rs.getInt("ID_Room_Type"));
                rType.setType(rs.getString("Type"));
                rType.setPrice(rs.getFloat("Price"));
                rType.setAddPrice(rs.getFloat("Add_Price"));
                
                data.add(rType);
            }
            return data;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if(rs!=null){
                rs.close();
            }
            if(st!=null){
                st.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
        
    }
    
}
