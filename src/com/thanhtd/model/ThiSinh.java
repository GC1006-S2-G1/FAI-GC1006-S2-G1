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
public class ThiSinh implements Comparable<ThiSinh>{

    private String MaThiSinh;
    private String HoTen;
    private Date NgaySinh;
    private String SoChungMinhThu;
    private String DiaChi;
    private String MaDe;
    private Date NgayPhaiLamBai;

    public ThiSinh() {
    }

    public ThiSinh(String MaThiSinh, String HoTen, Date NgaySinh, String SoChungMinhThu, String DiaChi, String MaDe, Date NgayPhaiLamBai) {
        this.MaThiSinh = MaThiSinh;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.SoChungMinhThu = SoChungMinhThu;
        this.DiaChi = DiaChi;
        this.MaDe = MaDe;
        this.NgayPhaiLamBai = NgayPhaiLamBai;
    }    
    
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

    public String getSoChungMinhThu() {
        return SoChungMinhThu;
    }

    public void setSoChungMinhThu(String SoChungMinhThu) {
        this.SoChungMinhThu = SoChungMinhThu;
    }    

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public Date getNgayPhaiLamBai() {
        return NgayPhaiLamBai;
    }

    public void setNgayPhaiLamBai(Date NgayPhaiLamBai) {
        this.NgayPhaiLamBai = NgayPhaiLamBai;
    }

    @Override
    public int compareTo(ThiSinh o) {
        return this.MaThiSinh.compareTo(o.MaThiSinh);
    }
}
