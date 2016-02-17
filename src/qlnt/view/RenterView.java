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
import qlnt.model.Renter;
import qlnt.model.RenterTableModel;
import qlnt.view.dialog.RenterDetailDialog;
import qlnt.view.dialog.RenterDialog;

/**
 *
 * @author lehoang
 */
public class RenterView extends JPanel {

    private JPanel pnTable, pnButton;
    private JButton btnAdd, btnUpdate, btnDetail, btnDelete;
    private JTable tbRenter;
    private ArrayList<Renter> data = null;
    private RenterTableModel dataTable;
    private Renter renter = new Renter();
    private JFrame owner;

    public RenterView(JFrame owner) {
        this.owner = owner;
        initComponent();
        init();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RenterDialog(owner, "Thêm mới người thuê trọ");
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RenterDialog(owner, "Cập nhật người thuê trọ", renter);
            }
        });
        
        btnDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RenterDetailDialog(owner, "Chi tiết khách thuê trọ", renter);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    renter.deleteRenter(renter);
                    dataTable.fireTableDataChanged();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RenterView.this,"Xóa không thành công", "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tbRenter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tbRenter.getSelectedRow();
                renter = data.get(index);
                btnUpdate.setEnabled(true);
                btnDetail.setEnabled(true);
                btnDelete.setEnabled(true);
            }

        });

    }

    public void initComponent() {
        pnTable = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnButton = new JPanel();

        btnAdd = new JButton("Thêm mới");
        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setEnabled(false);
        btnDetail = new JButton("Chi tiết");
        btnDetail.setEnabled(false);
        btnDelete = new JButton("Xóa");
        btnDelete.setEnabled(false);
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
    }

    public void createPanelTable() {
        try {
            data = renter.getRenters();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dataTable = new RenterTableModel(data);
        tbRenter = new JTable(dataTable);

        tbRenter.setPreferredSize(new Dimension(owner.getWidth() - 50, owner.getHeight() - 200));
        tbRenter.setRowHeight(25);

        JScrollPane sp = new JScrollPane(tbRenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setPreferredSize(new Dimension(owner.getWidth() - 20, owner.getHeight() - 170));

        pnTable.add(sp);
    }
}
