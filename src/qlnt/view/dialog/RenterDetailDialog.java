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
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import qlnt.model.Renter;

/**
 *
 * @author lehoang
 */
public class RenterDetailDialog extends JDialog{
    
    private JFrame owner;
    private JPanel mainPanel;
    private Renter renter;
    
    public RenterDetailDialog(JFrame owner, String title, Renter renter) {
        super(owner, title);
        this.owner = owner;
        this.renter = renter;
        
        init();
    }
    
    public void init(){
        setLayout(new BorderLayout());
        createMainPanel();
        add(mainPanel, BorderLayout.PAGE_START);
        pack();
        
        setLocationRelativeTo(owner);
        setVisible(true);
    }
    
    public void createMainPanel(){
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(320, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 5);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(new JLabel(renter.getFullName()), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Giới tính:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(new JLabel(convertGender(renter.getGender())), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Ngày sinh:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(new JLabel(renter.getBirthday()), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Số CMND:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(new JLabel(renter.getIdCardNum()), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(new JLabel(renter.getPhoneNumber()), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(new JLabel(renter.getAddress()), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(new JLabel("Tài sản:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(new JLabel(renter.getAssets()), gbc);
    }
    
    public String convertGender(int gender){
        String Sgender = "";
        switch(gender){
            case 0:
                Sgender = "Nam";
                break;
            case 1:
                Sgender = "Nữ";
                break;
        }
        return  Sgender;
    }
}
