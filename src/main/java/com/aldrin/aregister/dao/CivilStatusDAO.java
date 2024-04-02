/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.aregister.dao;

import com.aldrin.aregister.model.CivilStatus;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface CivilStatusDAO {

    public Long getMaxId();

    public void addCivilStatus(CivilStatus civilStatus);

    public void updateCivilStatus(CivilStatus civilStatus);

    public void deleteCivilStatus(CivilStatus civilStatus);

    public ArrayList<CivilStatus> selectCivilStatus();

    public void comboBoxCivilStatus();

}
