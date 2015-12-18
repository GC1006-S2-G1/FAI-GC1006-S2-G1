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
public class DeToan {
    private int MaDeToan;
    private List<Integer> MaCauHoi;

    public int getMaDeToan() {
        return MaDeToan;
    }

    public void setMaDeToan(int MaDeToan) {
        this.MaDeToan = MaDeToan;
    }

    public List<Integer> getMaCauHoi() {
        return MaCauHoi;
    }

    public void setMaCauHoi(List<Integer> MaCauHoi) {
        this.MaCauHoi = MaCauHoi;
    }

    public DeToan(int MaDeToan, List<Integer> MaCauHoi) {
        this.MaDeToan = MaDeToan;
        this.MaCauHoi = MaCauHoi;
    }

    public DeToan() {
    }
}
