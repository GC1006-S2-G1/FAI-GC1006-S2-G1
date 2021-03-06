/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.view;

import com.thanhtd.controller.DbController;
import com.thanhtd.model.ThiSinh;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author Thanh Tran
 */
public class EditStudentDialog extends javax.swing.JDialog {

    /**
     * Creates new form EditStudentDialog
     */
    private ThiSinh thisinh = null;
    private List<String> listGeneralExamId;
    private boolean isDeleted = false;

    public EditStudentDialog(java.awt.Frame parent, boolean modal, ThiSinh item) {
        super(parent, modal);
        initComponents();
        listGeneralExamId = DbController.getListGeneralExamId();

        createAndShowUI();
        if (item != null) {
            thisinh = item;
            showInformation();
        } else {
            showBlankInformation();
        }
    }

    private void createAndShowUI() {
        setTitle("Edit Student Information");
        setLocationRelativeTo(null);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setLineWrap(true);

        for (String i : listGeneralExamId) {
            jComboBox1.addItem(i);
        }
    }

    private void showInformation() {
        jTextField3.setEditable(false);
        jTextField3.setText(thisinh.getMaThiSinh());
        jTextField1.setText(thisinh.getHoTen());
        jDateChooser1.setDate(thisinh.getNgaySinh());
        jTextField2.setText(thisinh.getSoChungMinhThu());
        jDateChooser2.setDate(thisinh.getNgayPhaiLamBai());
        jTextArea1.setText(thisinh.getDiaChi());
        setEnableComponent(false);

        for (int i = 0; i < listGeneralExamId.size(); i++) {
            if (thisinh.getMaDe().equals(listGeneralExamId.get(i))) {
                jComboBox1.setSelectedIndex(i);
            }
        }

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEnableComponent(true);
                jButton3.setText("Save");
                jButton2.setVisible(false);
                jButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!(jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty())) {
                            if (jDateChooser1.getDate().after(new Date(System.currentTimeMillis()))) {
                                JOptionPane.showMessageDialog(null, "Date of Birth can't be the day after today.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else if (DateUtils.isSameDay(jDateChooser1.getDate(), new Date(System.currentTimeMillis()))) {
                                JOptionPane.showMessageDialog(null, "Date of Birth can't be today.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                thisinh.setHoTen(jTextField1.getText());
                                thisinh.setNgaySinh(jDateChooser1.getDate());
                                thisinh.setSoChungMinhThu(jTextField2.getText());
                                thisinh.setDiaChi(jTextArea1.getText());
                                thisinh.setMaDe(jComboBox1.getSelectedItem().toString());
                                thisinh.setNgayPhaiLamBai(jDateChooser2.getDate());
                                if (DbController.updateStudentToDb(thisinh)) {
                                    JOptionPane.showMessageDialog(null, "Update successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                setVisible(false);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please fill all text fields", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });
    }

    private void showBlankInformation() {
        setTitle("Add New Student");
        jTextField3.setText("");
        jTextField1.setText("");
        jDateChooser1.setDate(new Date(System.currentTimeMillis()));
        jTextField2.setText("");
        jTextArea1.setText("");
        jDateChooser2.setDate(new Date(System.currentTimeMillis()));
        jButton2.setVisible(false);
        setEnableComponent(true);
        jButton3.setText("Save");
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(jTextField3.getText().isEmpty() || jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty())) {
                    if (jDateChooser1.getDate().after(new Date(System.currentTimeMillis()))) {
                        JOptionPane.showMessageDialog(null, "Date of Birth can't be the day after today.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (DateUtils.isSameDay(jDateChooser1.getDate(), new Date(System.currentTimeMillis()))) {
                        JOptionPane.showMessageDialog(null, "Date of Birth can't be today.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!(DbController.getListStudentForCheck().containsKey(jTextField3.getText().toUpperCase()))) {
                        thisinh = new ThiSinh();
                        thisinh.setMaThiSinh(jTextField3.getText().toUpperCase());
                        thisinh.setHoTen(jTextField1.getText());
                        thisinh.setNgaySinh(jDateChooser1.getDate());
                        thisinh.setSoChungMinhThu(jTextField2.getText());
                        thisinh.setDiaChi(jTextArea1.getText());
                        thisinh.setMaDe(jComboBox1.getSelectedItem().toString());
                        thisinh.setNgayPhaiLamBai(jDateChooser2.getDate());
                        if (DbController.addStudentToDb(thisinh)) {
                            JOptionPane.showMessageDialog(null, "Insert successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Insert failed.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Student Id existed. Please chooose another Id", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all text fields", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setEnableComponent(boolean flag) {
        if (this == null) {
            jTextField3.setEditable(flag);
        }
        jTextField1.setEditable(flag);
        jTextField2.setEditable(flag);
        jTextArea1.setEditable(flag);
        jComboBox1.setEnabled(flag);
    }

    public ThiSinh getStudent() {
        return thisinh;
    }

    public boolean isDeletedStudent() {
        return isDeleted;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Student Id");

        jLabel3.setText("Student Name");

        jTextField1.setText("jTextField1");

        jLabel4.setText("Date of Birth");

        jLabel5.setText("Identify Number");

        jTextField2.setText("jTextField2");

        jLabel6.setText("Address");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel7.setText("Examination Date");

        jTextField3.setText("jTextField3");

        jLabel2.setText("General Exam ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 117, Short.MAX_VALUE))
                            .addComponent(jTextField3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 103, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int dr = JOptionPane.showConfirmDialog(this, "Are you sure want to delete this student?", "Delete Student", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (dr == JOptionPane.YES_OPTION) {
            if (DbController.deleteStudentFormDb(thisinh.getMaThiSinh())) {
                JOptionPane.showMessageDialog(this, "Student delete successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
                isDeleted = true;
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
