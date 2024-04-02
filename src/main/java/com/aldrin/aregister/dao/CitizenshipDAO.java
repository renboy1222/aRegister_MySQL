/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.aregister.dao;

import com.aldrin.aregister.model.Citizenship;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface CitizenshipDAO {

    public Long getMaxId();

    public void addCitizenship(Citizenship citizenship);

    public void updateCitizenship(Citizenship citizenship);

    public void deleteCitizenship(Citizenship citizenship);

    public ArrayList<Citizenship> selectCitizenship();

    public void comboBoxCitizenship();

}
