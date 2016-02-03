/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model.message;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author lehoang
 */
public class MessageUtil {
    public static void showErrorMessage(JFrame parent, String msg, String title){
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showWarningMessage(JFrame parent, String msg, String title){
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void showSuccessMessage(JFrame parent, String msg, String title){
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showConfirmMessage(JFrame parent, String msg, String title){
        JOptionPane.showConfirmDialog(parent, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}
