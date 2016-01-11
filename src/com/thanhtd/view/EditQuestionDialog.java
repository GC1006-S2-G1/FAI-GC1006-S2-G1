/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.view;

import com.thanhtd.controller.DbController;
import com.thanhtd.model.CauHoi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thanh Tran
 */
public class EditQuestionDialog extends javax.swing.JDialog {

    /**
     * Creates new form EditQuestionDialog
     */
    private CauHoi cauHoi = null;
    private boolean isDeleted = false;
    private boolean isUpdated=false;
    private List<String> listSubject = null;

    public EditQuestionDialog(java.awt.Frame parent, boolean modal, CauHoi item) {
        super(parent, modal);
        initComponents();
        cauHoi = item;
        ShowInformation();
        CreateAndShowUI();
    }

    private void ShowInformation() {
        getListSubject();
        if (cauHoi != null) {
            jLabel2.setText("" + cauHoi.getMaCauHoi());
            jTextArea1.setText(cauHoi.getNoiDung());
            jTextArea2.setText(cauHoi.getTraLoi1());
            jTextArea4.setText(cauHoi.getTraLoi2());
            jTextArea3.setText(cauHoi.getTraLoi3());
            jTextArea5.setText(cauHoi.getTraLoi4());
            jTextField1.setText("" + cauHoi.getDapAn());
            for (int i = 0; i < listSubject.size(); i++) {
                if (listSubject.get(i).equals(cauHoi.getMonThi())) {
                    jComboBox1.setSelectedIndex(i);
                }
            }
        } else {
            jLabel2.setText("" + (DbController.getLastQuestionId() + 1));
            jTextArea1.setText("");
            jTextArea2.setText("");
            jTextArea4.setText("");
            jTextArea3.setText("");
            jTextArea5.setText("");
            jTextField1.setText("");
        }
    }

    private void CreateAndShowUI() {
        setLocationRelativeTo(null);
        setTitle("Edit Question Information");

        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setLineWrap(true);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setLineWrap(true);
        jTextArea3.setWrapStyleWord(true);
        jTextArea3.setLineWrap(true);
        jTextArea4.setWrapStyleWord(true);
        jTextArea4.setLineWrap(true);
        jTextArea5.setWrapStyleWord(true);
        jTextArea5.setLineWrap(true);

        if (cauHoi == null) {
            jButton2.setText("Save");
            jButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cauHoi = new CauHoi();
                    cauHoi.setNoiDung(jTextArea1.getText());
                    cauHoi.setTraLoi1(jTextArea2.getText());
                    cauHoi.setTraLoi2(jTextArea4.getText());
                    cauHoi.setTraLoi3(jTextArea3.getText());
                    cauHoi.setTraLoi4(jTextArea5.getText());
                    cauHoi.setDapAn(Integer.parseInt(jTextField1.getText()));
                }
            });
        }
    }

    public CauHoi getCauHoi() {
        return cauHoi;
    }

    public boolean isDeletedQuestion() {
        return isDeleted;
    }
    
    public boolean isUpdatedQuestion(){
        return isUpdated;
    }

    private void getListSubject() {
        listSubject = DbController.getListSubject();
        for (String i : listSubject) {
            jComboBox1.addItem(i);
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Question ID");

        jLabel2.setText("jLabel2");

        jLabel3.setText("Content");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel4.setText("Choice A");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea2.setRows(3);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel5.setText("Choice C");

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea3.setRows(3);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel6.setText("Choice B");

        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea4.setRows(3);
        jScrollPane4.setViewportView(jTextArea4);

        jLabel7.setText("Choice D");

        jTextArea5.setColumns(20);
        jTextArea5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea5.setRows(3);
        jScrollPane5.setViewportView(jTextArea5);

        jLabel8.setText("Answer");

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

        jLabel9.setText("Subject");

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(247, 247, 247)
                                .addComponent(jLabel9)
                                .addGap(27, 27, 27)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane5))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int dr = JOptionPane.showConfirmDialog(this, "Are you sure want to delete this question?", "Question", JOptionPane.YES_NO_CANCEL_OPTION);
        if (dr == JOptionPane.YES_OPTION) {
            if (DbController.deleteQuestionFromDb(cauHoi)) {
                JOptionPane.showMessageDialog(this, "Question delete successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
                isDeleted = true;
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Question delete failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jComboBox1.getSelectedItem().toString().compareTo(cauHoi.getMonThi()) != 0 || jTextArea1.getText().compareTo(cauHoi.getNoiDung()) != 0
                || jTextArea2.getText().compareTo(cauHoi.getTraLoi1()) != 0 || jTextArea4.getText().compareTo(cauHoi.getTraLoi2()) != 0
                || jTextArea3.getText().compareTo(cauHoi.getTraLoi3()) != 0 || jTextArea5.getText().compareTo(cauHoi.getTraLoi4()) != 0
                || Integer.parseInt(jTextField1.getText()) != cauHoi.getDapAn()) {
            int dr = JOptionPane.showConfirmDialog(this, "Are you want to save?", "Save change", JOptionPane.YES_NO_CANCEL_OPTION);
            if (dr == JOptionPane.YES_OPTION) {
                CauHoi temp = new CauHoi();
                temp.setMaCauHoi(Integer.parseInt(jLabel2.getText()));
                temp.setMonThi(jComboBox1.getSelectedItem().toString());
                temp.setNoiDung(jTextArea1.getText());
                temp.setTraLoi1(jTextArea2.getText());
                temp.setTraLoi2(jTextArea4.getText());
                temp.setTraLoi3(jTextArea3.getText());
                temp.setTraLoi4(jTextArea5.getText());
                temp.setDapAn(Integer.parseInt(jTextField1.getText()));
                if (DbController.updateQuestionToDb(temp)) {
                    JOptionPane.showMessageDialog(this, "Update successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    isUpdated=true;
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
