/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.CitizenshipDAO;
import com.aldrin.aregister.model.Citizenship;
import com.aldrin.aregister.util.ComboBoxList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ALDRIN B. C.
 */
@Setter
@Getter
public class CitizenshipDAOImpl extends DBConnection implements CitizenshipDAO {

    @Override
    public void addCitizenship(Citizenship citizenship) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO CITIZENSHIP (ID,CITIZENSHIP) VALUES  (?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, citizenship.getCitizenship());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCitizenship(Citizenship citizenship) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CITIZENSHIP SET CITIZENSHIP =?  WHERE CITIZENSHIP.ID = ?");
            ps.setString(1, citizenship.getCitizenship());
            ps.setLong(2, citizenship.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCitizenship(Citizenship citizenship) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CITIZENSHIP SET DELETED =? WHERE CITIZENSHIP.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, citizenship.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<Citizenship> selectCitizenship() {
        ArrayList<Citizenship> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM CITIZENSHIP WHERE CITIZENSHIP.DELETED =FALSE  ORDER BY CITIZENSHIP ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Citizenship c = new Citizenship();
                c.setId(rs.getLong("ID"));
                c.setCitizenship(rs.getString("CITIZENSHIP"));
                list.add(c);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void comboBoxCitizenship() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM CITIZENSHIP WHERE CITIZENSHIP.DELETED =FALSE  ORDER BY CITIZENSHIP ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String namel = rs.getString("CITIZENSHIP");
                this.getList().add(new ComboBoxList(idl, namel));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Long getMaxId() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(CITIZENSHIP.ID) AS ID  \n"
                    + "FROM \n"
                    + "    CITIZENSHIP ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                if (idl == 0) {
                    maxId = 1L;
                } else {
                    maxId = idl + 1;
                }
            }
            rs.close();
            statement.close();
//            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxId;
    }

}
