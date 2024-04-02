/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.aregister.gui.dialog.au;

import com.aldrin.aregister.dao.impl.ClassOfferDAOImpl;
import com.aldrin.aregister.dao.impl.CourseDAOImpl;
import com.aldrin.aregister.dao.impl.DayDAOImpl;
import com.aldrin.aregister.dao.impl.InstructorDAOImpl;
import com.aldrin.aregister.dao.impl.RoomDAOImpl;
import com.aldrin.aregister.dao.impl.SchoolYearDAOImpl;
import com.aldrin.aregister.dao.impl.SubjectDAOImpl;
import com.aldrin.aregister.dao.impl.TimeDAOImpl;
import com.aldrin.aregister.gui.JFrameRegister;
import com.aldrin.aregister.model.ClassOffer;
import com.aldrin.aregister.model.Course;
import com.aldrin.aregister.model.Day;
import com.aldrin.aregister.model.Instructor;
import com.aldrin.aregister.model.Room;
import com.aldrin.aregister.model.SchoolYear;
import com.aldrin.aregister.model.Subject;
import com.aldrin.aregister.model.Time;
import com.aldrin.aregister.util.ComboBoxList;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ALDRIN B. C.
 */
public class JDialogClassOfferAU extends javax.swing.JDialog implements ActionListener {


    private JFrameRegister jFrameRegister;
    private ClassOffer classOffer = new ClassOffer();
    static String title;
    private ClassOfferDAOImpl classOfferDAOImpl = new ClassOfferDAOImpl();

    private SchoolYearDAOImpl schoolYearDAOImpl = new SchoolYearDAOImpl();
    private SubjectDAOImpl subjectDAOImpl = new SubjectDAOImpl();
    private InstructorDAOImpl instructorDAOImp = new InstructorDAOImpl();
    private CourseDAOImpl courseDAOImpl = new CourseDAOImpl();
    private RoomDAOImpl roomDAOImpl = new RoomDAOImpl();
    private DayDAOImpl dayDAOImpl = new DayDAOImpl();
    private TimeDAOImpl timeDAOImpl = new TimeDAOImpl();

    public JDialogClassOfferAU(JFrameRegister jFrameRegister, boolean modal) {
        super(jFrameRegister, modal);
        initComponents();
        setTitle("New");
        this.title = "New";
        jButton1.setIcon(new FlatSVGIcon("svg/save.svg", 24, 24));
        jTextFieldClassCode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CLASS CODE");
        jTextFieldClassCode.setText(classOffer.getClass_code());
        comboBoxSchoolYear();
        comboBoxSubject();
        comboBoxInstructor();
        comboBoxCourse();
        comboBoxRoom();
        comboBoxDay();
        comboBoxTime();
        jComboBoxInstructor.addActionListener(this);
        SchoolYear sy = schoolYearDAOImpl.getCurrentSchoolYearIDAndTuitionID();
        for (ComboBoxList a : this.schoolYearDAOImpl.getList()) {
            a.setSelectedId(schoolYearDAOImpl.getList(), String.valueOf(sy.getId()), jComboBoxSchoolYear);
        }

    }

    public JDialogClassOfferAU(JFrameRegister jFrameRegister, boolean modal, ClassOffer classOffer, String title) {
        super(jFrameRegister, modal);
        initComponents();
        setTitle(title);
        this.title = title;
        this.classOffer = classOffer;
        jButton1.setIcon(new FlatSVGIcon("svg/edit.svg", 24, 24));
        jButton1.setText(title);
        jTextFieldClassCode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CLASS CODE");
        jTextFieldClassCode.setText(classOffer.getClass_code());
        comboBoxSchoolYear();
        comboBoxSubject();
        comboBoxInstructor();
        comboBoxCourse();
        comboBoxRoom();
        comboBoxDay();
        comboBoxTime();
        SchoolYear sy = schoolYearDAOImpl.getCurrentSchoolYearIDAndTuitionID();
        for (ComboBoxList a : this.schoolYearDAOImpl.getList()) {
            a.setSelectedId(schoolYearDAOImpl.getList(), String.valueOf(sy.getId()), jComboBoxSchoolYear);
        }
        for (ComboBoxList a : this.subjectDAOImpl.getList()) {
            a.setSelectedId(subjectDAOImpl.getList(), String.valueOf(classOffer.getSubject().getId()), jComboBoxSubject);
        }
        for (ComboBoxList a : this.courseDAOImpl.getList()) {
            a.setSelectedId(courseDAOImpl.getList(), String.valueOf(classOffer.getCourse().getId()), jComboBoxCourse);
        }
        for (ComboBoxList a : this.instructorDAOImp.getList()) {
            a.setSelectedId(instructorDAOImp.getList(), String.valueOf(classOffer.getInstructor().getId()), jComboBoxInstructor);
        }
        for (ComboBoxList a : this.roomDAOImpl.getList()) {
            a.setSelectedId(roomDAOImpl.getList(), String.valueOf(classOffer.getRoom().getId()), jComboBoxRoom);
        }
        for (ComboBoxList a : this.dayDAOImpl.getList()) {
            a.setSelectedId(dayDAOImpl.getList(), String.valueOf(classOffer.getDay().getId()), jComboBoxDay);
        }
        for (ComboBoxList a : this.timeDAOImpl.getList()) {
            a.setSelectedId(timeDAOImpl.getList(), String.valueOf(classOffer.getTime().getId()), jComboBoxTime);
        }
        displayPicture(classOffer.getInstructor());
    }

    public JDialogClassOfferAU(JFrameRegister jFrameRegister, boolean modal, String title, ClassOffer classOffer) {
        super(jFrameRegister, modal);
        initComponents();
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(187, 187, 187));
        setTitle(title);
        this.classOffer = classOffer;
        this.title = title;
        jButton1.setIcon(new FlatSVGIcon("svg/delete.svg", 24, 24));
        jButton1.setText(title);
        jTextFieldClassCode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CLASS CODE");
        jTextFieldClassCode.setText(classOffer.getClass_code());
        SchoolYear sy = schoolYearDAOImpl.getCurrentSchoolYearIDAndTuitionID();
        for (ComboBoxList a : this.schoolYearDAOImpl.getList()) {
            a.setSelectedId(schoolYearDAOImpl.getList(), String.valueOf(sy.getId()), jComboBoxSchoolYear);
        }
        for (ComboBoxList a : this.subjectDAOImpl.getList()) {
            a.setSelectedId(subjectDAOImpl.getList(), String.valueOf(classOffer.getSubject().getId()), jComboBoxSubject);
        }
        for (ComboBoxList a : this.courseDAOImpl.getList()) {
            a.setSelectedId(courseDAOImpl.getList(), String.valueOf(classOffer.getCourse().getId()), jComboBoxCourse);
        }
        for (ComboBoxList a : this.instructorDAOImp.getList()) {
            a.setSelectedId(instructorDAOImp.getList(), String.valueOf(classOffer.getInstructor().getId()), jComboBoxInstructor);
        }
        for (ComboBoxList a : this.roomDAOImpl.getList()) {
            a.setSelectedId(roomDAOImpl.getList(), String.valueOf(classOffer.getRoom().getId()), jComboBoxRoom);
        }
        for (ComboBoxList a : this.dayDAOImpl.getList()) {
            a.setSelectedId(dayDAOImpl.getList(), String.valueOf(classOffer.getDay().getId()), jComboBoxDay);
        }
        for (ComboBoxList a : this.timeDAOImpl.getList()) {
            a.setSelectedId(timeDAOImpl.getList(), String.valueOf(classOffer.getTime().getId()), jComboBoxTime);
        }
        displayPicture(classOffer.getInstructor());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldClassCode = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxInstructor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTime = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxSchoolYear = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxSubject = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxCourse = new javax.swing.JComboBox<>();
        jComboBoxRoom = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxDay = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelPicture = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("INSTRUCTOR:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 90, 30));
        getContentPane().add(jTextFieldClassCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 240, 30));

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 140, 30));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("SCHOOL YEAR:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 20, 90, 30));

        getContentPane().add(jComboBoxInstructor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 240, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("TIME");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 300, 90, 30));

        getContentPane().add(jComboBoxTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 240, 30));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("SUBJECT:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 100, 90, 30));

        getContentPane().add(jComboBoxSchoolYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 240, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("CLASS CODE:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 90, 30));

        getContentPane().add(jComboBoxSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 240, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("COURSE:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 180, 90, 30));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("ROOM:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 220, 90, 30));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ROOM:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 220, 90, 30));

        getContentPane().add(jComboBoxCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 240, 30));

        getContentPane().add(jComboBoxRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 240, 30));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("DAY:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 260, 90, 30));

        getContentPane().add(jComboBoxDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 240, 30));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "INSTRUCTOR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
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
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 15, 210, 230));

        setSize(new java.awt.Dimension(605, 437));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.title.equals("New")) {
            int response = JOptionPane.showConfirmDialog(jFrameRegister, "Are you sure to save " + jTextFieldClassCode.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                this.classOffer.setId(null);
                ComboBoxList schoolYearId = (ComboBoxList) this.jComboBoxSchoolYear.getSelectedItem();
                SchoolYear schoolYear = new SchoolYear();
                schoolYear.setId(schoolYearId.getId());
                ComboBoxList subjectId = (ComboBoxList) this.jComboBoxSubject.getSelectedItem();
                Subject subject = new Subject();
                subject.setId(subjectId.getId());
                ComboBoxList instructorId = (ComboBoxList) this.jComboBoxInstructor.getSelectedItem();
                Instructor instructor = new Instructor();
                instructor.setId(instructorId.getId());
                ComboBoxList courseId = (ComboBoxList) this.jComboBoxCourse.getSelectedItem();
                Course course = new Course();
                course.setId(courseId.getId());
                ComboBoxList roomId = (ComboBoxList) this.jComboBoxRoom.getSelectedItem();
                Room room = new Room();
                room.setId(roomId.getId());
                ComboBoxList dayId = (ComboBoxList) this.jComboBoxDay.getSelectedItem();
                Day day = new Day();
                day.setId(dayId.getId());
                ComboBoxList timeId = (ComboBoxList) this.jComboBoxTime.getSelectedItem();
                Time time = new Time();
                time.setId(timeId.getId());
                this.classOffer.setSchoolYear(schoolYear);
                this.classOffer.setClass_code(jTextFieldClassCode.getText());
                this.classOffer.setSubject(subject);
                this.classOffer.setInstructor(instructor);
                this.classOffer.setCourse(course);
                this.classOffer.setRoom(room);
                this.classOffer.setDay(day);
                this.classOffer.setTime(time);
                classOfferDAOImpl.addClassOffer(classOffer);
                this.dispose();
            }
        } else if (this.title.equals("Update")) {
            int response = JOptionPane.showConfirmDialog(jFrameRegister, "Are you sure to update " + jTextFieldClassCode.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                ComboBoxList schoolYearId = (ComboBoxList) this.jComboBoxSchoolYear.getSelectedItem();
                SchoolYear schoolYear = new SchoolYear();
                schoolYear.setId(schoolYearId.getId());
                ComboBoxList subjectId = (ComboBoxList) this.jComboBoxSubject.getSelectedItem();
                Subject subject = new Subject();
                subject.setId(subjectId.getId());
                ComboBoxList instructorId = (ComboBoxList) this.jComboBoxInstructor.getSelectedItem();
                Instructor instructor = new Instructor();
                instructor.setId(instructorId.getId());
                ComboBoxList courseId = (ComboBoxList) this.jComboBoxCourse.getSelectedItem();
                Course course = new Course();
                course.setId(courseId.getId());
                ComboBoxList roomId = (ComboBoxList) this.jComboBoxRoom.getSelectedItem();
                Room room = new Room();
                room.setId(roomId.getId());
                ComboBoxList dayId = (ComboBoxList) this.jComboBoxDay.getSelectedItem();
                Day day = new Day();
                day.setId(dayId.getId());
                ComboBoxList timeId = (ComboBoxList) this.jComboBoxTime.getSelectedItem();
                Time time = new Time();
                time.setId(timeId.getId());
                this.classOffer.setSchoolYear(schoolYear);
                this.classOffer.setClass_code(jTextFieldClassCode.getText());
                this.classOffer.setSubject(subject);
                this.classOffer.setInstructor(instructor);
                this.classOffer.setCourse(course);
                this.classOffer.setRoom(room);
                this.classOffer.setDay(day);
                this.classOffer.setTime(time);
                classOfferDAOImpl.updateClassOffer(classOffer);
                this.dispose();
            }
        } else if (this.title.equals("Delete")) {
            int response = JOptionPane.showConfirmDialog(jFrameRegister, "Are you sure to delete " + jTextFieldClassCode.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                classOfferDAOImpl.deleteClassOffer(classOffer);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabelPictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPictureMouseClicked

    }//GEN-LAST:event_jLabelPictureMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<Object> jComboBoxCourse;
    private javax.swing.JComboBox<Object> jComboBoxDay;
    private javax.swing.JComboBox<Object> jComboBoxInstructor;
    private javax.swing.JComboBox<Object> jComboBoxRoom;
    private javax.swing.JComboBox<Object> jComboBoxSchoolYear;
    private javax.swing.JComboBox<Object> jComboBoxSubject;
    private javax.swing.JComboBox<Object> jComboBoxTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelPicture;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextFieldClassCode;
    // End of variables declaration//GEN-END:variables
    private void comboBoxSchoolYear() {
        schoolYearDAOImpl.comboBoxSchoolYear();
        jComboBoxSchoolYear.removeAllItems();
        for (ComboBoxList a : schoolYearDAOImpl.getList()) {
            this.jComboBoxSchoolYear.addItem(a);
        }
    }

    private void comboBoxSubject() {
        subjectDAOImpl.comboBoxSubject();
        jComboBoxSubject.removeAllItems();
        for (ComboBoxList a : subjectDAOImpl.getList()) {
            this.jComboBoxSubject.addItem(a);
        }
    }

    private void comboBoxInstructor() {
        instructorDAOImp.comboBoxInstructor();
        jComboBoxInstructor.removeAllItems();
        for (ComboBoxList a : instructorDAOImp.getList()) {
            this.jComboBoxInstructor.addItem(a);
        }
    }

    private void comboBoxCourse() {
        courseDAOImpl.comboBoxCourse();
        jComboBoxCourse.removeAllItems();
        for (ComboBoxList a : courseDAOImpl.getList()) {
            this.jComboBoxCourse.addItem(a);
        }
    }

    private void comboBoxRoom() {
        roomDAOImpl.comboBoxRoom();
        jComboBoxRoom.removeAllItems();
        for (ComboBoxList a : roomDAOImpl.getList()) {
            this.jComboBoxRoom.addItem(a);
        }
    }

    private void comboBoxDay() {
        dayDAOImpl.comboBoxDay();
        jComboBoxDay.removeAllItems();
        for (ComboBoxList a : dayDAOImpl.getList()) {
            this.jComboBoxDay.addItem(a);
        }
    }

    private void comboBoxTime() {
        timeDAOImpl.comboBoxTime();
        jComboBoxTime.removeAllItems();
        for (ComboBoxList a : timeDAOImpl.getList()) {
            this.jComboBoxTime.addItem(a);
        }
    }

    int IMG_WIDTH = 200;
    int IMG_HEIGHT = 200;

    private void displayPicture(Instructor instructor) {
        try {
            byte[] imageData = instructor.getPhoto();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ComboBoxList instructorId = (ComboBoxList) this.jComboBoxInstructor.getSelectedItem();
            Instructor instructor = new Instructor();
            instructor.setId(instructorId.getId());
            instructor = instructorDAOImp.findPhotoByInstructorId(instructor);
            displayPicture(instructor);
        } catch (Exception ex) {

        }
    }

}
