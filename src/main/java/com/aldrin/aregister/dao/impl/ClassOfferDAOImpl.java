/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.ClassOfferDAO;
import com.aldrin.aregister.model.ClassOffer;
import com.aldrin.aregister.model.Course;
import com.aldrin.aregister.model.Day;
import com.aldrin.aregister.model.Instructor;
import com.aldrin.aregister.model.Room;
import com.aldrin.aregister.model.SchoolYear;
import com.aldrin.aregister.model.Subject;
import com.aldrin.aregister.model.Time;
import com.aldrin.aregister.util.ComboBoxList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ALDRIN B. C.
 */
@Setter
@Getter
@ToString
public class ClassOfferDAOImpl extends DBConnection implements ClassOfferDAO {

    @Override
    public void addClassOffer(ClassOffer classOffer) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO CLASS_OFFER(CLASS_OFFER.ID, \n"
                    + "    CLASS_OFFER.SCHOOL_YEAR_ID, \n"
                    + "    CLASS_OFFER.SUBJECT_ID, \n"
                    + "    CLASS_OFFER.INSTRUCTOR_ID, \n"
                    + "    CLASS_OFFER.COURSE_ID, \n"
                    + "    CLASS_OFFER.ROOM_ID, \n"
                    + "    CLASS_OFFER.DAY_ID, \n"
                    + "    CLASS_OFFER.TIME_ID, \n"
                    + "    CLASS_OFFER.CLASS_CODE) VALUES  (?,?,?,?,?,?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setLong(2, classOffer.getSchoolYear().getId());
            ps.setLong(3, classOffer.getSubject().getId());
            ps.setLong(4, classOffer.getInstructor().getId());
            ps.setLong(5, classOffer.getCourse().getId());
            ps.setLong(6, classOffer.getRoom().getId());
            ps.setLong(7, classOffer.getDay().getId());
            ps.setLong(8, classOffer.getTime().getId());
            ps.setString(9, classOffer.getClass_code());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClassOffer(ClassOffer classOffer) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CLASS_OFFER SET  CLASS_OFFER.SCHOOL_YEAR_ID =? , \n"
                    + "    CLASS_OFFER.SUBJECT_ID = ? , \n"
                    + "    CLASS_OFFER.INSTRUCTOR_ID =? , \n"
                    + "    CLASS_OFFER.COURSE_ID= ?, \n"
                    + "    CLASS_OFFER.ROOM_ID =?, \n"
                    + "    CLASS_OFFER.DAY_ID =?, \n"
                    + "    CLASS_OFFER.TIME_ID =?, \n"
                    + "    CLASS_OFFER.CLASS_CODE= ? WHERE CLASS_OFFER.ID = ?");
            ps.setLong(1, classOffer.getSchoolYear().getId());
            ps.setLong(2, classOffer.getSubject().getId());
            ps.setLong(3, classOffer.getInstructor().getId());
            ps.setLong(4, classOffer.getCourse().getId());
            ps.setLong(5, classOffer.getRoom().getId());
            ps.setLong(6, classOffer.getDay().getId());
            ps.setLong(7, classOffer.getTime().getId());
            ps.setString(8, classOffer.getClass_code());
            ps.setLong(9, classOffer.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClassOffer(ClassOffer classOffer) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CLASS_OFFER SET DELETED =? WHERE CLASS_OFFER.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, classOffer.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<ClassOffer> selectClassOffer(Long syId) {
        ArrayList<ClassOffer> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    CLASS_OFFER.ID, \n"
                    + "    CLASS_OFFER.SCHOOL_YEAR_ID, \n"
                    + "    CLASS_OFFER.SUBJECT_ID, \n"
                    + "    CLASS_OFFER.INSTRUCTOR_ID, \n"
                    + "    CLASS_OFFER.COURSE_ID, \n"
                    + "    CLASS_OFFER.ROOM_ID, \n"
                    + "    CLASS_OFFER.DAY_ID, \n"
                    + "    CLASS_OFFER.TIME_ID, \n"
                    + "    CLASS_OFFER.CLASS_CODE,\n"
                    + "    SCHOOL_YEAR.SCHOOL_YEAR, \n"
                    + "    SEMESTER.SEMESTER, \n"
                    + "    SUBJECT.SUBJECT, \n"
                    + "    SUBJECT.CODE, \n"
                    + "    SUBJECT.UNIT, \n"
                    + "    INSTRUCTOR.SURNAME, \n"
                    + "    INSTRUCTOR.FIRSTNAME, \n"
                    + "    INSTRUCTOR.MIDDLENAME, \n"
                    + "    COURSE.COURSE, \n"
                    + "    ROOM.ROOM, \n"
                    + "    DAY.DAY, \n"
                    + "    TIME.TIME \n"
                    + "FROM \n"
                    + "    CLASS_OFFER \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON \n"
                    + "    (CLASS_OFFER.COURSE_ID = COURSE.ID) \n"
                    + "INNER JOIN \n"
                    + "    DAY \n"
                    + "ON \n"
                    + "    (CLASS_OFFER.DAY_ID = DAY.ID) \n"
                    + "INNER JOIN \n"
                    + "    INSTRUCTOR \n"
                    + "ON \n"
                    + "    (CLASS_OFFER.INSTRUCTOR_ID = INSTRUCTOR.ID) \n"
                    + "INNER JOIN \n"
                    + "    ROOM \n"
                    + "ON \n"
                    + "    (CLASS_OFFER.ROOM_ID = ROOM.ID) \n"
                    + "INNER JOIN \n"
                    + "    SCHOOL_YEAR \n"
                    + "ON \n"
                    + "    (CLASS_OFFER.SCHOOL_YEAR_ID = SCHOOL_YEAR.ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON \n"
                    + "    (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "INNER JOIN \n"
                    + "    TIME \n"
                    + "ON \n"
                    + "    (CLASS_OFFER.TIME_ID = TIME.ID) \n"
                    + "INNER JOIN \n"
                    + "    SEMESTER \n"
                    + "ON \n"
                    + "    (SCHOOL_YEAR.SEMESTER_ID = SEMESTER.ID) WHERE CLASS_OFFER.SCHOOL_YEAR_ID =" + syId + " ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ClassOffer classOffer = new ClassOffer();
                classOffer.setId(rs.getLong("ID"));
                classOffer.setClass_code(rs.getString("CLASS_CODE"));
                SchoolYear sy = new SchoolYear();//sy
                sy.setId(rs.getLong("SCHOOL_YEAR_ID"));
                String year = rs.getString("SCHOOL_YEAR");
                String semester = rs.getString("SEMESTER");
                String schoolYear = year + "  " + semester;
                sy.setSchoolYear(schoolYear);
                Subject subject = new Subject();
                subject.setId(rs.getLong("SUBJECT_ID"));
                subject.setCode(rs.getString("CODE"));
                subject.setSubject(rs.getString("SUBJECT"));
                subject.setUnit(rs.getInt("UNIT"));
                Instructor instructor = new Instructor();
                instructor.setId(rs.getLong("INSTRUCTOR_ID"));
                instructor.setFirstname(rs.getString("FIRSTNAME"));
                instructor.setMiddlename(rs.getString("MIDDLENAME"));
                instructor.setSurname(rs.getString("SURNAME"));
                Course course = new Course();
                course.setId(rs.getLong("COURSE_ID"));
                course.setCourse(rs.getString("COURSE"));
                Room room = new Room();
                room.setId(rs.getLong("ROOM_ID"));
                room.setRoom(rs.getString("ROOM"));
                Day day = new Day();
                day.setId(rs.getLong("DAY_ID"));
                day.setDay(rs.getString("DAY"));
                Time time = new Time();
                time.setId(rs.getLong("TIME_ID"));
                time.setTime(rs.getString("TIME"));

                classOffer.setSchoolYear(sy);
                classOffer.setSubject(subject);
                classOffer.setInstructor(instructor);
                classOffer.setCourse(course);
                classOffer.setRoom(room);
                classOffer.setDay(day);
                classOffer.setTime(time);

                list.add(classOffer);
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
    public void comboBoxClassOffer() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            SchoolYearDAOImpl syID = new SchoolYearDAOImpl();
            statement = getCon().prepareStatement("SELECT * FROM CLASS_OFFER WHERE CLASS_OFFER.DELETED =FALSE AND CLASS_OFFER.SCHOOL_YEAR_ID =" + syID.getCurrentSchoolYearIDAndTuitionID().getId() + " ORDER BY CLASS_OFFER.CLASS_CODE ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String namel = rs.getString("CLASS_CODE");
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
                    + "    MAX(CLASS_OFFER.ID) AS ID  \n"
                    + "FROM \n"
                    + "    CLASS_OFFER ");
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
    public ClassOffer selectClassOffer(ClassOffer co) {
        ClassOffer classOfferDetails = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    CLASS_OFFER.ID, \n"
                    + "    CLASS_OFFER.CLASS_CODE, \n"
                    + "    SUBJECT.SUBJECT, \n"
                    + "    SUBJECT.UNIT, \n"
                    + "    INSTRUCTOR.SURNAME, \n"
                    + "    INSTRUCTOR.FIRSTNAME, \n"
                    + "    INSTRUCTOR.MIDDLENAME, \n"
                    + "    ROOM.ROOM, \n"
                    + "    DAY.DAY, \n"
                    + "    TIME.TIME \n"
                    + "FROM \n"
                    + "    CLASS_OFFER \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON (CLASS_OFFER.COURSE_ID = COURSE.ID) \n"
                    + "INNER JOIN \n"
                    + "    DAY \n"
                    + "ON (CLASS_OFFER.DAY_ID = DAY.ID) \n"
                    + "INNER JOIN \n"
                    + "    INSTRUCTOR \n"
                    + "ON (CLASS_OFFER.INSTRUCTOR_ID = INSTRUCTOR.ID) \n"
                    + "INNER JOIN \n"
                    + "    ROOM \n"
                    + "ON (CLASS_OFFER.ROOM_ID = ROOM.ID) \n"
                    + "INNER JOIN \n"
                    + "    SCHOOL_YEAR \n"
                    + "ON (CLASS_OFFER.SCHOOL_YEAR_ID = SCHOOL_YEAR.ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "INNER JOIN \n"
                    + "    TIME \n"
                    + "ON (CLASS_OFFER.TIME_ID = TIME.ID) \n"
                    + "INNER JOIN \n"
                    + "    SEMESTER \n"
                    + "ON (SCHOOL_YEAR.SEMESTER_ID = SEMESTER.ID) WHERE CLASS_OFFER.ID =" + co.getId() + " ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ClassOffer classOffer = new ClassOffer();
                Instructor instructor = new Instructor();
                Subject subject = new Subject();
                Room room = new Room();
                Day day = new Day();
                Time time = new Time();

                Long classOfferId = rs.getLong("ID");
                String classCode = rs.getString("CLASS_CODE");
                String surname = rs.getString("SURNAME");
                String middlename = rs.getString("MIDDLENAME");
                String firstname = rs.getString("FIRSTNAME");
                String subjectl = rs.getString("SUBJECT");
                Integer units = rs.getInt("UNIT");
                String rooml = rs.getString("ROOM");
                String dayl = rs.getString("DAY");
                String timel = rs.getString("TIME");

                String instructorl = surname + ", " + firstname + " " + middlename.substring(0, 1);

                classOffer.setId(classOfferId);
                classOffer.setClass_code(classCode);
                instructor.setFirstname(firstname);
                instructor.setSurname(surname);
                instructor.setMiddlename(middlename);
                classOffer.setInstructor(instructor);
                subject.setSubject(subjectl);
                subject.setUnit(units);
                classOffer.setSubject(subject);
                room.setRoom(rooml);
                classOffer.setRoom(room);
                day.setDay(dayl);
                classOffer.setDay(day);
                time.setTime(timel);
                classOffer.setTime(time);

                classOfferDetails = classOffer;
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classOfferDetails;
    }

    @Override
    public Long getCurrentClassOfferId() {
        Long classOfferId = 0L;
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            SchoolYearDAOImpl syID = new SchoolYearDAOImpl();
            if (syID.getCurrentSchoolYearIDAndTuitionID().getId() == null) {
                JOptionPane.showMessageDialog(null, "Check the current school year and current date if match to the enrollment.", "WARNING!!", JOptionPane.WARNING_MESSAGE);
                return 0L;
            } else {
                statement = getCon().prepareStatement("SELECT * FROM CLASS_OFFER WHERE CLASS_OFFER.DELETED =FALSE AND CLASS_OFFER.SCHOOL_YEAR_ID =" + syID.getCurrentSchoolYearIDAndTuitionID().getId() + " ORDER BY CLASS_OFFER.CLASS_CODE ASC ");
                rs = statement.executeQuery();
                while (rs.next()) {
                    Long idl = rs.getLong("ID");
                    if (idl > 0) {
                        classOfferId = idl;
                    } else {
                        classOfferId = 0L;
                    }
                }

                rs.close();
                statement.close();
                closeConnection();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return classOfferId;
    }

}
