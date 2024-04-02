/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.aregister.dao;

import com.aldrin.aregister.model.Time;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface TimeDAO {

    public Long getMaxId();

    public void addTime(Time time);

    public void updateTime(Time time);

    public void deleteTime(Time time);

    public ArrayList<Time> selectTime();

    public void comboBoxTime();

}
