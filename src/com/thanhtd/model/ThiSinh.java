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
public class ThiSinh {
    private String MaThiSinh;
    private String MaDe;
    private String HoTen;
    private Date NgaySinh;

    public String getMaThiSinh() {
        return MaThiSinh;
    }

    public void setMaThiSinh(String MaThiSinh) {
        this.MaThiSinh = MaThiSinh;
    }

    public String getMaDe() {
        return MaDe;
    }

    public void setMaDe(String MaDe) {
        this.MaDe = MaDe;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public ThiSinh(String MaThiSinh, String MaDe, String HoTen, Date NgaySinh) {
        this.MaThiSinh = MaThiSinh;
        this.MaDe = MaDe;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
    }

    public ThiSinh() {
    }    
}
