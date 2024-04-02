/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ALRIN B.C.
 */
@Setter
@Getter
@ToString
//    ALDRIN.CLASS_ENROLL.ID, 
//    ALDRIN.CLASS_ENROLL.CLASS_OFFER_ID, 
//    ALDRIN.CLASS_ENROLL.STUDENT_ID, 
//    ALDRIN.CLASS_ENROLL.TUITION_ID, 
//    ALDRIN.CLASS_ENROLL.USER_ID 
public class ClassEnroll {

    private Long id;
    private ClassOffer classOffer;
    private Student student;
    private Tuition tuition;
    private UserAccount userAccount;
    private Boolean dropSubject;
    private Boolean deleted;

    private int noOfStudents;

}
