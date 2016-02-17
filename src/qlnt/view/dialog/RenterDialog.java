/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view.dialog;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import qlnt.model.Renter;
import qlnt.view.form.RenterForm;

/**
 *
 * @author lehoang
 */
public class RenterDialog extends JDialog {
    private JFrame owner;
    private Renter renter;
    
    
    public RenterDialog(JFrame owner, String title) {
        super(owner, title);
        this.owner = owner;
        
        init();
        
    }

    public RenterDialog(JFrame owner, String title, Renter renter) {
        super(owner, title);
        this.owner = owner;
        this.renter = renter;
    
        init(renter);
    }
    
    public void init(){
        setLayout(new BorderLayout());
        
        add(new RenterForm(), BorderLayout.PAGE_START);
        
        pack();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(owner);
        setModalityType(DEFAULT_MODALITY_TYPE);
    }
    
    public void init(Renter renter){
        setLayout(new BorderLayout());
        
        add(new RenterForm(renter), BorderLayout.PAGE_START);
        
        pack();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(owner);
        setModalityType(DEFAULT_MODALITY_TYPE);
    }
    
    
    
}
