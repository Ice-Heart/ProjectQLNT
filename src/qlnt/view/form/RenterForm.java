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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import qlnt.model.Renter;
import qlnt.model.RenterTableModel;


/**
 *
 * @author lehoang
 */
public class RenterForm extends JPanel{

    private JPanel pnTable, pnForm, pnButton;
    private JButton btnSave, btnReset;
    private JTable tbRenter;
    private JTextField txtFullName, txtBirthday, txtIdCardNum, txtPhoneNum, txtJob;
    private JTextArea txtAddress, txtAssets;
    private JScrollPane spAddress, spAssets;
    private JRadioButton rbMale, rbFamale;
    private JLabel lbMsg;
    private ButtonGroup btg;
    private ImageIcon addicon, reseticon, updateicon, deleteicon;
    private BufferedImage biadd, birs, biup, bidel;
    private ArrayList<Renter> data = null;
    private RenterTableModel dataTable;
    private Renter renter = new Renter();
    
public RenterForm(){
    initComponent();
    init();
    
    btnSave.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            renter.setFullName(txtFullName.getText());
            if(rbMale.isSelected()){
                renter.setGender(Integer.valueOf(rbMale.getName()));
            } else if(rbFamale.isSelected()){
                renter.setGender(Integer.valueOf(rbFamale.getName()));
            }
            renter.setBirthday(txtBirthday.getText());
            renter.setIdCardNum(txtIdCardNum.getText());
            renter.setPhoneNumber(txtPhoneNum.getText());
            renter.setJob(txtJob.getText());
            renter.setAddress(txtAddress.getText());
            renter.setAssets(txtAssets.getText());
            try {
                renter.createRenter(renter);
                clearInput();
                JOptionPane.showMessageDialog(RenterForm.this, "Thêm mới thành công", "System Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(RenterForm.this, "Thêm mới không thành công", "System Error", JOptionPane.ERROR_MESSAGE);
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

public RenterForm(Renter renter){
    initComponent();
    init();
    setInput(renter);
    
    btnSave.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            renter.setFullName(txtFullName.getText());
            if(rbMale.isSelected()){
                renter.setGender(Integer.valueOf(rbMale.getName()));
            } else if(rbFamale.isSelected()){
                renter.setGender(Integer.valueOf(rbFamale.getName()));
            }
            renter.setBirthday(txtBirthday.getText());
            renter.setIdCardNum(txtIdCardNum.getText());
            renter.setPhoneNumber(txtPhoneNum.getText());
            renter.setJob(txtJob.getText());
            renter.setAddress(txtAddress.getText());
            renter.setAssets(txtAssets.getText());
            try {
                renter.updateRenter(renter);
                clearInput();
                JOptionPane.showMessageDialog(RenterForm.this, "Cập nhật thành công", "System Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(RenterForm.this, "Cập nhật không thành công", "System Error", JOptionPane.ERROR_MESSAGE);
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
    
public void initComponent() {
        pnTable = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnForm = new JPanel(new GridBagLayout());
        pnButton = new JPanel(new FlowLayout(FlowLayout.LEFT));

        try {
            biadd = ImageIO.read(new File("src/qlnt/images/add-renter.png"));
            int x = 0, y = 18;
            x = biadd.getWidth() * y / biadd.getHeight();
            addicon = new ImageIcon(biadd.getScaledInstance(x, y, Image.SCALE_SMOOTH));

            birs = ImageIO.read(new File("src/qlnt/images/clear.png"));
            x = birs.getWidth() * y / birs.getHeight();
            reseticon = new ImageIcon(birs.getScaledInstance(x, y, Image.SCALE_SMOOTH));
            
            bidel = ImageIO.read(new File("src/qlnt/images/delete.png"));
            x = bidel.getWidth() * y / bidel.getHeight();
            deleteicon = new ImageIcon(bidel.getScaledInstance(x, y, Image.SCALE_SMOOTH));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        btnSave = new JButton(addicon);
        btnReset = new JButton(reseticon);

        txtFullName = new JTextField(25);
        txtBirthday = new JTextField(25);
        txtIdCardNum = new JTextField(25);
        txtPhoneNum = new JTextField(25);

        txtAddress = new JTextArea(3, 25);
        spAddress = new JScrollPane(txtAddress, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        txtJob = new JTextField(25);
        txtAssets = new JTextArea(4, 25);
        spAssets = new JScrollPane(txtAssets, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        rbMale = new JRadioButton("Nam");
        rbMale.setName("0");
        rbMale.setSelected(true);
        rbFamale = new JRadioButton("Nữ");
        rbFamale.setName("1");
        btg = new ButtonGroup();
        btg.add(rbMale);
        btg.add(rbFamale);
        
        lbMsg = new JLabel("", JLabel.CENTER);
    }

    public void init(){
        createPanelForm();
        add(pnForm);
    }

    public void createPanelForm() {
        pnForm.setPreferredSize(new Dimension(320, 370));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        pnForm.add(lbMsg, gbc);
        gbc.gridwidth = 1;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnForm.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pnForm.add(txtFullName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnForm.add(new JLabel("Giới tính:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        JPanel pnGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnGender.add(rbMale);
        pnGender.add(rbFamale);
        pnForm.add(pnGender, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pnForm.add(new JLabel("Ngày sinh:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pnForm.add(txtBirthday, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnForm.add(new JLabel("Số CMND:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        pnForm.add(txtIdCardNum, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        pnForm.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        pnForm.add(txtPhoneNum, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        pnForm.add(new JLabel("Nghề nghiệp:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        pnForm.add(txtJob, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        pnForm.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        pnForm.add(spAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        pnForm.add(new JLabel("Tài sản:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        pnForm.add(spAssets, gbc);

        createPanelButton();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        pnForm.add(pnButton, gbc);

    }
    
    
    public void createPanelButton(){
        pnButton.add(btnSave);
        pnButton.add(btnReset);
    }
    
    
    public void setInput(Renter renter) {
        txtFullName.setText(renter.getFullName());
        txtBirthday.setText(renter.getBirthday());
        txtIdCardNum.setText(renter.getIdCardNum());
        txtPhoneNum.setText(renter.getPhoneNumber());
        txtJob.setText(renter.getJob());
        txtAddress.setText(renter.getAddress());
        txtAssets.setText(renter.getAssets());
        if (renter.getGender() == 1) {
            rbFamale.setSelected(true);
        } else {
            rbMale.setSelected(true);
        }
    }

    public void clearInput() {
        txtFullName.setText("");
        txtFullName.requestFocus();
        txtBirthday.setText("");
        txtIdCardNum.setText("");
        txtPhoneNum.setText("");
        txtJob.setText("");
        txtAddress.setText("");
        txtAssets.setText("");
        rbMale.setSelected(true);
    }
}
