/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.aregister.gui.dialog;

import com.aldrin.aregister.dao.impl.ClassEnrollDAOImpl;
import com.aldrin.aregister.dao.impl.DBConnection;
import com.aldrin.aregister.dao.impl.InstructorDAOImpl;
import com.aldrin.aregister.dao.impl.SchoolYearDAOImpl;
import com.aldrin.aregister.gui.JFrameRegister;
import com.aldrin.aregister.model.ClassEnroll;
import com.aldrin.aregister.util.ComboBoxAutoFill;
import com.aldrin.aregister.util.ComboBoxList;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author ALRIN B.C.
 */
public class JDialogEnrolledStudents extends javax.swing.JDialog implements ActionListener, MouseListener {

    private JFrameRegister jFrameRegister;

    private DecimalFormat df = new DecimalFormat("##,##0.00");
    private JTextComponent editor;

    public JDialogEnrolledStudents(JFrameRegister jFrameRegister, boolean modal) {
        super(jFrameRegister, modal);
        initComponents();
        this.jFrameRegister = jFrameRegister;
        setTable();

        jTextFieldSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        jTextFieldSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("svg/search.svg", 24, 24));

        comboBoxSchoolYear();
        jComboBoxSchoolYear.setEditable(true);
        editor = (JTextComponent) jComboBoxSchoolYear.getEditor().getEditorComponent();
        editor.setDocument(new ComboBoxAutoFill(jComboBoxSchoolYear));
        jComboBoxSchoolYear.setEditable(true);
        jComboBoxSchoolYear.addActionListener(this);

        jButtonPrint.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanelFrameContainer = new javax.swing.JPanel();
        jPanelGrandTotal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableInstructor = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jComboBoxSchoolYear = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jButtonPrint = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ENROLLED STUDENTS");

        jPanel10.setPreferredSize(new java.awt.Dimension(350, 80));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanelFrameContainer.setPreferredSize(new java.awt.Dimension(260, 200));
        jPanelFrameContainer.setLayout(new java.awt.BorderLayout());

        jPanelGrandTotal.setPreferredSize(new java.awt.Dimension(260, 0));
        jPanelGrandTotal.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTableInstructor.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableInstructor);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelGrandTotal.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(835, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(435, 50));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.add(jComboBoxSchoolYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 10, 310, 30));

        jLabel2.setText("SCHOOL YEAR:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(350, 50));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 10));

        jTextFieldSearch.setPreferredSize(new java.awt.Dimension(250, 30));
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanel7.add(jTextFieldSearch);

        jPanel4.add(jPanel7, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanelGrandTotal.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanelFrameContainer.add(jPanelGrandTotal, java.awt.BorderLayout.CENTER);

        jPanel29.setOpaque(false);
        jPanel29.setPreferredSize(new java.awt.Dimension(270, 5));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1212, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel29, java.awt.BorderLayout.NORTH);

        jPanel30.setOpaque(false);
        jPanel30.setPreferredSize(new java.awt.Dimension(270, 60));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonPrint.setIcon(new FlatSVGIcon("svg/print.svg",24,24));
        jButtonPrint.setText("Print");
        jButtonPrint.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        jPanel30.add(jButtonPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));

        jPanelFrameContainer.add(jPanel30, java.awt.BorderLayout.SOUTH);

        jPanel31.setOpaque(false);
        jPanel31.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel31, java.awt.BorderLayout.EAST);

        jPanel32.setOpaque(false);
        jPanel32.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel32, java.awt.BorderLayout.WEST);

        jPanel10.add(jPanelFrameContainer, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel10, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1228, 554));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        autoPrint();
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        String text = jTextFieldSearch.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + ",*"));
        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JComboBox<Object> jComboBoxSchoolYear;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelFrameContainer;
    private javax.swing.JPanel jPanelGrandTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableInstructor;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

    public DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"#", "SURNAME", "FIRST NAME", "MIDDLE NAME", "GENDER", "CIVIL STATUS", "CITIZENSHIP", "COURSE", "UNITS"}, 0) {
        public Class getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return Integer.class;
            }
            switch (columnIndex) {
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                case 3:
                    return String.class;
                case 4:
                    return String.class;
                case 5:
                    return String.class;
                case 6:
                    return String.class;
                case 7:
                    return String.class;
                case 8:
                    return Integer.class;
                default:
                    return String.class;
            }
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 10) {
                return false;

            } else {
                return true;
            }
        }
    };
    private TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);

    private void setTable() {
        jTableInstructor.setCellSelectionEnabled(true);
        jTableInstructor = new JTable(tableModel);
        jScrollPane1.setViewportView(jTableInstructor);
        jTableInstructor.addMouseListener(this);
        jTableInstructor.setRowSorter(sorter);
//        TableColumn hide0 = jTableInstructor.getColumnModel().getColumn(0);
//        hide0.setMinWidth(0);
//        hide0.setMaxWidth(0);
//        hide0.setPreferredWidth(0);
        TableColumn[] column = new TableColumn[100];
        column[1] = jTableInstructor.getColumnModel().getColumn(0);
        column[1].setPreferredWidth(10);

        column[1] = jTableInstructor.getColumnModel().getColumn(1);
        column[1].setPreferredWidth(130);

        column[2] = jTableInstructor.getColumnModel().getColumn(2);
        column[2].setPreferredWidth(130);

        column[3] = jTableInstructor.getColumnModel().getColumn(3);
        column[3].setPreferredWidth(130);

        column[4] = jTableInstructor.getColumnModel().getColumn(4);
        column[4].setPreferredWidth(30);

        column[5] = jTableInstructor.getColumnModel().getColumn(5);
        column[5].setPreferredWidth(100);

        column[6] = jTableInstructor.getColumnModel().getColumn(6);
        column[6].setPreferredWidth(100);

        column[7] = jTableInstructor.getColumnModel().getColumn(7);
        column[7].setPreferredWidth(230);
        
        column[8] = jTableInstructor.getColumnModel().getColumn(8);
        column[8].setPreferredWidth(20);

    }

    private InstructorDAOImpl instructorDAOImpl = new InstructorDAOImpl();

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == jComboBoxSchoolYear) {
                ComboBoxList syID = (ComboBoxList) this.jComboBoxSchoolYear.getSelectedItem();
                selectEnrolledStudents(syID.getId());
                jButtonPrint.setEnabled(true);

            }
        } catch (Exception ex) {

        }
    }
    private SchoolYearDAOImpl classEnroll = new SchoolYearDAOImpl();

    private void comboBoxSchoolYear() {
        classEnroll.comboBoxSchoolYear();
        jComboBoxSchoolYear.removeAllItems();
        for (ComboBoxList a : classEnroll.getList()) {
            this.jComboBoxSchoolYear.addItem(a);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jTableInstructor) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getClickCount() == 1) {
                    int row = jTableInstructor.getSelectedRow();
                    if (row != -1) {
                        Long classOfferId = Long.parseLong(jTableInstructor.getValueAt(row, 0).toString());
                        jButtonPrint.setEnabled(true);
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

    private ClassEnrollDAOImpl classEnrollDAOImpl = new ClassEnrollDAOImpl();
    private ArrayList<ClassEnroll> classEnrollList = new ArrayList<>();
//"STUDENT ID", "SURNAME", "FIRST NAME", "MIDDLE NAME", "GENDER", "CIVIL STATUS", "CITIZENSHIP", "COURSE","UNITS"

    private void selectEnrolledStudents(Long schoolYear) {
        tableModel.setRowCount(0);
        classEnrollList = classEnrollDAOImpl.selectEnrolledStudents(schoolYear);
        tableModel.setRowCount(0);
        for (ClassEnroll ce : classEnrollList) {
            tableModel.addRow(new Object[]{jTableInstructor.getRowCount() + 1, ce.getStudent().getSurname(), ce.getStudent().getFirstname(),
                ce.getStudent().getMiddlename(), ce.getStudent().getGender(), ce.getStudent().getCivilStatus().getCivilStatus(),
                ce.getStudent().getCitizenship().getCitizenship(), ce.getStudent().getCourse().getCourse(), ce.getClassOffer().getSubject().getUnit()});
        }
    }

    public void autoPrint() {
        try {
            DBConnection con = new DBConnection();
            con.getDBConn();
            String fullPath = System.getProperty("user.dir") + "/src/main/resources/reports/enrolledStudents.jasper";
            Map<String, Object> param = new HashMap<String, Object>();
            ComboBoxList syId = (ComboBoxList) this.jComboBoxSchoolYear.getSelectedItem();
            param.put("SYID", syId.getId());
            JasperPrint jp = JasperFillManager.fillReport(fullPath, param, con.getCon());
            JasperPrintManager.printReport(jp, true); // print to default printer
//            JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\admin\\Desktop\\userSales.pdf");
        } catch (Exception e) {
        }
    }

}