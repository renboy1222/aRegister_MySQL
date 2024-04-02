/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.aregister.dao;

import com.aldrin.aregister.model.Student;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface StudentDAO {

    public Long getMaxId();

    public void addStudent(Student student);

    public void updateStudent(Student student);

    public void deleteStudent(Student student);

    public ArrayList<Student> selectStudent();

    public void comboBoxStudent();

    public Student findPhotoByStudentId(Student student);
    
    public void comboBoxStudentID();
    
    public Student selectStudentInfo(Student student) ;
    
    public Integer selectStudentAge(Student student) ;

}
