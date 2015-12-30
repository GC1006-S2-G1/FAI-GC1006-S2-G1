/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.controller;

import com.thanhtd.model.CauHoi;
import com.thanhtd.model.DeThi;
import com.thanhtd.model.GiaoVu;
import com.thanhtd.model.ThiSinh;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.apache.commons.codec.digest.DigestUtils;

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

    public static List<String> getListSubject() {
        try {
            List<String> temp = new LinkedList();
            CallableStatement cstm = conn.prepareCall("{call sp_listSubject}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                temp.add(result.getString(1));
            }

            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get list of Subject");
        }
        return null;
    }

    public static void changeUserPassword(String username, String newPass) {
        try {
            String sql = "UPDATE [vwListUser] SET MatKhau=? WHERE TenTaiKhoan=" + username;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, DigestUtils.md5Hex(newPass));
            int rowUpdateed = statement.executeUpdate();
            if (rowUpdateed > 0) {
                System.out.println("Change password successfully.");
            } else {
                System.err.println("Change password failed.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean changeCurrentUserPassword(String username, String newPass) {
        try {
            CallableStatement cstm = conn.prepareCall("{call sp_ChangePassWord(?,?)}");
            cstm.setString(1, username);
            cstm.setString(2, newPass);
            int rowEffected = cstm.executeUpdate();
            return rowEffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while change user pass.");
        }
        return false;
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

    public static boolean deleteGiaoVuFromDb(String item) {
        try {
            CallableStatement cstm = conn.prepareCall("{call sp_DeleteAccount(?)}");
            cstm.setString(1, item);
            int rowEffected = cstm.executeUpdate();
            System.out.println("Delete account " + item + " successfully.");
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Delete failed. Please try again.");
        }
        return false;
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

    public static boolean addQuestionsToDb(List<CauHoi> item) {
        try {
            CallableStatement cstm = conn.prepareCall("{call sp_addNewQuestion(?,?,?,?,?,?,?)}");
            for (CauHoi i : item) {
                cstm.setNString(1, i.getMonThi());
                cstm.setNString(2, i.getNoiDung());
                cstm.setNString(3, i.getTraLoi1());
                cstm.setNString(4, i.getTraLoi2());
                cstm.setNString(5, i.getTraLoi3());
                cstm.setNString(6, i.getTraLoi4());
                cstm.setInt(7, i.getDapAn());
                cstm.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Insert questions from text file to Database failed.");
            return false;
        }
    }

    public static boolean addQuestionToDb(CauHoi i) {
        try {
            CallableStatement cstm = conn.prepareCall("{call sp_addNewQuestion(?,?,?,?,?,?,?)}");
            cstm.setNString(1, i.getMonThi());
            cstm.setNString(2, i.getNoiDung());
            cstm.setNString(3, i.getTraLoi1());
            cstm.setNString(4, i.getTraLoi2());
            cstm.setNString(5, i.getTraLoi3());
            cstm.setNString(6, i.getTraLoi4());
            cstm.setInt(7, i.getDapAn());
            cstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Insert question to Database successfully.");
            return false;
        }
    }

    public static List<CauHoi> getListQuestionsFromDB() {
        try {
            List<CauHoi> temp = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllQuestions}");
            ResultSet result = cstm.executeQuery();
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

    public static int getLastQuestionId() {
        try {
            CallableStatement cstm = conn.prepareCall("{call sp_LastQuestionId()}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                return result.getInt("MaCauHoi");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error when get last question Id");
        }
        return -1;
    }

    public static GiaoVu getCurrentUser(String userName) {
        try {
            GiaoVu temp = new GiaoVu();
            CallableStatement cstm = conn.prepareCall("{call sp_GetCurrentUser(" + userName + ")}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                temp.setTenTaiKhoan(result.getString("TenTaiKhoan"));
                temp.setMatKhau(result.getString("MatKhau"));
                temp.setHoTen(result.getNString("HoTen"));
                temp.setEmail(result.getString("Email"));
                temp.setNgaySinh(result.getDate("NgaySinh"));
                temp.setMobile(result.getString("DienThoai"));
                temp.setDiaChi(result.getNString("DiaChi"));
                temp.setIsDeleted(result.getBoolean("IsDeleted"));
            }
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while getting Current User");
        }
        return null;
    }

    public static List<DeThi> getListDeThiToan() {
        try {
            List<DeThi> listDeThi = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllExamMath}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                DeThi temp = new DeThi();
                temp.setMaDe(result.getInt("MaDeToan"));
                temp.setMaCauHoi1(result.getInt("MaCauHoiToan1"));
                temp.setMaCauHoi2(result.getInt("MaCauHoiToan2"));
                temp.setMaCauHoi3(result.getInt("MaCauHoiToan3"));
                temp.setMaCauHoi4(result.getInt("MaCauHoiToan4"));
                temp.setMaCauHoi5(result.getInt("MaCauHoiToan5"));
                temp.setMaCauHoi6(result.getInt("MaCauHoiToan6"));
                temp.setMaCauHoi7(result.getInt("MaCauHoiToan7"));
                temp.setMaCauHoi8(result.getInt("MaCauHoiToan8"));
                temp.setMaCauHoi9(result.getInt("MaCauHoiToan9"));
                temp.setMaCauHoi10(result.getInt("MaCauHoiToan10"));
                listDeThi.add(temp);
            }
            return listDeThi;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<DeThi> getListDeThiVan() {
        try {
            List<DeThi> listDeThi = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllExamLiterial}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                DeThi temp = new DeThi();
                temp.setMaDe(result.getInt("MaDeVan"));
                temp.setMaCauHoi1(result.getInt("MaCauHoiVan1"));
                temp.setMaCauHoi2(result.getInt("MaCauHoiVan2"));
                temp.setMaCauHoi3(result.getInt("MaCauHoiVan3"));
                temp.setMaCauHoi4(result.getInt("MaCauHoiVan4"));
                temp.setMaCauHoi5(result.getInt("MaCauHoiVan5"));
                temp.setMaCauHoi6(result.getInt("MaCauHoiVan6"));
                temp.setMaCauHoi7(result.getInt("MaCauHoiVan7"));
                temp.setMaCauHoi8(result.getInt("MaCauHoiVan8"));
                temp.setMaCauHoi9(result.getInt("MaCauHoiVan9"));
                temp.setMaCauHoi10(result.getInt("MaCauHoiVan10"));
                listDeThi.add(temp);
            }
            return listDeThi;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<DeThi> getListDeThiSu() {
        try {
            List<DeThi> listDeThi = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllExamHistory}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                DeThi temp = new DeThi();
                temp.setMaDe(result.getInt("MaDeSu"));
                temp.setMaCauHoi1(result.getInt("MaCauHoiSu1"));
                temp.setMaCauHoi2(result.getInt("MaCauHoiSu2"));
                temp.setMaCauHoi3(result.getInt("MaCauHoiSu3"));
                temp.setMaCauHoi4(result.getInt("MaCauHoiSu4"));
                temp.setMaCauHoi5(result.getInt("MaCauHoiSu5"));
                temp.setMaCauHoi6(result.getInt("MaCauHoiSu6"));
                temp.setMaCauHoi7(result.getInt("MaCauHoiSu7"));
                temp.setMaCauHoi8(result.getInt("MaCauHoiSu8"));
                temp.setMaCauHoi9(result.getInt("MaCauHoiSu9"));
                temp.setMaCauHoi10(result.getInt("MaCauHoiSu10"));
                listDeThi.add(temp);
            }
            return listDeThi;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<CauHoi> getQuestionListInSubjectTest(String monThi, int maDe) {
        try {
            List<CauHoi> listCauHoi = new LinkedList<>();
            CallableStatement cstm = null;
            switch (monThi) {
                case "Math":
                    cstm = conn.prepareCall("{call sp_GetAllQuestionsFromMathTest(?)}");
                    break;
                case "Literature":
                    cstm = conn.prepareCall("{call sp_GetAllQuestionsFromLiteratureTest(?)}");
                    break;
                case "History":
                    cstm = conn.prepareCall("{call sp_GetAllQuestionsFromHistoryTest(?)}");
                    break;
            }
            cstm.setInt(1, maDe);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                CauHoi temp = new CauHoi();
                temp.setMaCauHoi(result.getInt("MaCauHoi"));
                temp.setMonThi(monThi);
                temp.setNoiDung(result.getNString("NoiDung"));
                temp.setTraLoi1(result.getNString("TraLoi1"));
                temp.setTraLoi2(result.getNString("TraLoi2"));
                temp.setTraLoi3(result.getNString("TraLoi3"));
                temp.setTraLoi4(result.getNString("TraLoi4"));
                temp.setDapAn(result.getInt("DapAn"));
                listCauHoi.add(temp);
            }
            return listCauHoi;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<Integer> getListQuestionId(String monThi) {
        try {
            List<Integer> listId = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListQuestionId(?)}");
            cstm.setNString(1, monThi);
            ResultSet result = cstm.executeQuery();
            int i = 0;
            while (result.next()) {
                listId.add(result.getInt("MaCauHoi"));
            }
            return listId;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get List Question ID.");
            return null;
        }
    }

    public static boolean addSubjectTestToDb(String monThi, List<Integer> questionsId) {
        try {
            CallableStatement cstm = null;
            switch (monThi) {
                case "Math":
                    cstm = conn.prepareCall("{call sp_InsertMathTest(?,?,?,?,?,?,?,?,?,?)}");
                    break;
                case "Literature":
                    cstm = conn.prepareCall("{call sp_InsertLiteratureTest(?,?,?,?,?,?,?,?,?,?)}");
                    break;
                case "History":
                    cstm = conn.prepareCall("{call sp_InsertHistoryTest(?,?,?,?,?,?,?,?,?,?)}");
                    break;
            }
            for (int i = 0; i < 10; i++) {
                cstm.setInt(i + 1, questionsId.get(i));
            }
            int rowEffected = cstm.executeUpdate();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void getListGeneralExamId(DefaultListModel list) {
        try {
            CallableStatement cstm = conn.prepareCall("{call sp_GetListGeneralExamId}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                list.addElement(result.getString("MaDe"));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get list of General Exam Id.");
        }
    }

    public static TreeMap<String, String> getListStudentForLogin() {
        try {
            TreeMap<String, String> listStudent = new TreeMap<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetStudentList}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listStudent.put(result.getString("MaThiSinh"), result.getString("MaDe"));
            }
            return listStudent;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get Student list for Login.");
            return null;
        }
    }

    public static List<ThiSinh> getListStudentsFromDB() {
        try {
            List<ThiSinh> listStudent = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetStudentList}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listStudent.add(new ThiSinh(result.getString("MaThiSinh"), result.getNString("HoTen"), result.getDate("NgaySinh"), result.getString("SoChungMinhThu"), result.getString("MaDe")));
            }
            return listStudent;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get Student list.");
            return null;
        }
    }

    public static ThiSinh getStudentById(String id) {
        try {
            ThiSinh temp = new ThiSinh();
            CallableStatement cstm = conn.prepareCall("{call sp_GetStudentById(?)}");
            cstm.setString(1, id);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                temp.setMaThiSinh(result.getString("MaThiSinh"));
                temp.setHoTen(result.getNString("HoTen"));
                temp.setNgaySinh(result.getDate("NgaySinh"));
                temp.setSoChungMinhThu(result.getString("SoChungMinhThu"));
                temp.setMaDe(result.getString("MaDe"));
            }
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get Student by Id.");
            return null;
        }
    }
}
