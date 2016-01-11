/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thanh Tran
 */
public class GiaoVuTableModel extends AbstractTableModel {

    private List<GiaoVu> listTaiKhoan = null;

    public GiaoVuTableModel() {
        listTaiKhoan = new LinkedList<>();
    }

    public void setData(List<GiaoVu> item) {
        listTaiKhoan = item;
    }

    public List<GiaoVu> getData() {
        return listTaiKhoan;
    }

    @Override
    public int getRowCount() {
        return listTaiKhoan.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        GiaoVu temp = listTaiKhoan.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return temp.getTenTaiKhoan();
            case 1:
                return temp.getHoTen();
            case 2:
                return temp.getEmail();
            case 3:
                return dateFormat.format(temp.getNgaySinh());
            case 4:
                return temp.getMobile();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Account";
            case 1:
                return "Full Name";
            case 2:
                return "Email";
            case 3:
                return "Date of Birth";
            case 4:
                return "Mobile";
            default:
                return "";
        }
    }

}
