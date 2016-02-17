/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import qlnt.model.Room;
import qlnt.model.RoomType;
import qlnt.view.QLNT;

/**
 *
 * @author lehoang
 */
public class RoomDetailDialog extends JDialog{
    private QLNT owner;
    private Room room;
    private RoomType rType;
    private JPanel mainPanel;
    
    public RoomDetailDialog(JFrame owner, String title, Room room){
        super(owner, title);
        this.owner = (QLNT)owner;
        this.room = room;
        init();
    }
   
    public void init(){
        setLayout(new BorderLayout());
        setSize(330, 190);
        
        createMainPanel();
        add(mainPanel, BorderLayout.PAGE_START);
        
        setVisible(true);
        setLocationRelativeTo(owner);
        setModalityType(DEFAULT_MODALITY_TYPE);
    }
    
    public void createMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.setBorder(new TitledBorder("Chi tiết phòng trọ"));
        mainPanel.setPreferredSize(new Dimension(320, 160));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 5);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Tên phòng:", JLabel.LEFT), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(new JLabel(room.getRoomName(), JLabel.LEFT), gbc);
        
        getRoomType(room.getIdRoomType());
                
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Loại phòng:", JLabel.LEFT), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(new JLabel(rType.getType(), JLabel.LEFT), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Giá phòng:", JLabel.LEFT), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(new JLabel(String.valueOf(rType.getPrice())+" vnđ", JLabel.LEFT), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Giá phòng tăng trên 1 người:", JLabel.LEFT), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(new JLabel(String.valueOf(rType.getAddPrice())+" vnđ", JLabel.LEFT), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Mô tả:", JLabel.LEFT), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(new JLabel(room.getDescription(), JLabel.LEFT), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Mô tả:", JLabel.LEFT), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(new JLabel(transalteStatus(room.getStatus()), JLabel.LEFT), gbc);
    }
    
    public RoomType getRoomType(int idRType){
        rType = new RoomType();
        ArrayList<RoomType> dataType = null;
        try {
            dataType = rType.getRoomType();
        } catch (Exception ex) {
            Logger.getLogger(RoomDetailDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dataType!=null){
            for (Iterator<RoomType> iterator = dataType.iterator(); iterator.hasNext();) {
                RoomType next = iterator.next();
                if(next.getId() == idRType){
                    rType = next;
                    break;
                }
            }
        }
        return rType;
    }
    
    public String transalteStatus(String enStatus){
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
