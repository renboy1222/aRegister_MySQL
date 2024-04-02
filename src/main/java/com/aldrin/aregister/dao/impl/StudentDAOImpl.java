/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.StudentDAO;
import com.aldrin.aregister.model.Citizenship;
import com.aldrin.aregister.model.CivilStatus;
import com.aldrin.aregister.model.Course;
import com.aldrin.aregister.model.Student;
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
public class StudentDAOImpl extends DBConnection implements StudentDAO {

    @Override
    public void addStudent(Student student) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO STUDENT (STUDENT.ID, \n"
                    + "    STUDENT.FIRSTNAME, \n"
                    + "    STUDENT.MIDDLENAME, \n"
                    + "    STUDENT.SURNAME, \n"
                    + "    STUDENT.GENDER, \n"
                    + "    STUDENT.MOBILE_NO, \n"
                    + "    STUDENT.ADDRESS, \n"
                    + "    STUDENT.DATE_OF_BIRTH, \n"
                    + "    STUDENT.EMAIL,\n"
                    + "    STUDENT.CITIZENSHIP_ID, \n"
                    + "    STUDENT.CIVIL_STATUS_ID, \n"
                    + "    STUDENT.COURSE_ID,\n"
                    + "    STUDENT.PHOTO,STUDENT_ID ) VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
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
            ps.setLong(12, student.getCourse().getId());
            ps.setBytes(13, student.getPhoto());
            ps.setString(14, student.getStudentId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE STUDENT SET   \n"
                    + "    FIRSTNAME =?, MIDDLENAME=?,SURNAME=?, GENDER=?, MOBILE_NO=?, ADDRESS=?, DATE_OF_BIRTH=?, EMAIL=?,CITIZENSHIP_ID=?, CIVIL_STATUS_ID=?, COURSE_ID =?,\n"
                    + "    PHOTO=?,STUDENT_ID=? WHERE STUDENT.ID = ?");
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
            ps.setLong(11, student.getCourse().getId());
            ps.setBytes(12, student.getPhoto());
            ps.setString(13, student.getStudentId());
            ps.setLong(14, student.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(Student student) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE STUDENT SET DELETED =? WHERE STUDENT.ID = ? ");
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
    public ArrayList<Student> selectStudent() {
        ArrayList<Student> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    STUDENT.ID, STUDENT.FIRSTNAME,STUDENT.MIDDLENAME, STUDENT.SURNAME,\n"
                    + "    STUDENT.GENDER,  STUDENT.MOBILE_NO,STUDENT.EMAIL,  STUDENT.DATE_OF_BIRTH, \n"
                    + "    STUDENT.ADDRESS, \n"
                    + "    STUDENT.CITIZENSHIP_ID, \n"
                    + "    STUDENT.CIVIL_STATUS_ID, \n"
                    + "    STUDENT.COURSE_ID, \n"
                    + "    CITIZENSHIP.CITIZENSHIP, \n"
                    + "    CIVIL_STATUS.CIVIL_STATUS, \n"
                    + "    COURSE.COURSE, STUDENT.STUDENT_ID\n"
                    + "FROM \n"
                    + "    STUDENT \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON \n"
                    + "    (STUDENT.COURSE_ID = COURSE.ID) \n"
                    + "INNER JOIN \n"
                    + "    CIVIL_STATUS \n"
                    + "ON \n"
                    + "    (STUDENT.CIVIL_STATUS_ID = CIVIL_STATUS.ID) \n"
                    + "INNER JOIN \n"
                    + "    CITIZENSHIP \n"
                    + "ON \n"
                    + "    (STUDENT.CITIZENSHIP_ID = CITIZENSHIP.ID) ORDER BY SURNAME ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getLong("ID"));
                s.setStudentId(rs.getString("STUDENT_ID"));
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
                Course course = new Course();
                course.setId(rs.getLong("COURSE_ID"));
                course.setCourse(rs.getString("COURSE"));
                s.setCitizenship(citizenship);
                s.setCivilStatus(cs);
                s.setCourse(course);
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
    public void comboBoxStudent() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM STUDENT WHERE STUDENT.DELETED =FALSE  ORDER BY STUDENT.SURNAME ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String firstname = rs.getString("FIRSTNAME");
                String middlename = rs.getString("MIDDLENAME");
                String surname = rs.getString("SURNAME");
                String name = surname + ", " + firstname + " " + middlename.substring(0, 1)+".";
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
                    + "    MAX(STUDENT.ID) AS ID  \n"
                    + "FROM \n"
                    + "    STUDENT ");
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
    public Student findPhotoByStudentId(Student student) {
        Student userPhoto = new Student();
        Blob photo = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT  PHOTO FROM STUDENT  WHERE ID  =" + student.getId() + "");
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

    @Override
    public void comboBoxStudentID() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM STUDENT WHERE STUDENT.DELETED =FALSE  ORDER BY STUDENT.SURNAME ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String studentId = rs.getString("STUDENT_ID");
                this.getList().add(new ComboBoxList(idl, studentId));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Student selectStudentInfo(Student student) {
        Student studentInfo = null;
        Blob photo = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    STUDENT.ID, \n"
                    + "    STUDENT.FIRSTNAME, \n"
                    + "    STUDENT.MIDDLENAME, \n"
                    + "    STUDENT.SURNAME, \n"
                    + "    STUDENT.COURSE_ID, \n"
                    + "    STUDENT.CIVIL_STATUS_ID, \n"
                    + "    STUDENT.GENDER, \n"
                    + "    STUDENT.PHOTO \n"
                    + "FROM \n"
                    + "    STUDENT WHERE  STUDENT.ID =" + student.getId() + " ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                CivilStatus cs = new CivilStatus();
                Course c = new Course();

                Long studentId = rs.getLong("ID");
                Long civilStatusId = rs.getLong("CIVIL_STATUS_ID");
                Long courseId = rs.getLong("COURSE_ID");
                String firstname = rs.getString("FIRSTNAME");
                String surname = rs.getString("SURNAME");
                String middlename = rs.getString("MIDDLENAME");
                String gender = rs.getString("GENDER");
                cs.setId(civilStatusId);
                c.setId(courseId);

                s.setId(studentId);
                s.setCourse(c);
                s.setCivilStatus(cs);
                s.setFirstname(firstname);
                s.setMiddlename(middlename);
                s.setSurname(surname);
                s.setGender(gender);

                Blob picturel = rs.getBlob("PHOTO");
                photo = picturel;
                byte[] bytes = convertBlobToBytes(picturel);
                s.setPhoto(bytes);
                studentInfo = s;
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentInfo;
    }

    @Override
    public Integer selectStudentAge(Student student) {
        Integer age = 0;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT id, firstname, date_of_birth,\n"
                    + "    YEAR(CURRENT_DATE) - YEAR(date_of_birth) - \n"
                    + "    CASE\n"
                    + "        WHEN MONTH(CURRENT_DATE) < MONTH(date_of_birth) OR \n"
                    + "            (MONTH(CURRENT_DATE) = MONTH(date_of_birth) AND day(CURRENT_DATE) < day(date_of_birth))\n"
                    + "        THEN 1\n"
                    + "        ELSE 0\n"
                    + "    END AS age\n"
                    + "FROM student where student.id =?");
            statement.setLong(1, student.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                age = rs.getInt("AGE");
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age;
    }

}
