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
public class DeThiTableModel extends AbstractTableModel {

    private List<DeThi> listDeThi = null;

    public DeThiTableModel() {
        listDeThi = new LinkedList<>();
    }
    
    public void setData(List<DeThi> item){
        this.listDeThi=item;
    }

    @Override
    public int getRowCount() {
        return listDeThi.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DeThi temp = listDeThi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return temp.getMaDe();
            case 1:
                return temp.getMaCauHoi1();
            case 2:
                return temp.getMaCauHoi2();
            case 3:
                return temp.getMaCauHoi3();
            case 4:
                return temp.getMaCauHoi4();
            case 5:
                return temp.getMaCauHoi5();
            case 6:
                return temp.getMaCauHoi6();
            case 7:
                return temp.getMaCauHoi7();
            case 8:
                return temp.getMaCauHoi8();
            case 9:
                return temp.getMaCauHoi9();
            case 10:
                return temp.getMaCauHoi10();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Question 1";
            case 2:
                return "Question 2";
            case 3:
                return "Question 3";
            case 4:
                return "Question 4";
            case 5:
                return "Question 5";
            case 6:
                return "Question 6";
            case 7:
                return "Question 7";
            case 8:
                return "Question 8";
            case 9:
                return "Question 9";
            case 10:
                return "Question 10";
            default:
                return "";
        }
    }    
}
