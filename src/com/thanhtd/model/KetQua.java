/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.model;

/**
 *
 * @author Thanh Tran
 */
public class KetQua {

    private int DiemToan;
    private int DiemSu;
    private int DiemVan;
    private ThiSinh thiSinh;

    public KetQua() {
    }

    public KetQua(int DiemToan, int DiemSu, int DiemVan, ThiSinh thiSinh) {
        this.DiemToan = DiemToan;
        this.DiemSu = DiemSu;
        this.DiemVan = DiemVan;
        this.thiSinh = thiSinh;
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
