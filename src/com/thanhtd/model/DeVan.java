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
public class DeVan {

    private int MaDeVan;
    private List<Integer> MaCauHoi;

    public int getMaDeVan() {
        return MaDeVan;
    }

    public void setMaDeVan(int MaDeVan) {
        this.MaDeVan = MaDeVan;
    }

    public List<Integer> getMaCauHoi() {
        return MaCauHoi;
    }

    public void setMaCauHoi(List<Integer> MaCauHoi) {
        this.MaCauHoi = MaCauHoi;
    }

    public DeVan(int MaDeVan, List<Integer> MaCauHoi) {
        this.MaDeVan = MaDeVan;
        this.MaCauHoi = MaCauHoi;
    }

    public DeVan() {
    }
}
