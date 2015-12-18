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
public class CauHoi {

    private int MaCauHoi;
    private String MonThi;
    private String NoiDung;
    private String TraLoi1;
    private String TraLoi2;
    private String TraLoi3;
    private String TraLoi4;
    private int DapAn;

    public int getMaCauHoi() {
        return MaCauHoi;
    }

    public void setMaCauHoi(int MaCauHoi) {
        this.MaCauHoi = MaCauHoi;
    }

    public String getMonThi() {
        return MonThi;
    }

    public void setMonThi(String MonThi) {
        this.MonThi = MonThi;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public String getTraLoi1() {
        return TraLoi1;
    }

    public void setTraLoi1(String TraLoi1) {
        this.TraLoi1 = TraLoi1;
    }

    public String getTraLoi2() {
        return TraLoi2;
    }

    public void setTraLoi2(String TraLoi2) {
        this.TraLoi2 = TraLoi2;
    }

    public String getTraLoi3() {
        return TraLoi3;
    }

    public void setTraLoi3(String TraLoi3) {
        this.TraLoi3 = TraLoi3;
    }

    public String getTraLoi4() {
        return TraLoi4;
    }

    public void setTraLoi4(String TraLoi4) {
        this.TraLoi4 = TraLoi4;
    }

    public int getDapAn() {
        return DapAn;
    }

    public void setDapAn(int DapAn) {
        this.DapAn = DapAn;
    }

    public CauHoi(int MaCauHoi, String MonThi, String NoiDung, String TraLoi1, String TraLoi2, String TraLoi3, String TraLoi4, int DapAn) {
        this.MaCauHoi = MaCauHoi;
        this.MonThi = MonThi;
        this.NoiDung = NoiDung;
        this.TraLoi1 = TraLoi1;
        this.TraLoi2 = TraLoi2;
        this.TraLoi3 = TraLoi3;
        this.TraLoi4 = TraLoi4;
        this.DapAn = DapAn;
    }

    public CauHoi() {
    }
}
