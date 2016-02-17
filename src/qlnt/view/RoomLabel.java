/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import qlnt.model.Room;
import qlnt.model.RoomType;

/**
 *
 * @author lehoang
 */
public class RoomLabel extends JPanel{
    private String color;
    private Room room;
    private RoomType rType = new RoomType();
    public RoomLabel(Room room, String color){
        this.color = color;
        this.room = room;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(160, 80));
        setBackground(Color.decode(color));
        
        JLabel lbRName = new JLabel(room.getRoomName(), JLabel.CENTER);
        lbRName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lbRName.setPreferredSize(new Dimension(160, 80));
        add(lbRName, BorderLayout.PAGE_START);
        
        String renterName = "";
        try {
            renterName = rType.getRoomTypeById(room.getIdRoomType()).getType();
        } catch (Exception ex) {
            Logger.getLogger(RoomLabel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        add(new JLabel(renterName, JLabel.CENTER), BorderLayout.CENTER);
        
    }
}
