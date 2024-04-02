/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.aregister.dao;

import com.aldrin.aregister.model.ClassOffer;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface ClassOfferDAO {

    public Long getMaxId();

    public void addClassOffer(ClassOffer classOffer);

    public void updateClassOffer(ClassOffer classOffer);

    public void deleteClassOffer(ClassOffer classOffer);

    public void comboBoxClassOffer();

    public ClassOffer selectClassOffer(ClassOffer student);
    
    public ArrayList<ClassOffer> selectClassOffer(Long syId);
    
    public Long getCurrentClassOfferId();

}
