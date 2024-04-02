/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.aregister.gui.dialog.au;


import com.aldrin.aregister.dao.impl.SemesterDAOImpl;
import com.aldrin.aregister.gui.JFrameRegister;
import com.aldrin.aregister.model.Semester;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author ALDRIN B. C.
 */
public class JDialogSemesterAU extends javax.swing.JDialog {


    private JFrameRegister jFrameRegister;
    private Semester semester = new Semester();
    static String title;
    private SemesterDAOImpl semesterDAOImpl = new SemesterDAOImpl();

    public JDialogSemesterAU(JFrameRegister jFrameRegister, boolean modal) {
        super(jFrameRegister, modal);
        initComponents();
        setTitle("New");
        this.title = "New";
        jButton1.setIcon(new FlatSVGIcon("svg/save.svg", 24, 24));
        jTextFieldSemester.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SEMESTER");
        jTextFieldSemester.setText(semester.getSemester());

    }

    public JDialogSemesterAU(JFrameRegister jFrameRegister, boolean modal, Semester semester, String title) {
        super(jFrameRegister, modal);
        initComponents();
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(187, 187, 187));
        setTitle(title);
        this.title = title;
        this.semester = semester;
        jButton1.setIcon(new FlatSVGIcon("svg/edit.svg", 24, 24));
        jButton1.setText(title);
        jTextFieldSemester.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SEMESTER");
        jTextFieldSemester.setText(semester.getSemester());
    }

    public JDialogSemesterAU(JFrameRegister jFrameRegister, boolean modal, String title, Semester semester) {
        super(jFrameRegister, modal);
        initComponents();
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(187, 187, 187));
        setTitle(title);
        this.semester = semester;
        this.title = title;
        jButton1.setIcon(new FlatSVGIcon("svg/delete.svg", 24, 24));
        jButton1.setText(title);
        jTextFieldSemester.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SEMESTER");
        jTextFieldSemester.setText(semester.getSemester());

    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldSemester = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("SEMESTER:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 20, 80, 30));
        getContentPane().add(jTextFieldSemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 250, 30));

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 140, 30));

        setSize(new java.awt.Dimension(366, 190));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.title.equals("New")) {
            int response = JOptionPane.showConfirmDialog(jFrameRegister, "Are you sure to save " + jTextFieldSemester.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                this.semester.setId(null);
                this.semester.setSemester(jTextFieldSemester.getText());
                semesterDAOImpl.addSemester(semester);
                this.dispose();
            }
        } else if (this.title.equals("Update")) {
            int response = JOptionPane.showConfirmDialog(jFrameRegister, "Are you sure to update " + jTextFieldSemester.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                this.semester.setSemester(jTextFieldSemester.getText());
                semesterDAOImpl.updateSemester(semester);
                this.dispose();
            }
        } else if (this.title.equals("Delete")) {
            int response = JOptionPane.showConfirmDialog(jFrameRegister, "Are you sure to delete " + jTextFieldSemester.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                semesterDAOImpl.deleteSemester(semester);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldSemester;
    // End of variables declaration//GEN-END:variables
}
