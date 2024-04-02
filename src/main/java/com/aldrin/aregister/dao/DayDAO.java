/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.aregister.dao;

import com.aldrin.aregister.model.Day;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface DayDAO {

    public Long getMaxId();

    public void addDay(Day day);

    public void updateDay(Day day);

    public void deleteDay(Day day);

    public ArrayList<Day> selectDay();

    public void comboBoxDay();

}
