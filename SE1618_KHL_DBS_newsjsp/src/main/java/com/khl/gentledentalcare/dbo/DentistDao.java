/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Dentist;
import com.khl.gentledentalcare.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DentistDao {

    public static ArrayList<Dentist> getDentist() {
        ArrayList<Dentist> dentistList = new ArrayList<>();
        Connection cn = null;
        Dentist dentist = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT DentistID, NameDentist, NumberPhoneDentist, DetailDentist, ImageDentist, DentistDescription, AcademicRank, titleDentist\n"
                        + "FROM Dentist\n";
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int DentistID = rs.getInt("DentistID");
                        String NameDentist = rs.getString("NameDentist");
                        String NumberPhoneDentist = rs.getString("NumberPhoneDentist");
                        String DetailDentist = rs.getString("DetailDentist");
                        String ImageDentist = rs.getString("ImageDentist");
                        String DentistDescription = rs.getString("DentistDescription");
                        String AcademicRank = rs.getString("AcademicRank");
                        String titleDentist = rs.getString("titleDentist");
                        dentist = new Dentist(DentistID, NameDentist, NumberPhoneDentist, DetailDentist, ImageDentist, DentistDescription, AcademicRank, titleDentist);
                        dentistList.add(dentist);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return dentistList;
    }
     public static Dentist getDentist(int dentistID) {
        Dentist dentist = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT NameDentist, NumberPhoneDentist, DetailDentist, ImageDentist, DentistDescription, AcademicRank, titleDentist\n"
                        + "FROM Dentist\n"
                        + "WHERE DentistID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, dentistID);
                ResultSet rs = st.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        String NameDentist = rs.getString("NameDentist");
                        String NumberPhoneDentist = rs.getString("NumberPhoneDentist");
                        String DetailDentist = rs.getString("DetailDentist");
                        String ImageDentist = rs.getString("ImageDentist");
                        String DentistDescription = rs.getString("DentistDescription");
                        String AcademicRank = rs.getString("AcademicRank");
                        String titleDentist = rs.getString("titleDentist");
                        dentist = new Dentist(dentistID, NameDentist, NumberPhoneDentist, DetailDentist, ImageDentist, DentistDescription, AcademicRank, titleDentist);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return dentist;
    }
}
