/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.aregister.dao;

import com.aldrin.aregister.model.Semester;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface SemesterDAO {

    public Long getMaxId();

    public void addSemester(Semester semester);

    public void updateSemester(Semester semester);

    public void deleteSemester(Semester semester);

    public ArrayList<Semester> selectSemester();

    public void comboBoxSemester();

}
