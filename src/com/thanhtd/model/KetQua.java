/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.model;

import java.util.Date;

/**
 *
 * @author Thanh Tran
 */
public class KetQua {

    private ThiSinh thiSinh;
    private Date ngayThi;
    private int DiemToan;
    private int DiemSu;
    private int DiemVan;

    public KetQua() {
    }

    public KetQua(ThiSinh thiSinh, Date ngayThi, int DiemToan, int DiemSu, int DiemVan) {
        this.thiSinh = thiSinh;
        this.ngayThi = ngayThi;
        this.DiemToan = DiemToan;
        this.DiemSu = DiemSu;
        this.DiemVan = DiemVan;
    }

    public Date getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(Date ngayThi) {
        this.ngayThi = ngayThi;
    }

    public int getDiemToan() {
        return DiemToan;
    }

    public void setDiemToan(int DiemToan) {
        this.DiemToan = DiemToan;
    }

    public int getDiemSu() {
        return DiemSu;
    }

    public void setDiemSu(int DiemSu) {
        this.DiemSu = DiemSu;
    }

    public int getDiemVan() {
        return DiemVan;
    }

    public void setDiemVan(int DiemVan) {
        this.DiemVan = DiemVan;
    }

    public ThiSinh getThiSinh() {
        return thiSinh;
    }

    public void setThiSinh(ThiSinh thiSinh) {
        this.thiSinh = thiSinh;
    }
}
