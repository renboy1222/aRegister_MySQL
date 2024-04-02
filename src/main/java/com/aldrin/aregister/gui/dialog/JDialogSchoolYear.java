/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.aregister.gui.dialog;

import com.aldrin.aregister.gui.JFrameRegister;
import com.aldrin.aregister.dao.impl.SchoolYearDAOImpl;
import com.aldrin.aregister.gui.dialog.au.JDialogSchoolYearAU;
import com.aldrin.aregister.model.SchoolYear;
import com.aldrin.aregister.model.Semester;
import com.aldrin.aregister.model.Tuition;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ALDRIN B. C.
 */
public class JDialogSchoolYear extends javax.swing.JDialog implements MouseListener {

    private JFrameRegister jFrameRegister;
    private SchoolYear schoolYear;

    public JDialogSchoolYear(JFrameRegister jFrameRegister, boolean modal) {
        super(jFrameRegister, modal);
        this.jFrameRegister = jFrameRegister;
        initComponents();
        setTable();
        selectSchoolYear();
        jTextFieldSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        //icon
        jTextFieldSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("svg/search.svg", 24, 24));
        jButtonDelete.setEnabled(false);
        jButtonUpdate.setEnabled(false);
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCourse = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButtonNew = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCHOOL YEAR");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jTableCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableCourse);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(10, 425));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel7.setPreferredSize(new java.awt.Dimension(10, 425));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, java.awt.BorderLayout.EAST);

        jPanel8.setPreferredSize(new java.awt.Dimension(886, 10));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(886, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));

        jButtonNew.setIcon(new FlatSVGIcon("svg/add.svg",24,24));
        jButtonNew.setText("New");
        jButtonNew.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonNew.setPreferredSize(new java.awt.Dimension(80, 32));
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonNew);

        jButtonUpdate.setIcon(new FlatSVGIcon("svg/edit.svg",24,24));
        jButtonUpdate.setText("Update");
        jButtonUpdate.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonUpdate.setPreferredSize(new java.awt.Dimension(80, 32));
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonUpdate);

        jButtonDelete.setIcon(new FlatSVGIcon("svg/delete.svg",24,24));
        jButtonDelete.setText("Delete");
        jButtonDelete.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(80, 32));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonDelete);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));

        jTextFieldSearch.setPreferredSize(new java.awt.Dimension(250, 32));
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanel4.add(jTextFieldSearch);

        jPanel2.add(jPanel4, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        setSize(new java.awt.Dimension(647, 416));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        JDialogSchoolYearAU syAU = new JDialogSchoolYearAU(jFrameRegister, true);
        syAU.setVisible(true);
        selectSchoolYear();
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        JDialogSchoolYearAU syAu = new JDialogSchoolYearAU(jFrameRegister, true, schoolYear, "Update");
        syAu.setVisible(true);
        selectSchoolYear();
        jButtonDelete.setEnabled(false);
        jButtonUpdate.setEnabled(false);
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        JDialogSchoolYearAU syAU = new JDialogSchoolYearAU(jFrameRegister, true, "Delete", schoolYear);
        syAU.setVisible(true);
        jButtonDelete.setEnabled(false);
        jButtonUpdate.setEnabled(false);
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        String text = jTextFieldSearch.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + ",*"));
        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCourse;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
  DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "TUITION ID", "SEMESTER_ID", "SCHOOL YEAR", "SEMESTER", "TUITION", "SY START", "SY END"}, 0);

    private TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);

    private void setTable() {
        jTableCourse.setCellSelectionEnabled(true);
        jTableCourse = new JTable(tableModel);
        jScrollPane1.setViewportView(jTableCourse);
        jTableCourse.addMouseListener(this);
        jTableCourse.setRowSorter(sorter);

        TableColumn[] column = new TableColumn[100];
        column[1] = jTableCourse.getColumnModel().getColumn(1);
        column[1].setPreferredWidth(80);

        TableColumn hide0 = jTableCourse.getColumnModel().getColumn(0);
        hide0.setMinWidth(0);
        hide0.setMaxWidth(0);
        hide0.setPreferredWidth(0);
        TableColumn hide1 = jTableCourse.getColumnModel().getColumn(1);
        hide1.setMinWidth(0);
        hide1.setMaxWidth(0);
        hide1.setPreferredWidth(0);
        TableColumn hide2 = jTableCourse.getColumnModel().getColumn(2);
        hide2.setMinWidth(0);
        hide2.setMaxWidth(0);
        hide2.setPreferredWidth(0);

    }

    private SchoolYearDAOImpl schoolYearDAOImpl = new SchoolYearDAOImpl();
    private ArrayList<SchoolYear> schoolYearList;

    private void selectSchoolYear() {
        tableModel.setRowCount(0);
        schoolYearList = schoolYearDAOImpl.selectSchoolYear();
        tableModel.setRowCount(0);
//"ID", "TUITION ID", "SEMESTER_ID", "SCHOOL YEAR", "SEMESTER", "TUITION", "SY START", "SY END"
        for (SchoolYear sy : schoolYearList) {
            tableModel.addRow(new Object[]{sy.getId(), sy.getTuition().getId(), sy.getSemester().getId(), sy.getSchoolYear(), sy.getSemester().getSemester(), sy.getTuition().getTuitionFee(), sy.getSyStart(), sy.getSyEnd()});
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jTableCourse) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getClickCount() == 1) {
                    int row = jTableCourse.getSelectedRow();
                    if (row != -1) {
//                      "ID", "TUITION ID", "SEMESTER_ID", "SCHOOL YEAR", "SEMESTER", "TUITION", "SY START", "SY END"
                        Long syId = Long.parseLong(jTableCourse.getValueAt(row, 0).toString());
                        Long tuitionId = Long.parseLong(jTableCourse.getValueAt(row, 1).toString());
                        Long semesterId = Long.parseLong(jTableCourse.getValueAt(row, 2).toString());
                        String schoolYear = jTableCourse.getValueAt(row, 3).toString();        
                        String semester = jTableCourse.getValueAt(row, 4).toString();
                        String tuition = jTableCourse.getValueAt(row, 5).toString();
                        String syStart = jTableCourse.getValueAt(row, 6).toString();
                        String syEnd = jTableCourse.getValueAt(row, 7).toString();
                        SchoolYear sy = new SchoolYear();
                        Semester sem = new Semester();
                        Tuition tui = new Tuition();
                        tui.setId(tuitionId);
                        sem.setId(semesterId);
                        sem.setSemester(semester);
                        sy.setId(syId);
                        sy.setSemester(sem);
                        sy.setTuition(tui);
                        sy.setSchoolYear(schoolYear);
                        sy.setSyStart(syStart);
                        sy.setSyEnd(syEnd);
                        this.schoolYear = sy;
                        jButtonDelete.setEnabled(true);
                        jButtonUpdate.setEnabled(true);
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
