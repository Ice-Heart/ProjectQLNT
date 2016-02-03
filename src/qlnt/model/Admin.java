/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import qlnt.coonfig.connectdb.DBUtil;
import qlnt.model.validate.ValidateDataUtil;

/**
 *
 * @author lehoang
 */
public class Admin {

    private int idAdmin;
    private String username;
    private String password;
    private String email;
    private String status = "disable";

    public Admin(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Admin() {
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Admin: Username = " + username + ", Password = " + password + ", Email = " + email + ", Status = " + status;
    }

    public boolean createAdmin(Admin admin) throws Exception {
        Connection conn = null;
        Statement st = null;

        conn = DBUtil.connectDB();
        if (conn != null) {
            String sql = "INSERT INTO tbl_Admin(Username, Password, Email, Status) VALUES('" + admin.getUsername() + "', '" + ValidateDataUtil.encodePass(admin.getPassword()) + "', '" + admin.getEmail() + "', '" + admin.getStatus() + "')";
            try {

                st = conn.createStatement();

                int result = st.executeUpdate(sql);
                if (result != 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            } finally {
                if (st != null) {
                    st.close();
                }
                conn.close();
            }
        } else {
            return false;
        }

    }

    public boolean deleteAdmin(Admin admin) throws Exception {
        Connection conn = null;
        Statement st = null;

        conn = DBUtil.connectDB();
        if (conn != null) {
            try {
                String sql = "DELETE FROM tbl_Admin WHERE ID_Admin = " + admin.getIdAdmin();
                st = conn.createStatement();
                int result = st.executeUpdate(sql);
                if (result != 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            } finally {
                if(st!=null) st.close();
                conn.close();
            }
        } else {
            return false;
        }
    }

    public boolean updateAdmin(Admin admin) throws Exception {
        Connection conn = null;
        Statement st = null;

        conn = DBUtil.connectDB();
        if (conn != null) {
            try {
                String sql = "UPDATE tbl_Admin SET Password = '" + ValidateDataUtil.encodePass(admin.getPassword()) + "', Email = '" + admin.getEmail() + "', Status = '" + admin.getStatus() + "' WHERE ID_Admin = "+admin.getIdAdmin();
                st = conn.createStatement();
                int result = st.executeUpdate(sql);
                if (result != 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            } finally {
                if (st != null) {
                    st.close();
                }
                conn.close();
            }
        } else {
            return false;
        }
    }

    public ArrayList<Admin> getAdmins() throws Exception {
        ArrayList<Admin> data = null;
        Connection conn = DBUtil.connectDB();
        Statement st = null;
        ResultSet rs = null;

        if (conn != null) {
            String sql = "SELECT * FROM tbl_Admin";
            try {
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Admin admin = new Admin();
                    admin.setIdAdmin(rs.getInt("ID_Admin"));
                    admin.setUsername(rs.getString("Username"));
                    admin.setPassword(rs.getString("Password"));
                    admin.setEmail(rs.getString("Email"));
                    admin.setStatus(rs.getString("Status"));
                    data.add(admin);
                }
                return data;
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    rs.close();
                }
                conn.close();
            }
        } else {
            return data;
        }
    }

    public boolean loginAdmin(Admin admin) throws Exception {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        conn = DBUtil.connectDB();

        if (conn != null) {
            String sql = "SELECT * FROM tbl_Admin WHERE Username = '" + admin.getUsername() + "' AND Password = '" + ValidateDataUtil.encodePass(admin.getPassword()) + "'";
            try {
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                conn.close();
            }
        } else {
            return false;
        }

    }

}
