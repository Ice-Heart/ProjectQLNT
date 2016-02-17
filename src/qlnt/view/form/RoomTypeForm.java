/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view.form;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import qlnt.model.RoomType;

/**
 *
 * @author lehoang
 */
public class RoomTypeForm extends JPanel{
    private JTextField txtType, txtPrice, txtAddPrice;
    private JButton btnSave, btnReset;
    private JPanel pnButton;
    private RoomType rType;
    private ImageIcon saveIcon, resetIcon;
    private BufferedImage biSave, biReset;
    private int imageH = 18;
    
    public RoomTypeForm(){
        initComponent();
        init();
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rType = new RoomType(txtType.getText(), Float.valueOf(txtPrice.getText()), Float.valueOf(txtAddPrice.getText()));
                try {
                    rType.createRoomType(rType);
                    JOptionPane.showMessageDialog(RoomTypeForm.this, "Thêm kiều phòng thành công", "System Success", JOptionPane.INFORMATION_MESSAGE);
                    validate();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RoomTypeForm.this, "Thêm kiều phòng không thành công", "System Error", JOptionPane.ERROR_MESSAGE);
                    validate();
                }
            }
        });
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
            }
        });
    }
    
    public RoomTypeForm(RoomType rType){
        
        initComponent();
        init();
        setInput(rType);
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rType.setType(txtType.getText());
                rType.setPrice(Float.valueOf(txtPrice.getText()));
                rType.setAddPrice(Float.valueOf(txtAddPrice.getText()));
                try {
                    rType.updateRoomType(rType);
                    JOptionPane.showMessageDialog(RoomTypeForm.this, "Cập nhật kiều phòng thành công", "System Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RoomTypeForm.this, "Cập nhật kiều phòng không thành công", "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
            }
        });
    }
    
    public void initComponent(){
        pnButton = new JPanel();
        
        txtType = new JTextField(20);
        txtPrice = new JTextField(20);
        txtAddPrice = new JTextField(20);
        
        
        int x, y;
        try {
            biSave = ImageIO.read(new File("src/qlnt/images/save.png"));
            y = imageH;
            x = biSave.getWidth()*y/biSave.getHeight();
            saveIcon = new ImageIcon(biSave.getScaledInstance(x, y, Image.SCALE_SMOOTH));
            
            biReset = ImageIO.read(new File("src/qlnt/images/clear.png"));
            y = imageH;
            x = biReset.getWidth()*y/biReset.getHeight();
            resetIcon = new ImageIcon(biReset.getScaledInstance(x, y, Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            Logger.getLogger(RoomForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btnSave = new JButton(saveIcon);
        btnReset = new JButton(resetIcon);
    }
    
    public void init(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(350, 170));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 5);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Kiều phòng:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtType, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Giá phòng:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPrice, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Giá/người:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtAddPrice, gbc);
        
        createPanelButton();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(pnButton, gbc);
    }
    
    public void createPanelButton(){
        pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnButton.add(btnSave);
        pnButton.add(btnReset);
    }
    
    public void clearInput(){
        txtType.setText("");
        txtType.requestFocus();
        txtPrice.setText("");
        txtAddPrice.setText("");
    }
    
    public void setInput(RoomType rType){
        txtType.setText(rType.getType());
        txtPrice.setText(String.valueOf(rType.getPrice()));
        txtAddPrice.setText(String.valueOf(rType.getAddPrice()));
        txtType.requestFocus();
    }
}
