/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view.dialog;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import qlnt.model.Room;
import qlnt.model.RoomTableModel;
import qlnt.view.QLNT;
import qlnt.view.form.RoomForm;

/**
 *
 * @author lehoang
 */
public class RoomDialog extends JDialog{
    
    private QLNT owner;
    private Room room;
    private RoomTableModel dataTable;
    
    public RoomDialog(JFrame owner, String title, RoomTableModel dataTable){
        super(owner, title);
        this.owner = (QLNT)owner;
        this.dataTable = dataTable;
        
        init();
        
    }
    
    public RoomDialog(JFrame owner, String title, Room room, RoomTableModel dataTable){
        super(owner, title);
        this.owner = (QLNT)owner;
        this.room = room;
        this.dataTable = dataTable;
        
        init(room);
    }
    
    public void init(){
        setLayout(new BorderLayout());
        
        add(new RoomForm(RoomDialog.this, dataTable));
        
        pack();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(owner);
        setModalityType(DEFAULT_MODALITY_TYPE);
        
    }
    
    public void init(Room room){
        setLayout(new BorderLayout());
        
        add(new RoomForm(RoomDialog.this, room, dataTable));
        
        pack();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(owner);
        setModalityType(DEFAULT_MODALITY_TYPE);
        
    }
}
