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
public class KetQuaTableModel extends AbstractTableModel {

    private List<KetQua> listKetQua = new LinkedList();

    public void setData(List<KetQua> item) {
        this.listKetQua = item;
    }

    public List<KetQua> getData() {
        return listKetQua;
    }

    @Override
    public int getRowCount() {
        return listKetQua.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        KetQua ketQua = listKetQua.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ketQua.getThiSinh().getMaThiSinh();
            case 1:
                return ketQua.getThiSinh().getHoTen();
            case 2:
                return ketQua.getThiSinh().getSoChungMinhThu();
            case 3:
                return dateFormat.format(ketQua.getThiSinh().getNgaySinh());
            case 4:
                return ketQua.getThiSinh().getMaDe();
            case 5:
                return dateFormat.format(ketQua.getNgayThi());
            case 6:
                return ketQua.getDiemToan();
            case 7:
                return ketQua.getDiemSu();
            case 8:
                return ketQua.getDiemVan();
            case 9:
                return ketQua.getDiemToan() + ketQua.getDiemSu() + ketQua.getDiemVan();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Student Id";
            case 1:
                return "Student Name";
            case 2:
                return "Identify Number";
            case 3:
                return "Student DoB";
            case 4:
                return "General Test Id";
            case 5:
                return "Date Exam";
            case 6:
                return "Math";
            case 7:
                return "History";
            case 8:
                return "Literature";
            case 9:
                return "Total";
            default:
                return null;
        }
    }
}
