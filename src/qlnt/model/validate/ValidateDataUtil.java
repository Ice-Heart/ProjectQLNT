/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model.validate;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import qlnt.coonfig.connectdb.DBUtil;
import qlnt.model.Admin;

/**
 *
 * @author lehoang
 */
public class ValidateDataUtil {
    public static boolean checkLogin(Admin admin) throws Exception{
        Connection conn = DBUtil.connectDB();
        Statement st = null;
        ResultSet rs = null;
        
        if(conn!=null){
            String sql = "SELECT * FROM tbl_Admin WHERE Username = '"+admin.getUsername()+"' AND Password = '"+encodePass(admin.getPassword())+"'";
            try {
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next()){
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            } finally {
                if(rs!=null) rs.close();
                if(st!=null) st.close();
                conn.close();
            }
        } else {
            return false;
        }
    }

    public static String encodePass(String pass) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());
        byte[] data = md.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(0xff & data[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
