/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.view;

import com.thanhtd.controller.DbController;
import com.thanhtd.model.GiaoVu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Thanh Tran
 */
public class EditAccountInfoDialog extends javax.swing.JDialog {

    /**
     * Creates new form EditAccountInfoDialog
     */
    GiaoVu giaoVu = null;

    public EditAccountInfoDialog(java.awt.Frame parent, boolean modal, GiaoVu item) {
        super(parent, modal);
        initComponents();

        if (item == null) {
            CreateAndShowUI(false);
            addNewAccountFunction();
        } else {
            giaoVu = item;
            CreateAndShowUI(true);
            editAccountFunction();
        }
    }

    private void CreateAndShowUI(boolean flag) {
        setLocationRelativeTo(null);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setLineWrap(true);
        if (flag) {
            jTextField1.setText(giaoVu.getTenTaiKhoan());
            jTextField2.setText(giaoVu.getHoTen());
            jTextField3.setText(giaoVu.getEmail());
            jDateChooser1.setDate(giaoVu.getNgaySinh());
            jTextField4.setText(giaoVu.getMobile());
            jTextArea1.setText(giaoVu.getDiaChi());
            setTitle("Edit Account Information");
        } else {
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextArea1.setText("");
            setTitle("Add new account");
        }
    }

    private void editAccountFunction() {
        setFrameEditable(false);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton3.setText("Save");
                jButton2.setEnabled(false);
                setFrameEditable(true);

                jButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (jTextField1.getText().equals("") || jTextField2.getText().equals("")
                                || jTextField3.getText().equals("") || jTextField4.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Please fill all data fileds", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int dr = JOptionPane.showConfirmDialog(null, "Are you sure want to save new information for this account?", "Confirm to edit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (dr == JOptionPane.YES_OPTION) {
                                giaoVu = new GiaoVu();
                                giaoVu.setTenTaiKhoan(jTextField1.getText());
                                giaoVu.setHoTen(jTextField2.getText());
                                giaoVu.setEmail(jTextField3.getText());
                                giaoVu.setMobile(jTextField4.getText());
                                giaoVu.setDiaChi(jTextArea1.getText());
                                giaoVu.setNgaySinh(jDateChooser1.getDate());
                                if (DbController.updateGiaoVuToDb(giaoVu)) {
                                    JOptionPane.showMessageDialog(null, "Edit account successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Edit failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            setVisible(false);
                        }
                    }
                });
            }
        });
    }

    public GiaoVu getAccount() {
        return giaoVu;
    }

    private void addNewAccountFunction() {
        setFrameEditable(true);
        jButton2.setEnabled(false);
        jButton3.setText("Add");
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextField1.getText().equals("") || jTextField2.getText().equals("")
                        || jTextField3.getText().equals("") || jTextField4.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all data fileds", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int dr = JOptionPane.showConfirmDialog(null, "Are you sure want to create new account?", "Confirm to create new account", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dr == JOptionPane.YES_OPTION) {
                        giaoVu = new GiaoVu();
                        giaoVu.setTenTaiKhoan(jTextField1.getText());
                        giaoVu.setHoTen(jTextField2.getText());
                        giaoVu.setEmail(jTextField3.getText());
                        giaoVu.setMobile(jTextField4.getText());
                        giaoVu.setDiaChi(jTextArea1.getText());
                        giaoVu.setNgaySinh(jDateChooser1.getDate());
                        if (DbController.addGiaoVuToDb(giaoVu)) {
                            JOptionPane.showMessageDialog(null, "Create new account successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Create failed.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    setVisible(false);
                }
            }
        });
    }

    private void setFrameEditable(boolean flag) {
        jTextArea1.setEditable(flag);
        jTextField1.setEditable(flag);
        jTextField2.setEditable(flag);
        jTextField3.setEditable(flag);
        jTextField4.setEditable(flag);
        jDateChooser1.setEnabled(flag);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Account name");

        jTextField1.setText("jTextField1");

        jLabel2.setText("Full Name");

        jTextField2.setText("jTextField2");

        jLabel3.setText("Email");

        jTextField3.setText("jTextField3");

        jLabel4.setText("Date of Birth");

        jLabel5.setText("Mobile");

        jLabel6.setText("Address");

        jTextField4.setText("jTextField4");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(jTextField4))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (giaoVu.getTenTaiKhoan().equals("admin")) {
            JOptionPane.showMessageDialog(this, "You can not delete administration account.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (giaoVu.getTenTaiKhoan().equals(MainFrame.currentUser.getTenTaiKhoan())) {
            JOptionPane.showMessageDialog(this, "You can not delete your current account. Please login as administrator and then delete again.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int dr = JOptionPane.showConfirmDialog(this, "Are you sure want to delete this account?", "Confirm to delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dr == JOptionPane.YES_OPTION) {
                if (DbController.deleteGiaoVuFromDb(giaoVu.getTenTaiKhoan())) {
                    JOptionPane.showMessageDialog(this, "Delete account successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Error while delete this account. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
