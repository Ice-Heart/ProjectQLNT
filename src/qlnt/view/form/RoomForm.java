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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import qlnt.model.Room;
import qlnt.model.RoomStatus;
import qlnt.model.RoomTableModel;
import qlnt.model.RoomType;

/**
 *
 * @author lehoang
 */
public class RoomForm extends JPanel {

    private JPanel pnButton;
    private JTextField txtName, txtPrice, txtAddPrice;
    private JTextArea txtDesc;
    private JComboBox cbStatus, cbRoomType;
    private JScrollPane spDesc;
    private ImageIcon saveIcon, resetIcon;
    private BufferedImage biSave, biReset;
    private JButton btnAdd, btnReset;
    private int imageH = 18;
    private String type = "create";
    private Room room = new Room();
    private DefaultComboBoxModel<RoomType> rTypeModel = new DefaultComboBoxModel<RoomType>();
    private ArrayList<RoomType> dataType = null;
    private DefaultComboBoxModel<RoomStatus> statusModel = new DefaultComboBoxModel<RoomStatus>();
    private ArrayList<RoomStatus> dataStatus = new ArrayList<RoomStatus>();
    private RoomStatus rStatus;
    private RoomType rType;

    public RoomForm(JDialog owner, RoomTableModel dataTable) {
        initComponent();
        init();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                room.setRoomName(txtName.getText());
                rType = (RoomType) cbRoomType.getSelectedItem();
                room.setIdRoomType(rType.getId());
                room.setDescription(txtDesc.getText());
                rStatus = (RoomStatus) cbStatus.getSelectedItem();
                room.setStatus(rStatus.getValue());

                try {
                    room.createRoom(room);
                    dataTable.fireTableDataChanged();
                    owner.setVisible(false);
                    JOptionPane.showMessageDialog(owner, "Thêm mới thành công", "System Error", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(owner, "Thêm mới không thành công", "System Error", JOptionPane.ERROR_MESSAGE);
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

    public RoomForm(JDialog owner, Room room, RoomTableModel dataTable) {
        initComponent();
        init();
        setInput(room);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                room.setRoomName(txtName.getText());
                rType = (RoomType) cbRoomType.getSelectedItem();
                room.setIdRoomType(rType.getId());
                room.setDescription(txtDesc.getText());
                rStatus = (RoomStatus) cbStatus.getSelectedItem();
                room.setStatus(rStatus.getValue());

                try {
                    room.updateRoom(room);
                    dataTable.fireTableDataChanged();
                    owner.setVisible(false);
                    JOptionPane.showMessageDialog(owner, "Cập nhật thành công", "System Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(owner, "Cập nhật không thành công", "System Success", JOptionPane.ERROR_MESSAGE);
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

    public void actionUpdateRoom() {

    }

    public void initComponent() {
        pnButton = new JPanel();

        txtName = new JTextField(25);
        txtDesc = new JTextArea(5, 25);
        txtPrice = new JTextField(25);
        txtAddPrice = new JTextField(25);

        
        dataStatus.add(new RoomStatus("Còn trống", "Empty"));
        dataStatus.add(new RoomStatus("Đã đặt phòng", "Reservation"));
        dataStatus.add(new RoomStatus("Đang cho thuê", "Lease"));
        for(Iterator<RoomStatus> iterator = dataStatus.iterator(); iterator.hasNext();){
            statusModel.addElement(iterator.next());
        }
        cbStatus = new JComboBox(statusModel);
        
        spDesc = new JScrollPane(txtDesc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        try {
            dataType = new RoomType().getRoomType();
        } catch (Exception ex) {
            Logger.getLogger(RoomForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (dataType != null) {
            for (Iterator<RoomType> iterator = dataType.iterator(); iterator.hasNext();) {
                rTypeModel.addElement(iterator.next());

            }
        }
        cbRoomType = new JComboBox(rTypeModel);

        int x, y;
        try {
            biSave = ImageIO.read(new File("src/qlnt/images/save.png"));
            y = imageH;
            x = biSave.getWidth() * y / biSave.getHeight();
            saveIcon = new ImageIcon(biSave.getScaledInstance(x, y, Image.SCALE_SMOOTH));

            biReset = ImageIO.read(new File("src/qlnt/images/clear.png"));
            y = imageH;
            x = biReset.getWidth() * y / biReset.getHeight();
            resetIcon = new ImageIcon(biReset.getScaledInstance(x, y, Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            Logger.getLogger(RoomForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnAdd = new JButton(saveIcon);
        btnReset = new JButton(resetIcon);
    }

    public void init() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(320, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Tên phòng:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Loại phòng:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(cbRoomType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Mô tả:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(spDesc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Trạng thái:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cbStatus, gbc);

        createPanelButton();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(pnButton, gbc);
    }

    public void createPanelButton() {
        pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnButton.add(btnAdd);
        pnButton.add(btnReset);
    }

    public void clearInput() {
        txtName.setText("");
        txtName.requestFocus();
        txtDesc.setText("");
    }
    
    public void setInput(Room room){
        txtName.setText(room.getRoomName());
        txtDesc.setText(room.getDescription());
        
        int index = 0;
        int i = 0;
        for (Iterator<RoomType> iterator = dataType.iterator(); iterator.hasNext();) {
            RoomType next = iterator.next();
            if(next.getId() == room.getIdRoomType()){
                index = i;
                break;
            }
            i++;
        }
        cbRoomType.setSelectedIndex(index);
        
        i = 0;
        for (Iterator<RoomStatus> iterator = dataStatus.iterator(); iterator.hasNext();) {
            RoomStatus next = iterator.next();
            if(next.getValue().equals(room.getStatus())){
                index = i;
                break;
            }
            i++;
        }
        cbStatus.setSelectedIndex(index);
        
    }

}
