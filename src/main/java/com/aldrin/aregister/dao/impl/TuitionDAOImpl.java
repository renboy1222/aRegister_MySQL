/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.TuitionDAO;
import com.aldrin.aregister.model.Tuition;
import com.aldrin.aregister.util.ComboBoxList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ALDRIN B. C.
 */
@Setter
@Getter
public class TuitionDAOImpl extends DBConnection implements TuitionDAO {
private DecimalFormat df = new DecimalFormat("##,##0.00");
    @Override
    public void addTuition(Tuition day) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO TUITION (ID,TUITION_FEE) VALUES  (?,?) ");
            ps.setLong(1, getMaxId());
            ps.setFloat(2, day.getTuitionFee());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTuition(Tuition day) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE TUITION SET TUITION_FEE =?  WHERE TUITION.ID = ?");
            ps.setFloat(1, day.getTuitionFee());
            ps.setLong(2, day.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTuition(Tuition day) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE TUITION SET DELETED =? WHERE TUITION.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, day.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<Tuition> selectTuition() {
        ArrayList<Tuition> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM TUITION WHERE TUITION.DELETED =FALSE  ORDER BY TUITION_FEE ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Tuition c = new Tuition();
                c.setId(rs.getLong("ID"));
                c.setTuitionFee(rs.getFloat("TUITION_FEE"));
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
    public void comboBoxTuition() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM TUITION WHERE TUITION.DELETED =FALSE  ORDER BY TUITION_FEE ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                Float tuitionl = rs.getFloat("TUITION_FEE");
                String tuition =df.format(tuitionl).toString();
                this.getList().add(new ComboBoxList(idl, tuition));
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
                    + "    MAX(TUITION.ID) AS ID  \n"
                    + "FROM \n"
                    + "    TUITION ");
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
