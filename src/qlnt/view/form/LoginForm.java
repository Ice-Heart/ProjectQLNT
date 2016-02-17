/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view.form;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import qlnt.model.Admin;
import qlnt.util.ValidateDatabaseUtil;
import qlnt.util.ValidateUtil;

/**
 *
 * @author lehoang
 */
public class LoginForm extends JFrame {

    private JLabel lbUsername, lbPassword, lbMessage;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnReset;
    private JPanel pnButton;
    private Admin admin;

    public LoginForm() {

        initComponent();
        init();

        login();

    }

    public void initComponent() {
        lbUsername = new JLabel("Username:", JLabel.LEFT);
        lbPassword = new JLabel("Password:", JLabel.LEFT);
        lbMessage = new JLabel();
        lbMessage.setForeground(Color.red);
        txtUsername = new JTextField(25);
        txtPassword = new JPasswordField(25);
        txtPassword.setEchoChar('*');
        btnLogin = new JButton("Login");
        btnReset = new JButton("Reset");
        pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
    }

    public void init() {
        setTitle("Login Form");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setResizable(false);
        setSize(350, 180);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lbUsername, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lbPassword, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(lbMessage, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        createPanelButton();
        add(pnButton, gbc);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createPanelButton() {
        pnButton.add(btnLogin);
        pnButton.add(btnReset);
    }

    public void resetInput() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus();
    }

    public void login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUsername.getText();
                char[] passArr = txtPassword.getPassword();
                String pass = String.valueOf(passArr);
                if (!ValidateUtil.checkNull(user) || !ValidateUtil.checkNull(pass)) {
                    lbMessage.setText("Tên đăng nhập hoặc mật khẩu không được bỏ trống");
                } else {
                    Admin admin = new Admin();
                    admin.setUsername(user);
                    admin.setPassword(pass);
                    try {
                        if(ValidateDatabaseUtil.checkLogin(admin)){
                            
                        } else {
                            lbMessage.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(LoginForm.this, "Có lỗi trong quá trình sử lý dữ liệu", "System Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    admin.createAdmin(admin);
                    JOptionPane.showMessageDialog(LoginForm.this, "Thêm mới thành công", "System Successs", JOptionPane.INFORMATION_MESSAGE);
                   
                } catch (Exception ex) {
                        JOptionPane.showMessageDialog(LoginForm.this, "Thêm mới không thành công", "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        txtPassword.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String user = txtUsername.getText();
                    char[] passArr = txtPassword.getPassword();
                    String pass = String.valueOf(passArr);
                    if (!ValidateUtil.checkNull(user) || !ValidateUtil.checkNull(pass)) {
                        lbMessage.setText("Tên đăng nhập hoặc mật khẩu không được bỏ trống");
                    } else {
                        lbMessage.setText("");
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

}
