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
import java.util.SortedMap;
import java.util.TreeMap;
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
            String sql = "SELECT * FROM GiaoVu WHERE IsDeleted=0";
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

    public static TreeMap<String, String> getListUserFromDb() {
        try {
            String sql = "SELECT * FROM vwListUser";
            TreeMap<String, String> temp = new TreeMap<>();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                temp.put(result.getString(1), result.getString(2));
            }
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error to get User from Database");
        }
        return null;
    }

    public static void deleteGiaoVuFromDb(GiaoVu item) {
        try {
            String url = "UPDATE [GiaoVu] SET IsDeleted=1 WHERE TenTaiKhoan=?";
            PreparedStatement statement = conn.prepareStatement(url);
            statement.setString(1, item.getTenTaiKhoan());
            int rowEffected = statement.executeUpdate();
            if (rowEffected > 0) {
                System.out.println("Delete account successfully.");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Delete failed. Please try again.");
        }
    }

    public static boolean addGiaoVuToDb(GiaoVu item) {
        try {
            String sql = "INSERT INTO [GiaoVu](TenTaiKhoan,MatKhau,HoTen,Email,NgaySinh,DienThoai,DiaChi) VALUES\n"
                    + "(?,'202cb962ac59075b964b07152d234b70',?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, item.getTenTaiKhoan());
            statement.setNString(2, item.getHoTen());
            statement.setString(3, item.getEmail());
            statement.setDate(4, new Date(item.getNgaySinh().getTime()));
            statement.setString(5, item.getMobile());
            statement.setNString(6, item.getDiaChi());
            int rowEffected = statement.executeUpdate();
            if (rowEffected > 0) {
                System.out.println("Insert new account successfully.");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Insert failed.");
            return false;
        }
        return false;
    }

    public static boolean updateGiaoVuToDb(GiaoVu item) {
        try {
            String sql = "UPDATE [GiaoVu] SET HoTen=?,Email=?,NgaySinh=?,DienThoai=?,DiaChi=? WHERE TenTaiKhoan=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setNString(1, item.getHoTen());
            statement.setString(2, item.getEmail());
            statement.setDate(3, new Date(item.getNgaySinh().getTime()));
            statement.setString(4, item.getMobile());
            statement.setNString(5, item.getDiaChi());
            statement.setString(6, item.getTenTaiKhoan());
            int rowEffected = statement.executeUpdate();
            if (rowEffected > 0) {
                System.out.println("Update successfully.");
                return true;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Update failed.");
        }
        return false;
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
