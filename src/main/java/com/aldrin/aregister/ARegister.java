/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.aldrin.aregister;

import com.aldrin.aregister.controller.AppController;
import com.aldrin.aregister.gui.JFrameLogin;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

/**
 *
 * @author ALRIN B.C.
 */
public class ARegister {

    public static void main(String[] args) {
//        FlatLightLaf.setup();
//        UIManager.put("Button.arc", 8);//JButton
//        UIManager.put("ProgressBar.arc", 999);//JProgressBar
//        UIManager.put("TextComponent.arc", 8);//JTextField,JPasswordField,JFormattedTextField
//        UIManager.put("CheckBox", 999);//JCheckBox
//        UIManager.put("Component.arc", 8);//JComboBox,JSpinner
//
//        UIManager.put("Component.innerFocusWidth", 2);//JComboBox, JTextField,JPasswordField,JFormattedTextField,JSpinner
//        UIManager.put("Button.innerFocusWidth", 2);//JButton
//
//        System.setProperty("flatlaf.menuBarEmbedded", "false");
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 8);//JButton
        UIManager.put("ProgressBar.arc", 999);//JProgressBar
        UIManager.put("TextComponent.arc", 8);//JTextField,JPasswordField,JFormattedTextField
        UIManager.put("CheckBox", 999);//JCheckBox
        UIManager.put("Component.arc", 8);//JComboBox,JSpinner

        UIManager.put("Component.innerFocusWidth", 2);//JComboBox, JTextField,JPasswordField,JFormattedTextField,JSpinner
        UIManager.put("Button.innerFocusWidth", 2);//JButton

        System.setProperty("flatlaf.menuBarEmbedded", "false");
        new AppController(new JFrameLogin());
    }
}
