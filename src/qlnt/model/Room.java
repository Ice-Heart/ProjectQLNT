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
import qlnt.coonfig.connectdb.DBUtil;


/**
 *
 * @author lehoang
 */
public class Room {
    private int idRoom;
    private String roomName;
    private String description;
    private String Status;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Room(int idRoom, String roomName, String description, String Status) {
        this.idRoom = idRoom;
        this.roomName = roomName;
        this.description = description;
        this.Status = Status;
    }

    public Room() {
    }
    
    public boolean createRoom(Room room) throws Exception{
        Connection conn = DBUtil.connectDB();
        Statement st = null;
        
        if(conn != null){
            String sql = "INSERT INTO tbl_Room(Room_Name, Description, Status) VALUES('"+room.getRoomName()+"', '"+room.getDescription()+"', '"+room.getStatus()+"')";
            try{
                st = conn.createStatement();
                int result = st.executeUpdate(sql);
                if(result != 0){
                    return true;
                } else {
                    return false;
                }
            }catch(Exception ex){
                throw new Exception(ex.getMessage());
            } finally {
                if(st!=null) st.close();
                conn.close();
            }
        } else {
            return false;
        }
    }
    
    public boolean updateRoom(Room room) throws Exception{
        Connection conn = DBUtil.connectDB();
        Statement st = null;
        
        if(conn != null){
            String sql = "UPDATE tbl_Room SET Room_Name = '"+room.getRoomName()+"', Description = '"+room.getDescription()+"', Status = '"+room.getStatus()+"' WHERE ID_Room = "+room.getIdRoom();
            try{
                st = conn.createStatement();
                int result = st.executeUpdate(sql);
                if(result != 0){
                    return true;
                } else {
                    return false;
                }
            }catch(Exception ex){
                throw new Exception(ex.getMessage());
            } finally {
                if(st!=null) st.close();
                conn.close();
            }
        } else {
            return false;
        }
    }
    
    public boolean deleteRoom(Room room) throws Exception{
        Connection conn = DBUtil.connectDB();
        Statement st = null;
        
        if(conn != null){
            String sql = "DELETE FROM tbl_Room WHERE ID_Room = "+room.getIdRoom();
            try{
                st = conn.createStatement();
                int result = st.executeUpdate(sql);
                if(result != 0){
                    return true;
                } else {
                    return false;
                }
            }catch(Exception ex){
                throw new Exception(ex.getMessage());
            } finally {
                if(st!=null) st.close();
                conn.close();
            }
        } else {
            return false;
        }
    }
    
    public ArrayList<Room> getRooms() throws Exception{
        ArrayList<Room> data = new ArrayList<Room>();
        Connection conn = DBUtil.connectDB();
        Statement st = null;
        ResultSet rs = null;
        
        if(conn!=null){
            String sql = "SELECT * FROM tbl_Room";
            try {
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    Room room = new Room();
                    room.setIdRoom(rs.getInt("ID_Room"));
                    room.setRoomName(rs.getString("Room_Name"));
                    room.setDescription(rs.getString("Description"));
                    room.setStatus(rs.getString("Status"));
                    data.add(room);
                }
                
                return data;
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            } finally {
                if(rs!=null) rs.close();
                if(st!=null) st.close();
                conn.close();
            }
        } else {
            return data;
        }
    }
}
