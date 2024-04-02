/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.CourseDAO;
import com.aldrin.aregister.model.Course;
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
public class CourseDAOImpl extends DBConnection implements CourseDAO {

    @Override
    public void addCourse(Course course) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO COURSE (ID,COURSE,ACRONYM) VALUES  (?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, course.getCourse());
            ps.setString(3, course.getAcronym());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourse(Course course) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE COURSE SET COURSE =?, ACRONYM =? WHERE COURSE.ID = ?");
            ps.setString(1, course.getCourse());
            ps.setString(2, course.getAcronym());
            ps.setLong(3, course.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(Course course) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE COURSE SET DELETED =? WHERE COURSE.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, course.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<Course> selectCourse() {
        ArrayList<Course> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM COURSE WHERE COURSE.DELETED =FALSE  ORDER BY COURSE ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getLong("ID"));
                c.setCourse(rs.getString("COURSE"));
                c.setAcronym(rs.getString("ACRONYM"));
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
    public void comboBoxCourse() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM COURSE WHERE COURSE.DELETED =FALSE  ORDER BY COURSE ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String namel = rs.getString("COURSE");
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
                    + "    MAX(COURSE.ID) AS ID  \n"
                    + "FROM \n"
                    + "    COURSE ");
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
