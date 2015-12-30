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
public class CauHoiTableModel extends AbstractTableModel {

    List<CauHoi> listCauHoi = new LinkedList<>();

    public void setData(List<CauHoi> item) {
        listCauHoi = item;
    }

    public List<CauHoi> getData() {
        return listCauHoi;
    }

    public void addQuestion(CauHoi item) {
        listCauHoi.add(item);
        fireTableDataChanged();
    }

    public void removeQuestion(int index) {
        listCauHoi.remove(index);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return listCauHoi.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CauHoi temp = listCauHoi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return temp.getMaCauHoi();
            case 1:
                return temp.getMonThi();
            case 2:
                return temp.getNoiDung();
            case 3:
                return temp.getTraLoi1();
            case 4:
                return temp.getTraLoi2();
            case 5:
                return temp.getTraLoi3();
            case 6:
                return temp.getTraLoi4();
            case 7:
                return temp.getDapAn();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Question ID";
            case 1:
                return "Subject";
            case 2:
                return "Content";
            case 3:
                return "Choice 1";
            case 4:
                return "Choice 2";
            case 5:
                return "Choice 3";
            case 6:
                return "Choice 4";
            case 7:
                return "Answer";
            default:
                return "";
        }
    }

}
