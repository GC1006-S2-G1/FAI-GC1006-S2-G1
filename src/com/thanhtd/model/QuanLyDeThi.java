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
public class QuanLyDeThi {
    private String MaDe;
    private String TenTaiKhoan;
    private Date NgayTaoDe;
    private int MaDeToan;
    private int MaDeSu;
    private int MaDeVan;

    public QuanLyDeThi() {
    }

    public QuanLyDeThi(String MaDe, String TenTaiKhoan, Date NgayTaoDe, int MaDeToan, int MaDeSu, int MaDeVan) {
        this.MaDe = MaDe;
        this.TenTaiKhoan = TenTaiKhoan;
        this.NgayTaoDe = NgayTaoDe;
        this.MaDeToan = MaDeToan;
        this.MaDeSu = MaDeSu;
        this.MaDeVan = MaDeVan;
    }

    public String getMaDe() {
        return MaDe;
    }

    public void setMaDe(String MaDe) {
        this.MaDe = MaDe;
    }

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String TenTaiKhoan) {
        this.TenTaiKhoan = TenTaiKhoan;
    }

    public Date getNgayTaoDe() {
        return NgayTaoDe;
    }

    public void setNgayTaoDe(Date NgayTaoDe) {
        this.NgayTaoDe = NgayTaoDe;
    }

    public int getMaDeToan() {
        return MaDeToan;
    }

    public void setMaDeToan(int MaDeToan) {
        this.MaDeToan = MaDeToan;
    }

    public int getMaDeSu() {
        return MaDeSu;
    }

    public void setMaDeSu(int MaDeSu) {
        this.MaDeSu = MaDeSu;
    }

    public int getMaDeVan() {
        return MaDeVan;
    }

    public void setMaDeVan(int MaDeVan) {
        this.MaDeVan = MaDeVan;
    }    
}