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
public class ClassOffer {

    private Long id;
    private String class_code;
    private Course course;
    private Instructor instructor;
    private Subject subject;
    private SchoolYear schoolYear;
    private Room room;
    private Day day;
    private Time time;
    private Boolean deleted;
}
