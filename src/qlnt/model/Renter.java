/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import qlnt.coonfig.connectdb.DBUtil;

/**
 *
 * @author lehoang
 */
public class Renter {
    private int idRenter;
    private String fullName;
    private int gender;
    private String birthday;
    private String address;
    private String job;
    private String idCardNum;
    private String phoneNumber;
    private String assets;

    public int getIdRenter() {
        return idRenter;
    }

    public void setIdRenter(int idRenter) {
        this.idRenter = idRenter;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "Renter{" + "idRenter=" + idRenter + ", fullName=" + fullName + ", gender=" + gender + ", birthday=" + birthday + ", address=" + address + ", job=" + job + ", idCardNum=" + idCardNum + ", phoneNumber=" + phoneNumber + ", assets=" + assets + '}';
    }

    public Renter(String fullName, int gender, String birthday, String address, String job, String idCardNum, String phoneNumber, String assets) {
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.job = job;
        this.idCardNum = idCardNum;
        this.phoneNumber = phoneNumber;
        this.assets = assets;
    }

    public Renter() {
    }
    
    
    public boolean createRenter(Renter renter) throws Exception{
        Connection conn = null;
        Statement st = null;
        
        conn = DBUtil.connectDB();
        if(conn != null){
            String sql = "INSERT INTO tbl_Renter(Full_Name, Gender, Birthday, ID_Card_Number, Phone_Number, Job, Address, Assets) VALUES('"+renter.getFullName()+"','"+renter.getGender()+"','"+renter.getBirthday()+"','"+renter.getIdCardNum()+"','"+renter.getPhoneNumber()+"','"+renter.getJob()
                    +"','"+renter.getAddress()+"', '"+renter.getAssets()+"')";
            try{
                st = conn.createStatement();
                int result = st.executeUpdate(sql);
                if(result != 0){
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex){
                throw new Exception(ex.getMessage());
            } finally {
                if(st!=null) st.close();
                conn.close();
            }
        } else {
            return false;
        }
    }
    
    public boolean updateRenter(Renter renter) throws Exception{
        Connection conn = DBUtil.connectDB();
        Statement st = null;
        
        if(conn!=null){
            String sql = "UPDATE tbl_Renter SET Full_Name = '"+renter.getFullName()+"', Gender = "+renter.getGender()+", Birthday = '"+renter.getBirthday()+"', Address = '"+renter.getAddress()+"', Job = '"+renter.getJob()+"', ID_Card_Number = '"+renter.getIdCardNum()+"', Assets = '"+renter.getAssets()+"', Phone_Number = '"+renter.getPhoneNumber()+"' WHERE ID_Renter = " + renter.getIdRenter();
            try {
                st = conn.createStatement();
                int result = st.executeUpdate(sql);
                if(result!=0){
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
    
    public boolean deleteRenter(Renter renter) throws Exception{
        Connection conn = DBUtil.connectDB();
        Statement st = null;
        
        if(conn!=null){
            String sql = "DELETE FROM tbl_Renter WHERE ID_Renter = " + renter.getIdRenter();
            try {
                st = conn.createStatement();
                int result = st.executeUpdate(sql);
                if(result != 0){
                   return true; 
                } else {
                    return false;
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            } finally {
                if(st!= null) st.close();
                conn.close();
            }
        } else {
            return false;
        }
    }
    
    public ArrayList<Renter> getRenters() throws Exception{
        ArrayList<Renter> data = new ArrayList<Renter>();
        Connection conn = DBUtil.connectDB();
        Statement st = null;
        ResultSet rs = null;
        
        if(conn!=null){
            String sql = "SELECT * FROM tbl_Renter";
            try {
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    Renter renter = new Renter();
                    renter.setFullName(rs.getString("Full_Name"));
                    renter.setGender(rs.getInt("Gender"));
                    renter.setBirthday(rs.getString("Birthday"));
                    renter.setIdCardNum(rs.getString("ID_Card_Number"));
                    renter.setPhoneNumber(rs.getString("Phone_Number"));
                    renter.setJob(rs.getString("Job"));
                    renter.setAddress(rs.getString("Address"));
                    renter.setAssets(rs.getString("Assets"));
                    data.add(renter);
                }
                
                return data;
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            } finally {
                if(rs!=null) rs.close();
                if(st!=null) st.close();
                conn.close();
            }
        } else {
            return data;
        }
    }
    
}
