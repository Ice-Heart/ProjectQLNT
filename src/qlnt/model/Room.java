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
public class Room {

    private int idRoom, idRoomType;
    private String roomName;
    private String description;
    private String Status;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdRoomType() {
        return idRoomType;
    }

    public void setIdRoomType(int idRoomType) {
        this.idRoomType = idRoomType;
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

    public void createRoom(Room room) throws Exception {
        String sql = "INSERT INTO tbl_Room(ID_Room_Type, Room_Name, Description, Status) VALUES("+room.getIdRoomType()+", N'" + room.getRoomName() + "', N'" + room.getDescription() + "', N'" + room.getStatus() + "')";
        CUDUtil.statementCUD(sql);
    }

    public void updateRoom(Room room) throws Exception {
        String sql = "UPDATE tbl_Room SET ID_Room_Type = "+room.getIdRoomType()+", Room_Name = N'" + room.getRoomName() + "', Description = N'" + room.getDescription() + "', Status = N'" + room.getStatus() + "' WHERE ID_Room = " + room.getIdRoom();
        CUDUtil.statementCUD(sql);
    }

    public void deleteRoom(Room room) throws Exception {
        String sql = "DELETE FROM tbl_Room WHERE ID_Room = " + room.getIdRoom();
        CUDUtil.statementCUD(sql);
    }
    

    public ArrayList<Room> getRooms() throws Exception {
        ArrayList<Room> data = new ArrayList<Room>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.connectDB();
            String sql = "SELECT * FROM tbl_Room";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Room room = new Room();
                room.setIdRoom(rs.getInt("ID_Room"));
                room.setIdRoomType(rs.getInt("ID_Room_Type"));
                room.setRoomName(rs.getString("Room_Name"));
                room.setDescription(rs.getString("Description"));
                room.setStatus(rs.getString("Status"));
                data.add(room);
            }

            return data;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public int getLastInsertID() throws Exception{
        int id = 0;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.connectDB();
            String sql = "SELECT TOP 1 ID_Room FROM tbl_Room ORDER BY ID_Room DESC";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                id = rs.getInt("ID_Room");
            }
            return id;
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
