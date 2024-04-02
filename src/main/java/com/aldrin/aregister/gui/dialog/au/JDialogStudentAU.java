/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.aregister.gui.dialog.au;

import com.aldrin.aregister.ARegister;
import com.aldrin.aregister.dao.impl.CitizenshipDAOImpl;
import com.aldrin.aregister.dao.impl.CivilStatusDAOImpl;
import com.aldrin.aregister.dao.impl.CourseDAOImpl;
import com.aldrin.aregister.dao.impl.StudentDAOImpl;
import com.aldrin.aregister.gui.JFrameRegister;
import com.aldrin.aregister.model.Citizenship;
import com.aldrin.aregister.model.CivilStatus;
import com.aldrin.aregister.model.Course;
import com.aldrin.aregister.model.Student;
import com.aldrin.aregister.util.ComboBoxList;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ALDRIN B. C.
 */
public class JDialogStudentAU extends javax.swing.JDialog {

    private StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
    private CourseDAOImpl courseDAOImpl = new CourseDAOImpl();
    private CivilStatusDAOImpl civilStatusDAOImpl = new CivilStatusDAOImpl();
    private CitizenshipDAOImpl citizenshipDAOImpl = new CitizenshipDAOImpl();

    private JFrameRegister jFrameSariPOS;


    private Student student = new Student();
    static String title = "";

    public JDialogStudentAU(JFrameRegister jFrameRegisteer, boolean modal) {
        super(jFrameRegisteer, modal);
        initComponents();
        this.jFrameSariPOS = jFrameRegisteer;
        setTitle("NEW STUDENT");
        this.title = "New";
        jTextFieldStudentId.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "STUDENT ID");
        jTextFieldFirstname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "FIRST NAME");
        jTextFieldMiddlename.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MIDDLE NAME");
        jTextFieldSurname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SURNAME");
        jTextFieldMobile.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MOBILE");
        jTextFieldEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "EMAIL");
        jTextFieldAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ADDRESS");
        comboBoxCourse();
        comboBoxCivilStatus();
        comboBoxCitizenship();
        jButton1.setIcon(new FlatSVGIcon("svg/save.svg", 24, 24));
        jButton1.setText("Save");

    }

    public JDialogStudentAU(JFrameRegister jFrameRegister, boolean modal, Student student, String title) {
        super(jFrameRegister, modal);
        initComponents();
        setTitle("UPDATE STUDENT");
        this.student = student;
        this.title = title;
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(187, 187, 187));
        jButton1.setIcon(new FlatSVGIcon("svg/edit.svg", 24, 24));
        jButton1.setText("Update");
        comboBoxCourse();
        comboBoxCivilStatus();
        comboBoxCitizenship();

        jTextFieldStudentId.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "STUDENT ID");
        jTextFieldFirstname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "FIRST NAME");
        jTextFieldMiddlename.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MIDDLE NAME");
        jTextFieldSurname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SURNAME");
        jTextFieldMobile.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MOBILE");
        jTextFieldEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "EMAIL");
        jTextFieldAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ADDRESS");

        jTextFieldStudentId.setText(student.getStudentId());
        jTextFieldFirstname.setText(student.getFirstname());
        jTextFieldMiddlename.setText(student.getMiddlename());
        jTextFieldSurname.setText(student.getSurname());
        jTextFieldMobile.setText(student.getMobileNo());
        jTextFieldEmail.setText(student.getEmail());
        jTextFieldAddress.setText(student.getAddress());

        for (ComboBoxList a : this.courseDAOImpl.getList()) {
            a.setSelectedId(courseDAOImpl.getList(), String.valueOf(student.getCourse().getId()), jComboBoxCourse);
        }
        for (ComboBoxList a : this.civilStatusDAOImpl.getList()) {
            a.setSelectedId(civilStatusDAOImpl.getList(), String.valueOf(student.getCivilStatus().getId()), jComboBoxCivilStatus);
        }
        for (ComboBoxList a : this.citizenshipDAOImpl.getList()) {
            a.setSelectedId(citizenshipDAOImpl.getList(), String.valueOf(student.getCitizenship().getId()), jComboBoxCitizenship);
        }
        if (student.getGender().equals(jComboBoxGender.getSelectedItem().toString())) {
            jComboBoxGender.setSelectedIndex(0);
        } else {
            jComboBoxGender.setSelectedIndex(1);
        }
        jDateChooserDateOfBirth.setDate(new Date(Integer.parseInt(student.getDateOfBirth().toString().substring(0, 4)) - 1900, Integer.parseInt(student.getDateOfBirth().toString().substring(5, 7)) - 1, Integer.parseInt(student.getDateOfBirth().substring(8))));

        displayPicture(student);

    }

    public JDialogStudentAU(JFrameRegister jFrameRegister, boolean modal, String title, Student student) {
        super(jFrameRegister, modal);
        initComponents();
        setTitle("DELETE STUDENT ACCOUNT");
        jButton1.setIcon(new FlatSVGIcon("svg/delete.svg", 24, 24));
        jButton1.setText("Delete");
        this.student = student;
        this.title = title;
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(187, 187, 187));

        comboBoxCourse();
        comboBoxCivilStatus();
        comboBoxCitizenship();
        jTextFieldStudentId.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "STUDENT ID");
        jTextFieldFirstname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "FIRST NAME");
        jTextFieldMiddlename.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MIDDLE NAME");
        jTextFieldSurname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SURNAME");
        jTextFieldMobile.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MOBILE");
        jTextFieldEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "EMAIL");
        jTextFieldAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ADDRESS");

        jTextFieldStudentId.setText(student.getStudentId());
        jTextFieldFirstname.setText(student.getFirstname());
        jTextFieldMiddlename.setText(student.getMiddlename());
        jTextFieldSurname.setText(student.getSurname());
        jTextFieldMobile.setText(student.getMobileNo());
        jTextFieldEmail.setText(student.getEmail());
        jTextFieldAddress.setText(student.getAddress());

        for (ComboBoxList a : this.courseDAOImpl.getList()) {
            a.setSelectedId(courseDAOImpl.getList(), String.valueOf(student.getCourse().getId()), jComboBoxCourse);
        }
        for (ComboBoxList a : this.civilStatusDAOImpl.getList()) {
            a.setSelectedId(civilStatusDAOImpl.getList(), String.valueOf(student.getCivilStatus().getId()), jComboBoxCivilStatus);
        }
        for (ComboBoxList a : this.citizenshipDAOImpl.getList()) {
            a.setSelectedId(citizenshipDAOImpl.getList(), String.valueOf(student.getCitizenship().getId()), jComboBoxCitizenship);
        }
        if (student.getGender().equals(jComboBoxGender.getSelectedItem().toString())) {
            jComboBoxGender.setSelectedIndex(0);
        } else {
            jComboBoxGender.setSelectedIndex(1);
        }
        jDateChooserDateOfBirth.setDate(new Date(Integer.parseInt(student.getDateOfBirth().toString().substring(0, 4)) - 1900, Integer.parseInt(student.getDateOfBirth().toString().substring(5, 7)) - 1, Integer.parseInt(student.getDateOfBirth().substring(8))));

        displayPicture(student);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelPicture = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldFirstname = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTextFieldSurname = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextFieldMiddlename = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jComboBoxCourse = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jComboBoxGender = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jComboBoxCivilStatus = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jDateChooserDateOfBirth = new com.toedter.calendar.JDateChooser();
        jPanel13 = new javax.swing.JPanel();
        jTextFieldMobile = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jTextFieldEmail = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jComboBoxCitizenship = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jTextFieldAddress = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jTextFieldStudentId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 220, 80, 30));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "PHOTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(215, 225));
        jPanel1.setMinimumSize(new java.awt.Dimension(215, 225));
        jPanel1.setPreferredSize(new java.awt.Dimension(215, 225));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabelPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/no photo.jpg"))); // NOI18N
        jLabelPicture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPictureMouseClicked(evt);
            }
        });
        jPanel2.add(jLabelPicture, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(210, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("FIRST NAME"));
        jPanel6.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jTextFieldFirstname.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel6.add(jTextFieldFirstname, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("SURNAME"));
        jPanel7.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jTextFieldSurname.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel7.add(jTextFieldSurname, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("MIDDLE NAME"));
        jPanel8.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jTextFieldMiddlename.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel8.add(jTextFieldMiddlename, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel8);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("COURSE"));
        jPanel9.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jComboBoxCourse.setPreferredSize(new java.awt.Dimension(72, 32));
        jPanel9.add(jComboBoxCourse, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel9);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("GENDER"));
        jPanel10.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE", " " }));
        jComboBoxGender.setPreferredSize(new java.awt.Dimension(72, 32));
        jPanel10.add(jComboBoxGender, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel10);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("CIVIL STATUS"));
        jPanel11.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jComboBoxCivilStatus.setPreferredSize(new java.awt.Dimension(72, 32));
        jPanel11.add(jComboBoxCivilStatus, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel11);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("DATE OF BIRTH"));
        jPanel12.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jDateChooserDateOfBirth.setDateFormatString("MMM dd, yyyy");
        jPanel12.add(jDateChooserDateOfBirth, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel12);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("MOBILE"));
        jPanel13.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel13.setLayout(new java.awt.BorderLayout());
        jPanel13.add(jTextFieldMobile, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel13);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("EMAIL"));
        jPanel14.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel14.setLayout(new java.awt.BorderLayout());
        jPanel14.add(jTextFieldEmail, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel14);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("CITIZENSHIP"));
        jPanel15.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel15.add(jComboBoxCitizenship, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel15);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("ADDRESS"));
        jPanel16.setPreferredSize(new java.awt.Dimension(405, 50));
        jPanel16.setLayout(new java.awt.BorderLayout());
        jPanel16.add(jTextFieldAddress, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel16);

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 620, 220));

        jButton1.setText("Save");
        jButton1.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 840, 50));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("STUDENT ID"));
        jPanel17.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jTextFieldStudentId.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel17.add(jTextFieldStudentId, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 40, 405, 50));

        setSize(new java.awt.Dimension(880, 410));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.title.equals("New")) {
            int response = JOptionPane.showConfirmDialog(jFrameSariPOS, "Are you sure to save " + jTextFieldFirstname.getText() + " " + jTextFieldSurname.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                ComboBoxList courseId = (ComboBoxList) this.jComboBoxCourse.getSelectedItem();
                Course course = new Course();
                ComboBoxList civilStatusId = (ComboBoxList) this.jComboBoxCivilStatus.getSelectedItem();
                CivilStatus civilStatus = new CivilStatus();
                ComboBoxList citizenshipId = (ComboBoxList) this.jComboBoxCitizenship.getSelectedItem();
                Citizenship citizenship = new Citizenship();

                course.setId(courseId.getId());
                civilStatus.setId(civilStatusId.getId());
                citizenship.setId(citizenshipId.getId());
                this.student.setStudentId(jTextFieldStudentId.getText());
                this.student.setFirstname(jTextFieldFirstname.getText());
                this.student.setSurname(jTextFieldSurname.getText());
                this.student.setMiddlename(jTextFieldMiddlename.getText());
                this.student.setGender(jComboBoxGender.getSelectedItem().toString());
                this.student.setDateOfBirth(new java.sql.Date(jDateChooserDateOfBirth.getDate().getTime()).toString());
                this.student.setMobileNo(jTextFieldMobile.getText());
                this.student.setEmail(jTextFieldEmail.getText());
                this.student.setAddress(jTextFieldAddress.getText());
                this.student.setCourse(course);
                this.student.setCivilStatus(civilStatus);
                this.student.setCitizenship(citizenship);
                try {
                    validatePhoto();
                } catch (URISyntaxException ex) {
                    Logger.getLogger(JDialogStudentAU.class.getName()).log(Level.SEVERE, null, ex);
                }
                studentDAOImpl.addStudent(student);
                this.dispose();
            }
        } else if (this.title.equals("Update")) {
            int response = JOptionPane.showConfirmDialog(jFrameSariPOS, "Are you sure to update " + jTextFieldFirstname.getText() + " " + jTextFieldSurname.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                ComboBoxList courseId = (ComboBoxList) this.jComboBoxCourse.getSelectedItem();
                Course course = new Course();
                ComboBoxList civilStatusId = (ComboBoxList) this.jComboBoxCivilStatus.getSelectedItem();
                CivilStatus civilStatus = new CivilStatus();
                ComboBoxList citizenshipId = (ComboBoxList) this.jComboBoxCitizenship.getSelectedItem();
                Citizenship citizenship = new Citizenship();

                course.setId(courseId.getId());
                civilStatus.setId(civilStatusId.getId());
                citizenship.setId(citizenshipId.getId());
                this.student.setStudentId(jTextFieldStudentId.getText());
                this.student.setFirstname(jTextFieldFirstname.getText());
                this.student.setSurname(jTextFieldSurname.getText());
                this.student.setMiddlename(jTextFieldMiddlename.getText());
                this.student.setGender(jComboBoxGender.getSelectedItem().toString());
                this.student.setDateOfBirth(new java.sql.Date(jDateChooserDateOfBirth.getDate().getTime()).toString());
                this.student.setMobileNo(jTextFieldMobile.getText());
                this.student.setEmail(jTextFieldEmail.getText());
                this.student.setAddress(jTextFieldAddress.getText());
                this.student.setCourse(course);
                this.student.setCivilStatus(civilStatus);
                this.student.setCitizenship(citizenship);
                try {
                    validatePhoto();
                } catch (URISyntaxException ex) {
                    Logger.getLogger(JDialogStudentAU.class.getName()).log(Level.SEVERE, null, ex);
                }
                studentDAOImpl.updateStudent(student);
                this.dispose();
            }
        } else if (this.title.equals("Delete")) {
            int response = JOptionPane.showConfirmDialog(jFrameSariPOS, "Are you sure to delete " + jTextFieldFirstname.getText() + " " + jTextFieldSurname.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                studentDAOImpl.deleteStudent(student);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabelPictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPictureMouseClicked

        browse();
    }//GEN-LAST:event_jLabelPictureMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<Object> jComboBoxCitizenship;
    private javax.swing.JComboBox<Object> jComboBoxCivilStatus;
    private javax.swing.JComboBox<Object> jComboBoxCourse;
    private javax.swing.JComboBox<Object> jComboBoxGender;
    private com.toedter.calendar.JDateChooser jDateChooserDateOfBirth;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelPicture;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFirstname;
    private javax.swing.JTextField jTextFieldMiddlename;
    private javax.swing.JTextField jTextFieldMobile;
    private javax.swing.JTextField jTextFieldStudentId;
    private javax.swing.JTextField jTextFieldSurname;
    // End of variables declaration//GEN-END:variables

    private void comboBoxCourse() {
        courseDAOImpl.comboBoxCourse();
        jComboBoxCourse.removeAllItems();
        for (ComboBoxList a : courseDAOImpl.getList()) {
            this.jComboBoxCourse.addItem(a);
        }
    }

    private void comboBoxCivilStatus() {
        civilStatusDAOImpl.comboBoxCivilStatus();
        jComboBoxCivilStatus.removeAllItems();
        for (ComboBoxList a : civilStatusDAOImpl.getList()) {
            this.jComboBoxCivilStatus.addItem(a);
        }
    }

    private void comboBoxCitizenship() {
        citizenshipDAOImpl.comboBoxCitizenship();
        jComboBoxCitizenship.removeAllItems();
        for (ComboBoxList a : citizenshipDAOImpl.getList()) {
            this.jComboBoxCitizenship.addItem(a);
        }
    }

    private File pictureFile = null;

    private void browse() {
        try {
            int returnVal = jFileChooser1.showOpenDialog(this);
            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                pictureFile = jFileChooser1.getSelectedFile();
                uploadPhoto(pictureFile);
                int IMG_WIDTH = jLabelPicture.getWidth();
                int IMG_HEIGHT = jLabelPicture.getHeight();

                try {
                    BufferedImage originalImage = ImageIO.read(pictureFile);
                    int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
                    Graphics2D g = resizedImage.createGraphics();
                    g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
                    g.dispose();
                    g.setComposite(AlphaComposite.Src);

                    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

                    jLabelPicture.setIcon(new ImageIcon(resizedImage)); //to eliminate .jpeg from picture filename
                    ImageIO.write(resizedImage, "png", new File(ARegister.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "\\images\\model.jpg"));

                } catch (final IOException ex) {

                }

            } else {
                File defaultDirectory = new File(System.getProperty("user.home"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadPhoto(File file) {
        if (file != null) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] imageData = new byte[(int) file.length()];
                fis.read(imageData);
                student.setPhoto(imageData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    int IMG_WIDTH = 200;
    int IMG_HEIGHT = 200;

    private void displayPicture(Student student) {
        try {
            byte[] imageData = student.getPhoto();
            ImageIcon imageIcon = new ImageIcon(imageData);

            Image image = imageIcon.getImage();
            int type = BufferedImage.TYPE_INT_ARGB;

            BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(image, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
            g.dispose();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            jLabelPicture.setIcon(new ImageIcon(resizedImage));//image to label

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validatePhoto() throws URISyntaxException {
        if (student.getPhoto() == null) {
            File targetClassesDir = new File(ARegister.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "\\images\\no photo.jpg");
            try {
                FileInputStream fis = new FileInputStream(targetClassesDir);
                byte[] imageData = new byte[(int) targetClassesDir.length()];
                fis.read(imageData);
                student.setPhoto(imageData);
            } catch (Exception e) {
                System.out.println("default of no photo is error");
            }
        }
    }

}
