/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.SchoolYearDAO;
import com.aldrin.aregister.model.SchoolYear;
import com.aldrin.aregister.model.Semester;
import com.aldrin.aregister.model.Tuition;
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
public class SchoolYearDAOImpl extends DBConnection implements SchoolYearDAO {

    @Override
    public void addSchoolYear(SchoolYear schoolYear) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO SCHOOL_YEAR (ID,SCHOOL_YEAR,SEMESTER_ID,TUITION_ID,SY_START,SY_END) VALUES  (?,?,?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, schoolYear.getSchoolYear());
            ps.setLong(3, schoolYear.getSemester().getId());
            ps.setLong(4, schoolYear.getTuition().getId());
            ps.setString(5, schoolYear.getSyStart());
            ps.setString(6, schoolYear.getSyEnd());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSchoolYear(SchoolYear schoolYear) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE SCHOOL_YEAR SET SCHOOL_YEAR =?,SEMESTER_ID =?,TUITION_ID=?, SY_START =?,SY_END=? WHERE SCHOOL_YEAR.ID = ?");
            ps.setString(1, schoolYear.getSchoolYear());
            ps.setLong(2, schoolYear.getSemester().getId());
            ps.setLong(3, schoolYear.getTuition().getId());
            ps.setString(4, schoolYear.getSyStart());
            ps.setString(5, schoolYear.getSyEnd());
            ps.setLong(6, schoolYear.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSchoolYear(SchoolYear schoolYear) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE SCHOOL_YEAR SET DELETED =? WHERE SCHOOL_YEAR_ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, schoolYear.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<SchoolYear> selectSchoolYear() {
        ArrayList<SchoolYear> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    SCHOOL_YEAR.ID, \n"
                    + "    SCHOOL_YEAR.SEMESTER_ID, \n"
                    + "    SCHOOL_YEAR.TUITION_ID,\n"
                    + "    SCHOOL_YEAR.SCHOOL_YEAR, \n"
                    + "    SEMESTER.SEMESTER, \n"
                    + "    TUITION.TUITION_FEE, \n"
                    + "    SCHOOL_YEAR.SY_END, \n"
                    + "    SCHOOL_YEAR.SY_START\n"
                    + "   \n"
                    + "FROM \n"
                    + "    SCHOOL_YEAR \n"
                    + "INNER JOIN \n"
                    + "    TUITION \n"
                    + "ON \n"
                    + "    (SCHOOL_YEAR.TUITION_ID = TUITION.ID) \n"
                    + "INNER JOIN \n"
                    + "    SEMESTER \n"
                    + "ON \n"
                    + "    ( SCHOOL_YEAR.SEMESTER_ID = SEMESTER.ID) ORDER BY SCHOOL_YEAR.ID DESC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                SchoolYear sy = new SchoolYear();
                Semester s = new Semester();
                Tuition t = new Tuition();
                sy.setId(rs.getLong("ID"));
                s.setId(rs.getLong("SEMESTER_ID"));
                s.setSemester(rs.getString("SEMESTER"));
                sy.setSemester(s);
                sy.setSchoolYear(rs.getString("SCHOOL_YEAR"));
                sy.setSyStart(rs.getString("SY_START"));
                sy.setSyEnd(rs.getString("SY_END"));
                t.setId(rs.getLong("TUITION_ID"));
                t.setTuitionFee(rs.getFloat("TUITION_FEE"));
                sy.setTuition(t);
                list.add(sy);
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
    public void comboBoxSchoolYear() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT \n"
                    + "    SCHOOL_YEAR.ID, \n"
                    + "    SCHOOL_YEAR.SEMESTER_ID, \n"
                    + "    SCHOOL_YEAR.SCHOOL_YEAR, \n"
                    + "    SCHOOL_YEAR.SY_END, \n"
                    + "    SCHOOL_YEAR.SY_START, \n"
                    + "    SEMESTER.SEMESTER \n"
                    + "FROM \n"
                    + "    SCHOOL_YEAR \n"
                    + "INNER JOIN \n"
                    + "    SEMESTER \n"
                    + "ON \n"
                    + "    (SCHOOL_YEAR.SEMESTER_ID = SEMESTER.ID) ORDER BY SCHOOL_YEAR.ID DESC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String schoolYear = rs.getString("SCHOOL_YEAR");
                String semester = rs.getString("SEMESTER");

                this.getList().add(new ComboBoxList(idl, schoolYear + " [" + semester + "] "));
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
                    + "    MAX(SCHOOL_YEAR.ID) AS ID  \n"
                    + "FROM \n"
                    + "    SCHOOL_YEAR ");
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

    public SchoolYear getCurrentSchoolYearIDAndTuitionID() {
        SchoolYear schoolYear = new SchoolYear();
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("""
                 SELECT    SCHOOL_YEAR.ID,SCHOOL_YEAR.TUITION_ID
                        FROM 
                            SCHOOL_YEAR 
                        INNER JOIN 
                            TUITION 
                        ON (SCHOOL_YEAR.TUITION_ID =TUITION.ID) 
                        INNER JOIN 
                            SEMESTER 
                         ON (SCHOOL_YEAR.SEMESTER_ID = SEMESTER.ID)   WHERE SCHOOL_YEAR.SY_START <CURRENT_DATE and SCHOOL_YEAR.SY_END > CURRENT_DATE 
                                                                    """);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                SchoolYear sy = new SchoolYear();
                if (rs.getLong("ID") < 1) {
                    schoolYear = null;
                } else {
                    sy.setId(rs.getLong("ID"));
                    Tuition tuition = new Tuition();
                    tuition.setId(rs.getLong("TUITION_ID"));
                    sy.setTuition(tuition);
                    schoolYear = sy;
                }
            }
            rs.close();
            statement.close();
//            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schoolYear;

    }

}
