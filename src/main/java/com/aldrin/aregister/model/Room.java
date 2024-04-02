/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.model;

import com.aldrin.aregister.dao.impl.DBConnection;
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
public class Room {

    private Long id;
    private String room;
    private String location;
    private Boolean deleted;
}
