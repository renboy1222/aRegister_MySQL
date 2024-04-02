/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.aregister.dao;

import com.aldrin.aregister.model.ClassEnroll;
import com.aldrin.aregister.model.SchoolYear;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface ClassEnrollDAO {

    public Long getMaxId();

    public void addClassEnroll(ClassEnroll classEnroll);

    public void updateClassEnroll(ClassEnroll classEnroll);

    public void deleteClassEnroll(ClassEnroll classEnroll);

    public ArrayList<ClassEnroll> selectClassEnroll(Long syID);

    public void comboBoxClassEnroll();

    public ArrayList<ClassEnroll> selectStudentIFEnrolled(ClassEnroll classEnroll, SchoolYear schoolYear);

    public Long selectStudentSubject(ClassEnroll classEnroll);

    public Float selectStudentByTuition(Long studentID);

    public Boolean selectDisableRegister(Long studentID, Long classOfferId);

    public void updateDropSubject(Long studentId, Long classOfferId);

    public void comboBoxInstructorSubject(Long instructorId);

    public void comboBoxInstructor();

    public ArrayList<ClassEnroll> selectInstructorsSubject();
    
    public ArrayList<ClassEnroll> selectEnrolledStudents(Long schoolYear);
     
    public ArrayList<ClassEnroll> selectTotalTuition(Long schoolYear);
    
    public ArrayList<ClassEnroll> selectInstructorsSubject(Long instructorId, Long syId);
    
    public void comboBoxInstructorSubject(Long instructorId, Long syId);

}
