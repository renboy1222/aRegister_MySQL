/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.InstructorDAO;
import com.aldrin.aregister.model.Citizenship;
import com.aldrin.aregister.model.CivilStatus;
import com.aldrin.aregister.model.Instructor;
import com.aldrin.aregister.util.ComboBoxList;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class InstructorDAOImpl extends DBConnection implements InstructorDAO {

    @Override
    public void addInstructor(Instructor student) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO INSTRUCTOR ( INSTRUCTOR.ID, \n"
                    + "    INSTRUCTOR.FIRSTNAME, \n"
                    + "    INSTRUCTOR.MIDDLENAME, \n"
                    + "    INSTRUCTOR.SURNAME, \n"
                    + "    INSTRUCTOR.GENDER, \n"
                    + "    INSTRUCTOR.MOBILE_NO, \n"
                    + "    INSTRUCTOR.ADDRESS, \n"
                    + "    INSTRUCTOR.DATE_OF_BIRTH, \n"
                    + "    INSTRUCTOR.EMAIL,\n"
                    + "    INSTRUCTOR.CITIZENSHIP_ID, \n"
                    + "    INSTRUCTOR.CIVIL_STATUS_ID, \n"
                    + "    INSTRUCTOR.PHOTO ) VALUES  (?,?,?,?,?,?,?,?,?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, student.getFirstname());
            ps.setString(3, student.getMiddlename());
            ps.setString(4, student.getSurname());
            ps.setString(5, student.getGender());
            ps.setString(6, student.getMobileNo());
            ps.setString(7, student.getAddress());
            ps.setString(8, student.getDateOfBirth());
            ps.setString(9, student.getEmail());
            ps.setLong(10, student.getCitizenship().getId());
            ps.setLong(11, student.getCivilStatus().getId());
            ps.setBytes(12, student.getPhoto());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInstructor(Instructor student) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE INSTRUCTOR SET   \n"
                    + "    FIRSTNAME =?, MIDDLENAME=?,SURNAME=?, GENDER=?, MOBILE_NO=?, ADDRESS=?, DATE_OF_BIRTH=?, EMAIL=?,CITIZENSHIP_ID=?, CIVIL_STATUS_ID=?, \n"
                    + "    PHOTO=? WHERE INSTRUCTOR.ID = ?");
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getMiddlename());
            ps.setString(3, student.getSurname());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getMobileNo());
            ps.setString(6, student.getAddress());
            ps.setString(7, student.getDateOfBirth());
            ps.setString(8, student.getEmail());
            ps.setLong(9, student.getCitizenship().getId());
            ps.setLong(10, student.getCivilStatus().getId());
            ps.setBytes(11, student.getPhoto());
            ps.setLong(12, student.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInstructor(Instructor student) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE INSTRUCTOR SET DELETED =? WHERE INSTRUCTOR.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, student.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<Instructor> selectInstructor() {
        ArrayList<Instructor> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    INSTRUCTOR.ID, \n"
                    + "    INSTRUCTOR.CIVIL_STATUS_ID, \n"
                    + "    INSTRUCTOR.CITIZENSHIP_ID, \n"
                    + "    INSTRUCTOR.FIRSTNAME, \n"
                    + "    INSTRUCTOR.MIDDLENAME, \n"
                    + "    INSTRUCTOR.SURNAME, \n"
                    + "    INSTRUCTOR.MOBILE_NO, \n"
                    + "    INSTRUCTOR.DATE_OF_BIRTH, \n"
                    + "    INSTRUCTOR.EMAIL, \n"
                    + "    INSTRUCTOR.ADDRESS, \n"
                    + "    INSTRUCTOR.GENDER, \n"
                    + "    CIVIL_STATUS.CIVIL_STATUS, \n"
                    + "    CITIZENSHIP.CITIZENSHIP \n"
                    + "FROM \n"
                    + "    INSTRUCTOR \n"
                    + "INNER JOIN \n"
                    + "    CIVIL_STATUS \n"
                    + "ON \n"
                    + "    (INSTRUCTOR.CIVIL_STATUS_ID = CIVIL_STATUS.ID) \n"
                    + "INNER JOIN \n"
                    + "    CITIZENSHIP \n"
                    + "ON \n"
                    + "    (INSTRUCTOR.CITIZENSHIP_ID = CITIZENSHIP.ID) ORDER BY INSTRUCTOR.SURNAME ASC  ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Instructor s = new Instructor();
                s.setId(rs.getLong("ID"));
                s.setFirstname(rs.getString("FIRSTNAME"));
                s.setMiddlename(rs.getString("MIDDLENAME"));
                s.setSurname(rs.getString("SURNAME"));
                s.setGender(rs.getString("GENDER"));
                s.setMobileNo(rs.getString("MOBILE_NO"));
                s.setEmail(rs.getString("EMAIL"));
                s.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
                s.setAddress(rs.getString("ADDRESS"));
                Citizenship citizenship = new Citizenship();
                citizenship.setId(rs.getLong("CITIZENSHIP_ID"));
                citizenship.setCitizenship(rs.getString("CITIZENSHIP"));
                CivilStatus cs = new CivilStatus();
                cs.setId(rs.getLong("CIVIL_STATUS_ID"));
                cs.setCivilStatus(rs.getString("CIVIL_STATUS"));
                s.setCitizenship(citizenship);
                s.setCivilStatus(cs);
                list.add(s);
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
    public void comboBoxInstructor() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM INSTRUCTOR WHERE INSTRUCTOR.DELETED =FALSE  ORDER BY SURNAME ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String firstname = rs.getString("FIRSTNAME");
                String middlename = rs.getString("MIDDLENAME");
                String surname = rs.getString("SURNAME");
                String name = surname + ", " + firstname + " " + middlename.substring(0, 1);
                this.getList().add(new ComboBoxList(idl, name));
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
                    + "    MAX(INSTRUCTOR.ID) AS ID  \n"
                    + "FROM \n"
                    + "    INSTRUCTOR ");
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

    @Override
    public Instructor findPhotoByInstructorId(Instructor instructor) {
        Instructor userPhoto = new Instructor();
        Blob photo = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT  PHOTO FROM INSTRUCTOR  WHERE ID  =" + instructor.getId() + "");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Blob picturel = rs.getBlob("PHOTO");
                photo = picturel;
                byte[] bytes = convertBlobToBytes(picturel);
                userPhoto.setPhoto(bytes);
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userPhoto;
    }

    private static byte[] convertBlobToBytes(java.sql.Blob blob) throws IOException, SQLException {
        try (InputStream inputStream = blob.getBinaryStream()) {
            return convertInputStreamToBytes(inputStream);
        }
    }

    private static byte[] convertInputStreamToBytes(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }

    private static void writeBytesToFile(byte[] bytes, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(bytes);
        }
    }

}
