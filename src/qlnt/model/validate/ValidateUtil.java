/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model.validate;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author lehoang
 */
public class ValidateUtil {
    public static boolean checkNull(String value){
        if(value.equals("")){
            return false;
        }
        return true;
    }
    
    
    public static boolean isEmail(String email){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+*@[A-Za-z0-9]{2,8}.[A-Za-z0-9]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkMatchValue(String value1, String value2){
        if(value1.equals(value2)){
            return true;
        }
        return false;
    }
    
}
