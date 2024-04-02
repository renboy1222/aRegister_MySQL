/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.aregister.gui.dialog.au;

import com.aldrin.aregister.dao.impl.SchoolYearDAOImpl;
import com.aldrin.aregister.dao.impl.SemesterDAOImpl;
import com.aldrin.aregister.dao.impl.TuitionDAOImpl;
import com.aldrin.aregister.gui.JFrameRegister;
import com.aldrin.aregister.model.SchoolYear;
import com.aldrin.aregister.model.Semester;
import com.aldrin.aregister.model.Tuition;
import com.aldrin.aregister.util.ComboBoxList;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ALDRIN B. C.
 */
public class JDialogSchoolYearAU extends javax.swing.JDialog {


    private JFrameRegister jFrameRegister;
    private SchoolYear schoolYear = new SchoolYear();
    static String title;
    private SemesterDAOImpl semesterDAOImpl = new SemesterDAOImpl();
    private Semester semester = new Semester();
    private SchoolYearDAOImpl schoolYearDAOImpl = new SchoolYearDAOImpl();
    private Tuition tuition = new Tuition();
    private TuitionDAOImpl tuitionDAOImpl = new TuitionDAOImpl();

    public JDialogSchoolYearAU(JFrameRegister jFrameRegister, boolean modal) {
        super(jFrameRegister, modal);
        initComponents();
        setTitle("New");
        this.title = "New";
        jButton1.setIcon(new FlatSVGIcon("svg/save.svg", 24, 24));
        jTextFieldSchoolYear.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SCHOOL YEAR");
        jTextFieldSchoolYear.setText(schoolYear.getSchoolYear());
        comboBoxSemester();
        comboBoxTuition();

    }

    public JDialogSchoolYearAU(JFrameRegister jFrameRegister, boolean modal, SchoolYear schoolYear, String title) {
        super(jFrameRegister, modal);
        initComponents();
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(187, 187, 187));
        setTitle(title);
        comboBoxSemester();
        comboBoxTuition();
        this.title = title;
        this.schoolYear = schoolYear;
        jButton1.setIcon(new FlatSVGIcon("svg/edit.svg", 24, 24));
        jButton1.setText(title);
        jTextFieldSchoolYear.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SCHOOL YEAR");
        jTextFieldSchoolYear.setText(schoolYear.getSchoolYear());
        for (ComboBoxList a : this.semesterDAOImpl.getList()) {
            a.setSelectedId(semesterDAOImpl.getList(), String.valueOf(schoolYear.getSemester().getId()), jComboBoxSemester);
        }
        
        jDateChooserStart.setDate(new Date(Integer.parseInt(schoolYear.getSyStart().toString().substring(0, 4)) - 1900, Integer.parseInt(schoolYear.getSyStart().toString().substring(5, 7)) - 1, Integer.parseInt(schoolYear.getSyStart().substring(8))));
        jDateChooserEnd.setDate(new Date(Integer.parseInt(schoolYear.getSyEnd().toString().substring(0, 4)) - 1900, Integer.parseInt(schoolYear.getSyEnd().toString().substring(5, 7)) - 1, Integer.parseInt(schoolYear.getSyEnd().substring(8))));

    }

    public JDialogSchoolYearAU(JFrameRegister jFrameRegister, boolean modal, String title, SchoolYear schoolYear) {
        super(jFrameRegister, modal);
        initComponents();
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(187, 187, 187));
        setTitle(title);
        comboBoxSemester();
        comboBoxTuition();
        this.schoolYear = schoolYear;
        this.title = title;
        jButton1.setIcon(new FlatSVGIcon("svg/delete.svg", 24, 24));
        jButton1.setText(title);
        jTextFieldSchoolYear.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SCHOOL YEAR");
        jTextFieldSchoolYear.setText(schoolYear.getSchoolYear());
        for (ComboBoxList a : this.semesterDAOImpl.getList()) {
            a.setSelectedId(semesterDAOImpl.getList(), String.valueOf(schoolYear.getSemester().getId()), jComboBoxSemester);
        }
        
        jDateChooserStart.setDate(new Date(Integer.parseInt(schoolYear.getSyStart().toString().substring(0, 4)) - 1900, Integer.parseInt(schoolYear.getSyStart().toString().substring(5, 7)) - 1, Integer.parseInt(schoolYear.getSyStart().substring(8))));
        jDateChooserEnd.setDate(new Date(Integer.parseInt(schoolYear.getSyEnd().toString().substring(0, 4)) - 1900, Integer.parseInt(schoolYear.getSyEnd().toString().substring(5, 7)) - 1, Integer.parseInt(schoolYear.getSyEnd().substring(8))));

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
        jTextFieldSchoolYear = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxSemester = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserEnd = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jDateChooserStart = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxTuition = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("SCHOOL YEAR:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 20, 90, 30));
        getContentPane().add(jTextFieldSchoolYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 250, 30));

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 140, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("END:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 180, 90, 30));

        getContentPane().add(jComboBoxSemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 250, 30));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("TUITION:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 90, 30));
        getContentPane().add(jDateChooserEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 250, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("START:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 90, 30));
        getContentPane().add(jDateChooserStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 250, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("SEMESTER:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 100, 90, 30));

        getContentPane().add(jComboBoxTuition, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 250, 30));

        setSize(new java.awt.Dimension(381, 313));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.title.equals("New")) {
            int response = JOptionPane.showConfirmDialog(jFrameRegister, "Are you sure to save " + jTextFieldSchoolYear.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                this.schoolYear.setId(null);
                this.schoolYear.setSchoolYear(jTextFieldSchoolYear.getText());
                ComboBoxList semesterId = (ComboBoxList) this.jComboBoxSemester.getSelectedItem();  
                Semester semester = new Semester();
                semester.setId(semesterId.getId());
                ComboBoxList tuitionId = (ComboBoxList) this.jComboBoxTuition.getSelectedItem();
                Tuition tuition = new Tuition();
                tuition.setId(tuitionId.getId());
                this.schoolYear.setTuition(tuition);
                this.schoolYear.setSemester(semester);
                this.schoolYear.setSyStart(new java.sql.Date(jDateChooserStart.getDate().getTime()).toString());
                this.schoolYear.setSyEnd(new java.sql.Date(jDateChooserEnd.getDate().getTime()).toString());
                this.schoolYearDAOImpl.addSchoolYear(schoolYear);
                this.dispose();
            }
        } else if (this.title.equals("Update")) {
            int response = JOptionPane.showConfirmDialog(jFrameRegister, "Are you sure to update " + jTextFieldSchoolYear.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                this.schoolYear.setSchoolYear(jTextFieldSchoolYear.getText());
                ComboBoxList citizenshipId = (ComboBoxList) this.jComboBoxSemester.getSelectedItem();
                Semester semester = new Semester();
                semester.setId(citizenshipId.getId());
                ComboBoxList tuitionId = (ComboBoxList) this.jComboBoxTuition.getSelectedItem();
                Tuition tuition = new Tuition();
                tuition.setId(tuitionId.getId());
                this.schoolYear.setTuition(tuition);
                this.schoolYear.setSemester(semester);
                this.schoolYear.setSyStart(new java.sql.Date(jDateChooserStart.getDate().getTime()).toString());
                this.schoolYear.setSyEnd(new java.sql.Date(jDateChooserEnd.getDate().getTime()).toString());
                this.schoolYearDAOImpl.updateSchoolYear(schoolYear);
                this.dispose();
            }
        } else if (this.title.equals("Delete")) {
            int response = JOptionPane.showConfirmDialog(jFrameRegister, "Are you sure to delete " + jTextFieldSchoolYear.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                this.schoolYearDAOImpl.deleteSchoolYear(schoolYear);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<Object> jComboBoxSemester;
    private javax.swing.JComboBox<Object> jComboBoxTuition;
    private com.toedter.calendar.JDateChooser jDateChooserEnd;
    private com.toedter.calendar.JDateChooser jDateChooserStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextFieldSchoolYear;
    // End of variables declaration//GEN-END:variables
    private void comboBoxSemester() {
        semesterDAOImpl.comboBoxSemester();
        jComboBoxSemester.removeAllItems();
        for (ComboBoxList a : semesterDAOImpl.getList()) {
            this.jComboBoxSemester.addItem(a);
        }
    }

    private void comboBoxTuition() {
        tuitionDAOImpl.comboBoxTuition();
        jComboBoxTuition.removeAllItems();
        for (ComboBoxList a : tuitionDAOImpl.getList()) {
            this.jComboBoxTuition.addItem(a);
        }
    }

}
