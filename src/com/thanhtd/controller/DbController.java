/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.controller;

import com.thanhtd.model.CauHoi;
import com.thanhtd.model.DeThi;
import com.thanhtd.model.GiaoVu;
import com.thanhtd.model.KetQua;
import com.thanhtd.model.QuanLyDeThi;
import com.thanhtd.model.ThiSinh;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tran
 */
public class DbController {

    public static List<GiaoVu> getGiaoVuFromDb() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
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
            conn.close();
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error to get GiaoVu from Database");
        }
        return null;
    }

    public static List<String> getListSubject() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            List<String> temp = new LinkedList();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListSubject}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                temp.add(result.getString(1));
            }
            conn.close();
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get list of Subject");
        }
        return null;
    }

    public static boolean changeCurrentUserPassword(String username, String newPass) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_ChangePassWord(?,?)}");
            cstm.setString(1, username);
            cstm.setString(2, newPass);
            int rowEffected = cstm.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while change user pass.");
            return false;
        }
    }

    public static TreeMap<String, String> getListUserFromDb() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            String sql = "SELECT * FROM vwListUser";
            TreeMap<String, String> temp = new TreeMap<>();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                temp.put(result.getString(1), result.getString(2));
            }
            conn.close();
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error to get User from Database");
            return null;
        }
    }

    public static boolean deleteGiaoVuFromDb(String item) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_DeleteAccount(?)}");
            cstm.setString(1, item);
            int rowEffected = cstm.executeUpdate();
            System.out.println("Delete account " + item + " successfully.");
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Delete failed. Please try again.");
            return false;
        }
    }

    public static boolean deleteStudentFormDb(String maThiSinh) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_DeleteStudentById(?)}");
            cstm.setString(1, maThiSinh);
            int rowEffected = cstm.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while delete Student from Db. Please try again.");
            return false;
        }
    }

    public static boolean addGiaoVuToDb(GiaoVu item) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            String sql = "INSERT INTO [GiaoVu](TenTaiKhoan,MatKhau,HoTen,Email,NgaySinh,DienThoai,DiaChi) VALUES\n"
                    + "(?,'202cb962ac59075b964b07152d234b70',?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, item.getTenTaiKhoan());
            statement.setNString(2, item.getHoTen());
            statement.setString(3, item.getEmail());

            java.sql.Date tempDate = new java.sql.Date(item.getNgaySinh().getTime());
            statement.setDate(4, tempDate);

            statement.setString(5, item.getMobile());
            statement.setNString(6, item.getDiaChi());
            int rowEffected = statement.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Insert failed.");
            return false;
        }
    }

    public static boolean updateGiaoVuToDb(GiaoVu item) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            String sql = "UPDATE [GiaoVu] SET HoTen=?,Email=?,NgaySinh=?,DienThoai=?,DiaChi=? WHERE TenTaiKhoan=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setNString(1, item.getHoTen());
            statement.setString(2, item.getEmail());
            statement.setDate(3, (java.sql.Date) new Date(item.getNgaySinh().getTime()));
            statement.setString(4, item.getMobile());
            statement.setNString(5, item.getDiaChi());
            statement.setString(6, item.getTenTaiKhoan());
            int rowEffected = statement.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Update failed.");
            return false;
        }
    }

    public static boolean addQuestionsToDb(List<CauHoi> item) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
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
            conn.close();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Insert questions from file to Database failed.");
            return false;
        }
    }

    public static boolean addQuestionToDb(CauHoi i) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_addNewQuestion(?,?,?,?,?,?,?)}");
            cstm.setNString(1, i.getMonThi());
            cstm.setNString(2, i.getNoiDung());
            cstm.setNString(3, i.getTraLoi1());
            cstm.setNString(4, i.getTraLoi2());
            cstm.setNString(5, i.getTraLoi3());
            cstm.setNString(6, i.getTraLoi4());
            cstm.setInt(7, i.getDapAn());
            cstm.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Insert question to Database successfully.");
            return false;
        }
    }

    public static boolean deleteQuestionFromDb(CauHoi item) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_DeleteQuestionById(?)}");
            cstm.setInt(1, item.getMaCauHoi());
            int rowEffected = cstm.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute deleteQuestionFromDb from DbController.");
            return false;
        }
    }

    public static List<CauHoi> getListQuestionsFromDB() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
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
            conn.close();
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error when get Literature Questions from Database");
        }
        return null;
    }

    public static int getLastQuestionId() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetLastQuestionId()}");
            ResultSet result = cstm.executeQuery();
            int nextId = -1;
            while (result.next()) {
                nextId = result.getInt("MaCauHoi");
            }
            conn.close();
            return nextId;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error when get last question Id");
        }
        return -1;
    }

    public static GiaoVu getCurrentUser(String userName) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
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
            conn.close();
            return temp;
        } catch (SQLException ex) {
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while getting Current User");
        }
        return null;
    }

    public static List<DeThi> getListDeThiToan() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            List<DeThi> listDeThi = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllMathExam}");
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
            conn.close();
            return listDeThi;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<DeThi> getListDeThiVan() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            List<DeThi> listDeThi = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllLiteratureExam}");
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
            conn.close();
            return listDeThi;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<DeThi> getListDeThiSu() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            List<DeThi> listDeThi = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllHistoryExam}");
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
            conn.close();
            return listDeThi;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<CauHoi> getListQuestionInSubjectTest(String monThi, int maDe) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
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
            conn.close();
            return listCauHoi;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<CauHoi> getListQuestionOfMathTest(int maDe) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            List<CauHoi> listCauHoiToan = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListQuestionIdOfMathTest(?)}");
            cstm.setInt(1, maDe);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                for (int i = 0; i < 10; i++) {
                    listCauHoiToan.add(getQuestionById(result.getInt("MaCauHoiToan" + (i + 1))));
                }
            }
            conn.close();
            return listCauHoiToan;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while running getListQuestionOfMathTest Function on DbController.");
            return null;
        }
    }

    public static List<CauHoi> getListQuestionOfLiteratureTest(int maDe) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            List<CauHoi> listCauHoiVan = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListQuestionIdOfLiteratureTest(?)}");
            cstm.setInt(1, maDe);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                for (int i = 0; i < 10; i++) {
                    listCauHoiVan.add(getQuestionById(result.getInt("MaCauHoiVan" + (i + 1))));
                }
            }
            conn.close();
            return listCauHoiVan;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while running getListQuestionOfLiteratureTest Function on DbController.");
            return null;
        }
    }

    public static List<CauHoi> getListQuestionOfHistoryTest(int maDe) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            List<CauHoi> listCauHoiSu = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListQuestionIdOfHistoryTest(?)}");
            cstm.setInt(1, maDe);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                for (int i = 0; i < 10; i++) {
                    listCauHoiSu.add(getQuestionById(result.getInt("MaCauHoiSu" + (i + 1))));
                }
            }
            conn.close();
            return listCauHoiSu;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while running getListQuestionOfHistoryTest Function on DbController.");
            return null;
        }
    }

    public static CauHoi getQuestionById(int id) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CauHoi temp = new CauHoi();
            CallableStatement cstm = conn.prepareCall("{call sp_GetQuestionById(?)}");
            cstm.setInt(1, id);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                temp.setMaCauHoi(result.getInt("MaCauHoi"));
                temp.setMonThi(result.getString("MonThi"));
                temp.setNoiDung(result.getNString("NoiDung"));
                temp.setTraLoi1(result.getNString("TraLoi1"));
                temp.setTraLoi2(result.getNString("TraLoi2"));
                temp.setTraLoi3(result.getNString("TraLoi3"));
                temp.setTraLoi4(result.getNString("TraLoi4"));
                temp.setDapAn(result.getInt("DapAn"));
            }
            conn.close();
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while running getQuestionById Function on DbController.");
            return null;
        }
    }

    public static List<Integer> getListQuestionId(String monThi) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            List<Integer> listId = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListQuestionId(?)}");
            cstm.setNString(1, monThi);
            ResultSet result = cstm.executeQuery();
            int i = 0;
            while (result.next()) {
                listId.add(result.getInt("MaCauHoi"));
            }
            conn.close();
            return listId;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get List Question ID.");
            return null;
        }
    }

    public static boolean addSubjectTestToDb(String monThi, List<Integer> questionsId) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
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
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static List<String> getListGeneralExamId() {
        try {
            List<String> listItem = new LinkedList<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListGeneralExamId}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listItem.add(result.getString("MaDe"));
            }
            conn.close();
            return listItem;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get list of General Exam Id (2nd edition).");
            return null;
        }
    }

    public static TreeMap<String, java.util.Date> getListStudentForLogin() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            TreeMap<String, Date> listStudent = new TreeMap<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetStudentListForLogin}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listStudent.put(result.getString("MaThiSinh"), result.getDate("NgayPhaiLamBai"));
            }
            conn.close();
            return listStudent;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get Student list for Login.");
            return null;
        }
    }

    public static TreeMap<String, java.util.Date> getListStudentForCheck() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            TreeMap<String, Date> listStudent = new TreeMap<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetStudentList}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listStudent.put(result.getString("MaThiSinh"), result.getDate("NgayPhaiLamBai"));
            }
            conn.close();
            return listStudent;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get Student list for Login.");
            return null;
        }
    }

    public static List<ThiSinh> getListStudentsFromDB() {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            List<ThiSinh> listStudent = new LinkedList<>();
            CallableStatement cstm = conn.prepareCall("{call sp_GetStudentList}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listStudent.add(new ThiSinh(result.getString("MaThiSinh"), result.getNString("HoTen"), result.getDate("NgaySinh"), result.getString("SoChungMinhThu"), result.getNString("DiaChi"), result.getString("MaDe"), result.getDate("NgayPhaiLamBai")));
            }
            conn.close();
            return listStudent;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get Student list.");
            return null;
        }
    }

    public static ThiSinh getStudentById(String id) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            ThiSinh temp = new ThiSinh();
            CallableStatement cstm = conn.prepareCall("{call sp_GetStudentById(?)}");
            cstm.setString(1, id);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                temp.setMaThiSinh(result.getString("MaThiSinh"));
                temp.setHoTen(result.getNString("HoTen"));
                temp.setNgaySinh(result.getDate("NgaySinh"));
                temp.setSoChungMinhThu(result.getString("SoChungMinhThu"));
                temp.setDiaChi(result.getNString("DiaChi"));
                temp.setMaDe(result.getString("MaDe"));
                temp.setNgayPhaiLamBai(result.getDate("NgayPhaiLamBai"));
            }
            conn.close();
            return temp;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while get Student by Id.");
            return null;
        }
    }

    public static TreeMap<String, Integer> GetListSubjectExamId(String MaDe) {
        try {
            TreeMap<String, Integer> listSubjectId = new TreeMap<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListSubjectExamId(?)}");
            cstm.setString(1, MaDe);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listSubjectId.put("History", result.getInt("MaDeSu"));
                listSubjectId.put("Literature", result.getInt("MaDeVan"));
                listSubjectId.put("Math", result.getInt("MaDeToan"));
            }
            conn.close();
            return listSubjectId;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while GetListSubjectExamId on DbController.");
            return null;
        }
    }

    public static List<KetQua> GetResultByStudent(String maThiSinh) {
        try {
            List<KetQua> listKetQua = new LinkedList<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetResultTestForStudent(?)}");
            cstm.setString(1, maThiSinh);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                ThiSinh tempThiSinh = new ThiSinh();
                tempThiSinh.setMaThiSinh(result.getString("MaThiSinh"));
                tempThiSinh.setHoTen(result.getNString("HoTen"));
                tempThiSinh.setNgaySinh(result.getDate("NgaySinh"));
                tempThiSinh.setSoChungMinhThu(result.getString("SoChungMinhThu"));
                tempThiSinh.setMaDe(result.getString("MaDe"));

                KetQua tempKetQua = new KetQua();
                tempKetQua.setThiSinh(tempThiSinh);
                tempKetQua.setNgayThi(result.getDate("NgayThi"));
                tempKetQua.setDiemToan(result.getInt("DiemToan"));
                tempKetQua.setDiemSu(result.getInt("DiemSu"));
                tempKetQua.setDiemVan(result.getInt("DiemVan"));

                listKetQua.add(tempKetQua);
            }
            conn.close();
            return listKetQua;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute function GetResultByStudent from DbController.");
            return null;
        }
    }

    public static List<KetQua> GetResultByUser() {
        try {
            List<KetQua> listKetQua = new LinkedList<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetResultTestForUser}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                ThiSinh tempThiSinh = new ThiSinh();
                tempThiSinh.setMaThiSinh(result.getString("MaThiSinh"));
                tempThiSinh.setHoTen(result.getNString("HoTen"));
                tempThiSinh.setNgaySinh(result.getDate("NgaySinh"));
                tempThiSinh.setSoChungMinhThu(result.getString("SoChungMinhThu"));
                tempThiSinh.setMaDe(result.getString("MaDe"));

                KetQua tempKetQua = new KetQua();
                tempKetQua.setThiSinh(tempThiSinh);
                tempKetQua.setNgayThi(result.getDate("NgayThi"));
                tempKetQua.setDiemToan(result.getInt("DiemToan"));
                tempKetQua.setDiemSu(result.getInt("DiemSu"));
                tempKetQua.setDiemVan(result.getInt("DiemVan"));

                listKetQua.add(tempKetQua);
            }
            conn.close();
            return listKetQua;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute function GetResultByUser from DbController.");
            return null;
        }
    }

    public static boolean InsertResultToDb(KetQua ketQua) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_InsertResultToDb(?,?,?,?,?,?)}");
            cstm.setString(1, ketQua.getThiSinh().getMaDe());
            cstm.setString(2, ketQua.getThiSinh().getMaThiSinh());

            java.sql.Date tempDate = new java.sql.Date(ketQua.getNgayThi().getTime());
            cstm.setDate(3, tempDate);

            cstm.setInt(4, ketQua.getDiemToan());
            cstm.setInt(5, ketQua.getDiemSu());
            cstm.setInt(6, ketQua.getDiemVan());
            int rowEffected = cstm.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute InsertResultToDb from DbController.");
            return false;
        }
    }

    public static List<QuanLyDeThi> getListGeneralExamFromDb() {
        try {
            List<QuanLyDeThi> listDeThi = new LinkedList<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListGeneralExam}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                QuanLyDeThi temp = new QuanLyDeThi();
                temp.setMaDe(result.getString("MaDe"));
                temp.setTenTaiKhoan(result.getString("TenTaiKhoan"));
                temp.setMaDeToan(result.getInt("MaDeToan"));
                temp.setMaDeSu(result.getInt("MaDeSu"));
                temp.setMaDeVan(result.getInt("MaDeVan"));
                java.sql.Date tempDate = new java.sql.Date(result.getDate("NgayTaoDe").getTime());
                temp.setNgayTaoDe(tempDate);

                listDeThi.add(temp);
            }
            conn.close();
            return listDeThi;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute getListGeneralExamFromDb from DbController.");
            return null;
        }
    }

    public static List<Integer> getListMathExamId() {
        try {
            List<Integer> listId = new LinkedList<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllMathExam}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listId.add(result.getInt("MaDeToan"));
            }
            conn.close();
            return listId;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute getListMathExamId from DbController.");
            return null;
        }
    }

    public static List<Integer> getListHistoryExamId() {
        try {
            List<Integer> listId = new LinkedList<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllHistoryExam}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listId.add(result.getInt("MaDeSu"));
            }
            conn.close();
            return listId;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute getListHistoryExamId from DbController.");
            return null;
        }
    }

    public static List<Integer> getListLiteratureExamId() {
        try {
            List<Integer> listId = new LinkedList<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetAllLiteratureExam}");
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                listId.add(result.getInt("MaDeVan"));
            }
            conn.close();
            return listId;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute getListLiteratureExamId from DbController.");
            return null;
        }
    }

    public static boolean insertNewGeneralExamToDb(QuanLyDeThi item) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_InsertNewGeneralExam(?,?,?,?,?,?)}");
            cstm.setString(1, item.getMaDe());
            cstm.setString(2, item.getTenTaiKhoan());
            java.sql.Date tempDate = new java.sql.Date(item.getNgayTaoDe().getTime());
            cstm.setDate(3, tempDate);
            cstm.setInt(4, item.getMaDeToan());
            cstm.setInt(5, item.getMaDeSu());
            cstm.setInt(6, item.getMaDeVan());
            int rowEffected = cstm.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute insertNewGeneralExamToDb from DbController.");
            return false;
        }
    }

    public static boolean deleteGeneralExamById(String maDe) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_DeleteGeneralExamById(?)}");
            cstm.setString(1, maDe);
            int rowEffected = cstm.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute deleteGeneralExamById from DbController.");
            return false;
        }
    }

    public static boolean addStudentToDb(ThiSinh item) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_AddNewStudent(?,?,?,?,?,?,?)}");
            cstm.setString(1, item.getMaThiSinh());
            cstm.setNString(2, item.getHoTen());
            java.sql.Date tempDate1 = new java.sql.Date(item.getNgaySinh().getTime());
            cstm.setDate(3, tempDate1);
            cstm.setString(4, item.getSoChungMinhThu());
            cstm.setNString(5, item.getDiaChi());
            cstm.setString(6, item.getMaDe());
            java.sql.Date tempDate2 = new java.sql.Date(item.getNgayPhaiLamBai().getTime());
            cstm.setDate(7, tempDate2);
            int rowEffected = cstm.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute addStudentToDb from DbController.");
            return false;
        }
    }

    public static boolean updateStudentToDb(ThiSinh item) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_UpdateStudent(?,?,?,?,?,?,?)}");
            cstm.setString(1, item.getMaThiSinh());
            cstm.setNString(2, item.getHoTen());
            java.sql.Date tempDate1 = new java.sql.Date(item.getNgaySinh().getTime());
            cstm.setDate(3, tempDate1);
            cstm.setString(4, item.getSoChungMinhThu());
            cstm.setNString(5, item.getDiaChi());
            cstm.setString(6, item.getMaDe());
            java.sql.Date tempDate2 = new java.sql.Date(item.getNgayPhaiLamBai().getTime());
            cstm.setDate(7, tempDate2);
            int rowEffected = cstm.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute updateStudentToDb from DbController.");
            return false;
        }
    }

    public static List<KetQua> getListStudentPassedByDate(Date date) {
        try {
            List<KetQua> listPass = new LinkedList<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListStudentPassedByDate(?)}");
            java.sql.Date tempDate = new java.sql.Date(date.getTime());
            cstm.setDate(1, tempDate);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                ThiSinh tempThiSinh = new ThiSinh();
                tempThiSinh.setMaThiSinh(result.getString("MaThiSinh"));
                tempThiSinh.setHoTen(result.getNString("HoTen"));
                tempThiSinh.setNgaySinh(result.getDate("NgaySinh"));
                tempThiSinh.setSoChungMinhThu(result.getString("SoChungMinhThu"));
                tempThiSinh.setMaDe(result.getString("MaDe"));

                KetQua tempKetQua = new KetQua();
                tempKetQua.setThiSinh(tempThiSinh);
                tempKetQua.setNgayThi(result.getDate("NgayThi"));
                tempKetQua.setDiemToan(result.getInt("DiemToan"));
                tempKetQua.setDiemSu(result.getInt("DiemSu"));
                tempKetQua.setDiemVan(result.getInt("DiemVan"));

                listPass.add(tempKetQua);
            }
            conn.close();
            return listPass;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute getListStudentPassedByDate from DbController.");
            return null;
        }
    }

    public static List<KetQua> getListStudentFailedByDate(Date date) {
        try {
            List<KetQua> listFail = new LinkedList<>();
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_GetListStudentFailedByDate(?)}");
            java.sql.Date tempDate = new java.sql.Date(date.getTime());
            cstm.setDate(1, tempDate);
            ResultSet result = cstm.executeQuery();
            while (result.next()) {
                ThiSinh tempThiSinh = new ThiSinh();
                tempThiSinh.setMaThiSinh(result.getString("MaThiSinh"));
                tempThiSinh.setHoTen(result.getNString("HoTen"));
                tempThiSinh.setNgaySinh(result.getDate("NgaySinh"));
                tempThiSinh.setSoChungMinhThu(result.getString("SoChungMinhThu"));
                tempThiSinh.setMaDe(result.getString("MaDe"));

                KetQua tempKetQua = new KetQua();
                tempKetQua.setThiSinh(tempThiSinh);
                tempKetQua.setNgayThi(result.getDate("NgayThi"));
                tempKetQua.setDiemToan(result.getInt("DiemToan"));
                tempKetQua.setDiemSu(result.getInt("DiemSu"));
                tempKetQua.setDiemVan(result.getInt("DiemVan"));

                listFail.add(tempKetQua);
            }
            conn.close();
            return listFail;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute getListStudentFailedByDate from DbController.");
            return null;
        }
    }

    public static boolean updateQuestionToDb(CauHoi cauHoi) {
        try {
            Connection conn = ConnectionUtil.connectToDb();
            CallableStatement cstm = conn.prepareCall("{call sp_UpdateQuestion(?,?,?,?,?,?,?,?)}");
            cstm.setInt(1, cauHoi.getMaCauHoi());
            cstm.setNString(2, cauHoi.getMonThi());
            cstm.setNString(3, cauHoi.getNoiDung());
            cstm.setNString(4, cauHoi.getTraLoi1());
            cstm.setNString(5, cauHoi.getTraLoi2());
            cstm.setNString(6, cauHoi.getTraLoi3());
            cstm.setNString(7, cauHoi.getTraLoi4());
            cstm.setInt(8, cauHoi.getDapAn());
            int rowEffected = cstm.executeUpdate();
            conn.close();
            return rowEffected > 0;
        } catch (SQLException ex) {
            //Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while execute updateQuestionToDb from DbController.");
            return false;
        }
    }
}
