/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.util;

import java.sql.Connection;
import java.sql.Statement;
import qlnt.connectdb.DBUtil;

/**
 *
 * @author lehoang
 */
public class CUDUtil {
    public static void statementCUD(String sql) throws Exception{
        Connection conn = null;
        Statement st = null;
        try {
            conn = DBUtil.connectDB();

            st = conn.createStatement();
            st.executeUpdate(sql);

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }
}
