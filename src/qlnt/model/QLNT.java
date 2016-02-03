/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model;

import javax.swing.UIManager;

/**
 *
 * @author lehoang
 */
public class QLNT {
    
    public static void main(String[] args) {
        setLAF("Windows");
        new LoginForm();
    }
    
    
    public static void setLAF(String name){
        try {
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if(name.equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
