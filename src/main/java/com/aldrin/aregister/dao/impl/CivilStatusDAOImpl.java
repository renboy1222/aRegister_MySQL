/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.CivilStatusDAO;
import com.aldrin.aregister.model.CivilStatus;
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
public class CivilStatusDAOImpl extends DBConnection implements CivilStatusDAO {

    @Override
    public void addCivilStatus(CivilStatus civilStatus) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO CIVIL_STATUS (ID,CIVIL_STATUS) VALUES  (?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, civilStatus.getCivilStatus());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCivilStatus(CivilStatus civilStatus) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CIVIL_STATUS SET CIVIL_STATUS =?  WHERE CIVIL_STATUS.ID = ?");
            ps.setString(1, civilStatus.getCivilStatus());
            ps.setLong(2, civilStatus.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCivilStatus(CivilStatus civilStatus) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CIVIL_STATUS SET DELETED =? WHERE CIVIL_STATUS.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, civilStatus.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<CivilStatus> selectCivilStatus() {
        ArrayList<CivilStatus> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM CIVIL_STATUS WHERE CIVIL_STATUS.DELETED =FALSE  ORDER BY CIVIL_STATUS ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CivilStatus c = new CivilStatus();
                c.setId(rs.getLong("ID"));
                c.setCivilStatus(rs.getString("CIVIL_STATUS"));
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
    public void comboBoxCivilStatus() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM CIVIL_STATUS WHERE CIVIL_STATUS.DELETED =FALSE  ORDER BY ID ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String namel = rs.getString("CIVIL_STATUS");
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
                    + "    MAX(CIVIL_STATUS.ID) AS ID  \n"
                    + "FROM \n"
                    + "    CIVIL_STATUS ");
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
