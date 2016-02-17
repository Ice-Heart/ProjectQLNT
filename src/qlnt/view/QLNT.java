/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 *
 * @author lehoang
 */
public class QLNT extends JFrame {

    private JPanel pnMenu, pnContent;
    private JButton btnHome, btnRenter, btnRoom, btnService;
    private ImageIcon homeIcon, renterIcon, roomIcon, serviceIcon;
    private int ImageH = 40;
    private BufferedImage biHome, biRenter, biRoom, biService;

    public QLNT() {
        initComponent();
        init();

        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll();
                pnContent.add(new HomeView());
                pnContent.revalidate();
                pnContent.repaint();
            }
        });
        
        btnRenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll();
                pnContent.add(new RenterView(QLNT.this));
                pnContent.revalidate();
                pnContent.repaint();
            }
        });
        
        btnRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll();
                pnContent.add(new RoomView(QLNT.this));
                pnContent.revalidate();
                pnContent.repaint();
            }
        });

    }

    public static void main(String[] args) {
        setLaF("Windows");
        
        new QLNT();

    }

    public static void setLaF(String name) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (name.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initComponent() {
        pnMenu = new JPanel();
        pnContent = new JPanel();
        try {
            int x, y;
            
            biHome = ImageIO.read(new File("src/qlnt/images/home.png"));
            y = ImageH;
            x = ImageH * biHome.getWidth() / biHome.getHeight();
            homeIcon = new ImageIcon(biHome.getScaledInstance(x, y, Image.SCALE_SMOOTH));
            
            biRenter = ImageIO.read(new File("src/qlnt/images/renter.png"));
            y = ImageH;
            x = ImageH * biRenter.getWidth() / biRenter.getHeight();
            renterIcon = new ImageIcon(biRenter.getScaledInstance(x, y, Image.SCALE_SMOOTH));
            
            biRoom = ImageIO.read(new File("src/qlnt/images/room.png"));
            y = ImageH;
            x = ImageH * biRoom.getWidth() / biRoom.getHeight();
            roomIcon = new ImageIcon(biRoom.getScaledInstance(x, y, Image.SCALE_SMOOTH));
            
            biService = ImageIO.read(new File("src/qlnt/images/service.png"));
            y = ImageH;
            x = ImageH * biService.getWidth() / biService.getHeight();
            serviceIcon = new ImageIcon(biService.getScaledInstance(x, y, Image.SCALE_SMOOTH));
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        btnHome = new JButton();
        btnHome.setLayout(new BorderLayout());
        btnHome.add(new JLabel(homeIcon), BorderLayout.PAGE_START);
        btnHome.add(new JLabel("Trang chủ"), BorderLayout.CENTER);
        
        btnRenter = new JButton();
        btnRenter.setLayout(new BorderLayout());
        btnRenter.add(new JLabel(renterIcon), BorderLayout.PAGE_START);
        btnRenter.add(new JLabel("Người thuê trọ"), BorderLayout.CENTER);
        
        btnRoom = new JButton();
        btnRoom.setLayout(new BorderLayout());
        btnRoom.add(new JLabel(roomIcon), BorderLayout.PAGE_START);
        btnRoom.add(new JLabel("Phòng trọ"), BorderLayout.CENTER);
        
        btnService= new JButton();
        btnService.setLayout(new BorderLayout());
        btnService.add(new JLabel(serviceIcon), BorderLayout.PAGE_START);
        btnService.add(new JLabel("Dịch vụ"), BorderLayout.CENTER);
        
    }

    public void init() {
        setTitle("Phần mềm quản lý phòng trọ v1.0");
        setLayout(new BorderLayout());
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPanelMenu();
        add(pnMenu, BorderLayout.PAGE_START);

        createPanelContent();
        add(pnContent, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void createPanelMenu() {
        pnMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnMenu.setPreferredSize(new Dimension(getWidth(), 80));
        pnMenu.setBorder(new TitledBorder(""));
        pnMenu.add(btnHome);
        pnMenu.add(btnRenter);
        pnMenu.add(btnRoom);
        pnMenu.add(btnService);
    }

    public void createPanelContent() {
        pnContent.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnContent.setPreferredSize(new Dimension(getWidth(), getHeight() - 80));
        
        pnContent.add(new HomeView());
    }
}
