/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.model;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thanh Tran
 */
public class ThiSinhTableModel extends AbstractTableModel {

    private List<ThiSinh> listThiSinh = new LinkedList<>();

    public void setData(List<ThiSinh> item) {
        listThiSinh = item;
    }

    public List<ThiSinh> getData() {
        return listThiSinh;
    }

    @Override
    public int getRowCount() {
        return listThiSinh.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ThiSinh temp = listThiSinh.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return temp.getMaThiSinh();
            case 1:
                return temp.getHoTen();
            case 2:
                return temp.getNgaySinh();
            case 3:
                return temp.getSoChungMinhThu();
            case 4:
                return temp.getMaDe();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Student Id";
            case 1:
                return "Full Name";
            case 2:
                return "Date of Birth";
            case 3:
                return "Identify Number";
            case 4:
                return "General Exam Id";
            default:
                return "";
        }
    }
}
