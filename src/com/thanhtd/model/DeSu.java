/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.model;

import java.util.List;

/**
 *
 * @author Thanh Tran
 */
public class DeSu {
    private int MaDeSu;
    private List<Integer> MaCauHoi;

    public int getMaDeSu() {
        return MaDeSu;
    }

    public void setMaDeSu(int MaDeSu) {
        this.MaDeSu = MaDeSu;
    }

    public List<Integer> getMaCauHoi() {
        return MaCauHoi;
    }

    public void setMaCauHoi(List<Integer> MaCauHoi) {
        this.MaCauHoi = MaCauHoi;
    }

    public DeSu(int MaDeSu, List<Integer> MaCauHoi) {
        this.MaDeSu = MaDeSu;
        this.MaCauHoi = MaCauHoi;
    }

    public DeSu() {
    }    
}
