/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import qlnt.model.RoomType;
import qlnt.model.RoomTypeTableModel;
import qlnt.view.QLNT;

/**
 *
 * @author lehoang
 */
public class RoomTypeDialog extends JDialog{

    private QLNT owner;
    private String title;
    private JPanel pnButton, pnTable;
    private JButton btnAdd, btnUpdate, btnDelete;
    private JTable tbRoomType;
    private JScrollPane spTable;
    private TableModel dataModel;
    private RoomType rType = new RoomType();
    private ArrayList<RoomType> data;

    public RoomTypeDialog(JFrame owner, String title) {
        super(owner, title);
        this.owner = (QLNT)owner;
        this.title = title;
        
        initComponent();
        init();
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomTypeFormDialog(RoomTypeDialog.this, "Thêm mới kiểu phòng");
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomTypeFormDialog(RoomTypeDialog.this, "Cập nhật kiều phòng", rType);
            }
        });
        
        tbRoomType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int index = tbRoomType.getSelectedRow();
                rType = data.get(index);
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
            }
            
        });
    }
    
    
    public void initComponent(){
        pnButton = new JPanel();
        pnTable = new JPanel();
        
        btnAdd = new JButton("Thêm mới");
        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setEnabled(false);
        btnDelete = new JButton("Xóa");
        btnDelete.setEnabled(false);
    }
    
    public void init(){
        setLayout(new BorderLayout());
        setSize(600, 300);
        
        createPanelButton();
        add(pnButton, BorderLayout.PAGE_START);
        createPanelTable();
        add(pnTable, BorderLayout.CENTER);
        
        setVisible(true);
        setLocationRelativeTo(owner);
    }
    
    public void createPanelButton(){
       pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
       pnButton.setPreferredSize(new Dimension(getWidth()-40, 40));
       pnButton.setBorder(new TitledBorder(""));
       pnButton.add(btnAdd);
       pnButton.add(btnUpdate);
       pnButton.add(btnDelete);
        
    }
    
    public void createPanelTable(){
        pnTable.setLayout(new FlowLayout());
        pnTable.setPreferredSize(new Dimension(getWidth()-20, 250));
        try {
            data = rType.getRoomType();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dataModel = new RoomTypeTableModel(data);
        tbRoomType = new JTable(dataModel);
        tbRoomType.setRowHeight(25);
        tbRoomType.setPreferredSize(new Dimension(getWidth()-50, 220));
        spTable = new JScrollPane(tbRoomType, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spTable.setPreferredSize(new Dimension(getWidth()-20, 250));
        
        pnTable.add(spTable);
    }
    
    
}
