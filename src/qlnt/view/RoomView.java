/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import qlnt.model.Room;
import qlnt.model.RoomTableModel;
import qlnt.view.dialog.RoomDetailDialog;
import qlnt.view.dialog.RoomDialog;
import qlnt.view.dialog.RoomTypeDialog;

/**
 *
 * @author lehoang
 */
public class RoomView extends JPanel {

    private JPanel pnButton, pnTable;
    private JButton btnAdd, btnUpdate, btnDetail, btnDelete, btnType;
    private JTable tbRoom;
    private RoomTableModel dataTable;
    private JScrollPane spRoom;
    private ArrayList<Room> data;
    private Room room = new Room();
    private JFrame owner;

    public RoomView(JFrame owner) {
        this.owner = owner;
        initComponent();
        init();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomDialog(owner, "Thêm mới phòng trọ", dataTable);
            }
        });

        btnType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomTypeDialog(owner, "Quản lý kiểu phòng");
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomDialog(owner, "Cập nhật phòng trọ", room, dataTable);
            }
        });

        btnDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomDetailDialog(owner, "Chi tiết phòng trọ", room);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    room.deleteRoom(room);
                    dataTable.fireTableDataChanged();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RoomView.this, "Xóa đối tượng không thành công, có lỗi xảy ra", "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tbRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tbRoom.getSelectedRow();
                room.setIdRoom(data.get(index).getIdRoom());
                room.setIdRoomType(data.get(index).getIdRoomType());
                room.setRoomName(data.get(index).getRoomName());
                room.setDescription(data.get(index).getDescription());
                room.setStatus(data.get(index).getStatus());

                btnUpdate.setEnabled(true);
                btnDetail.setEnabled(true);
                btnDelete.setEnabled(true);
            }

        });

    }

    public void initComponent() {
        pnButton = new JPanel();
        pnTable = new JPanel();

        btnAdd = new JButton("Thêm phòng");
        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setEnabled(false);
        btnDetail = new JButton("Chi tiết");
        btnDetail.setEnabled(false);
        btnDelete = new JButton("Xóa");
        btnDelete.setEnabled(false);
        btnType = new JButton("Thêm loại phòng");
    }

    public void init() {
        setLayout(new BorderLayout());
        createPanelButton();
        add(pnButton, BorderLayout.PAGE_START);
        createPanelTable();
        add(pnTable, BorderLayout.CENTER);
    }

    public void createPanelButton() {
        pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnButton.setBorder(new TitledBorder(""));
        pnButton.setPreferredSize(new Dimension(owner.getWidth() - 20, 40));
        pnButton.add(btnAdd);
        pnButton.add(btnUpdate);
        pnButton.add(btnDetail);
        pnButton.add(btnDelete);
        pnButton.add(btnType);
    }

    public void createPanelTable() {
        pnTable.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnTable.setPreferredSize(new Dimension(owner.getWidth() - 30, owner.getHeight() - 160));

        try {
            data = room.getRooms();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        dataTable = new RoomTableModel(data);
        tbRoom = new JTable(dataTable);
        tbRoom.setPreferredSize(new Dimension(owner.getWidth() - 50, owner.getHeight() - 200));
        tbRoom.setRowHeight(25);

        spRoom = new JScrollPane(tbRoom, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spRoom.setPreferredSize(new Dimension(owner.getWidth() -20, owner.getHeight() - 170));

        pnTable.add(spRoom);
    }

}
