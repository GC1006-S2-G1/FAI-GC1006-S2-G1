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
public class QuanLyDeThiTableModel extends AbstractTableModel {

    private List<QuanLyDeThi> listDeThiChung = new LinkedList<>();

    public void setData(List<QuanLyDeThi> item) {
        this.listDeThiChung = item;
    }

    public List<QuanLyDeThi> getData() {
        return listDeThiChung;
    }

    @Override
    public int getRowCount() {
        return listDeThiChung.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        QuanLyDeThi temp = listDeThiChung.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return temp.getMaDe();
            case 1:
                return temp.getTenTaiKhoan();
            case 2:
                return dateFormat.format(temp.getNgayTaoDe());
            case 3:
                return temp.getMaDeToan();
            case 4:
                return temp.getMaDeSu();
            case 5:
                return temp.getMaDeVan();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "General Exam ID";
            case 1:
                return "Creator";
            case 2:
                return "Create Date";
            case 3:
                return "Math Exam ID";
            case 4:
                return "History Exam ID";
            case 5:
                return "Literature Exam ID";
            default:
                return "";
        }
    }
}
