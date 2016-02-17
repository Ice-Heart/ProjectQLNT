/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lehoang
 */
public class RenterTableModel extends DefaultTableModel {

    private ArrayList<Renter> data = new ArrayList<Renter>();

    public RenterTableModel(ArrayList<Renter> data) {
        this.data = data;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public int getRowCount() {
        if (this.data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        Renter renter = data.get(row);
        Object value = null;
        switch (column) {
            case 0:
                value = row+1;
                break;
            case 1:
                value = renter.getFullName();
                break;
            case 2:
                if(renter.getGender() == 0){
                    value = "Nam";
                } else {
                    value = "Nữ";
                }
                break;
            case 3:
                value = renter.getBirthday();
                break;
            case 4:
                value = renter.getIdCardNum();
                break;
            case 5:
                value = renter.getAddress();
                break;
            case 6:
                value = renter.getJob();
                break;
            case 7:
                value = renter.getAssets();
                break;
        }

        return value;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";
        switch (column) {
            case 0:
                name = "STT";
                break;
            case 1:
                name = "Họ tên";
                break;
            case 2:
                name = "Giới tính";
                break;
            case 3:
                name = "Ngày sinh";
                break;
            case 4:
                name = "Số CMND";
                break;
            case 5:
                name = "Địa chỉ";
                break;
            case 6:
                name = "Nghề nghiệp";
                break;
            case 7:
                name = "Tài sản";
                break;
        }
        return name;
    }

}
