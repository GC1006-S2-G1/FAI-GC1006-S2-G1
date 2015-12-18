/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.controller;

import com.thanhtd.model.CauHoi;
import com.thanhtd.model.GiaoVu;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tran
 */
public class DbController {

    public static Connection conn = null;

    public static void connectToDb() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=Project_S2";
            conn = DriverManager.getConnection(dbUrl, "sa", "12345678");
            if (conn != null) {
                System.out.println("Connect to Database successfully. Program starting...");
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Connect to Database failed. Please try again!");
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<GiaoVu> getGiaoVuFromDb() {
        try {
            List<GiaoVu> temp = new LinkedList<>();
            String sql = "SELECT * FROM [GiaoVu]";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String dbTenTaiKhoan = result.getString(1);
                String dbMatKhau = result.getString(2);
                String dbHoTen = result.getString(3);
                String dbEmail = result.getString(4);
                Date dbNgaySinh = result.getDate(5);
                String dbMobile = result.getString(6);
                String dbDiaChi = result.getString(7);

                temp.add(new GiaoVu(dbTenTaiKhoan, dbMatKhau, dbHoTen, dbEmail, dbNgaySinh, dbMobile, dbDiaChi));
            }
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error to get GiaoVu from Database");
        }
        return null;
    }

    public static List<CauHoi> getCauHoiFromDB() {
        try {
            List<CauHoi> temp = new LinkedList<>();
            String sql = "SELECT * FROM [NganHangCauHoi] WHERE IsDeleted=0";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int dbMaCauHoi = result.getInt(1);
                String dbMonThi = result.getNString(2);
                String dbNoiDung = result.getNString(3);
                String dbTraLoi1 = result.getNString(4);
                String dbTraLoi2 = result.getNString(5);
                String dbTraLoi3 = result.getNString(6);
                String dbTraLoi4 = result.getNString(7);
                int dbDapAn = result.getInt(8);

                temp.add(new CauHoi(dbMaCauHoi, dbMonThi, dbNoiDung, dbTraLoi1, dbTraLoi2, dbTraLoi3, dbTraLoi4, dbDapAn));
            }
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error when get Literature Questions from Database");
        }
        return null;
    }
}
