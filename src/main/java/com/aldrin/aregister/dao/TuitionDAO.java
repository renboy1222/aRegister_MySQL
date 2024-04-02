/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.aregister.dao;

import com.aldrin.aregister.model.Tuition;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface TuitionDAO {

    public Long getMaxId();

    public void addTuition(Tuition tuition);

    public void updateTuition(Tuition tuition);

    public void deleteTuition(Tuition tuition);

    public ArrayList<Tuition> selectTuition();

    public void comboBoxTuition();

}
