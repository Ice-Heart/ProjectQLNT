/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDialog;
import qlnt.model.RoomType;
import qlnt.view.form.RoomTypeForm;

/**
 *
 * @author lehoang
 */
public class RoomTypeFormDialog extends JDialog{
    
    private RoomTypeDialog owner;
    private String title;
    private RoomType rType = new RoomType();
     
    public RoomTypeFormDialog(JDialog owner, String title) {
        super(owner, title);
        this.owner = (RoomTypeDialog) owner;
        this.title = title;
        
        init();
        
    }
     
    public RoomTypeFormDialog(JDialog owner, String title, RoomType rType) {
        super(owner, title);
        this.owner = (RoomTypeDialog) owner;
        this.title = title;
        
        init(rType);
        
    }
    
    public void init(RoomType rType){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(320, 200));
        
        add(new RoomTypeForm(rType), BorderLayout.PAGE_START);
        pack();
        
        setLocationRelativeTo(owner);
        setVisible(true);
        setModalityType(DEFAULT_MODALITY_TYPE);
    }
    
    public void init(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(320, 200));
        
        add(new RoomTypeForm(), BorderLayout.PAGE_START);
        pack();
        
        setLocationRelativeTo(owner);
        setVisible(true);
        setModalityType(DEFAULT_MODALITY_TYPE);
    }

    
}
