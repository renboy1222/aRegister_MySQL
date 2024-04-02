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
public class SchoolYear {

    private Long id;
    private String schoolYear;
    private Semester semester;
    private Tuition tuition;
    private String syStart;
    private String syEnd;
    private Boolean deleted;
}
