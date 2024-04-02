/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.ClassEnrollDAO;
import com.aldrin.aregister.model.Citizenship;
import com.aldrin.aregister.model.CivilStatus;
import com.aldrin.aregister.model.ClassEnroll;
import com.aldrin.aregister.model.ClassOffer;
import com.aldrin.aregister.model.Course;
import com.aldrin.aregister.model.Day;
import com.aldrin.aregister.model.Instructor;
import com.aldrin.aregister.model.Room;
import com.aldrin.aregister.model.SchoolYear;
import com.aldrin.aregister.model.Semester;
import com.aldrin.aregister.model.Student;
import com.aldrin.aregister.model.Subject;
import com.aldrin.aregister.model.Time;
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
public class ClassEnrollDAOImpl extends DBConnection implements ClassEnrollDAO {

    @Override
    public void addClassEnroll(ClassEnroll classEnroll) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO CLASS_ENROLL (CLASS_ENROLL.ID , \n"
                    + "    CLASS_ENROLL.CLASS_OFFER_ID, \n"
                    + "    CLASS_ENROLL.STUDENT_ID, \n"
                    + "    CLASS_ENROLL.USER_ID ) VALUES  (?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setLong(2, classEnroll.getClassOffer().getId());
            ps.setLong(3, classEnroll.getStudent().getId());
            ps.setLong(4, classEnroll.getUserAccount().getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClassEnroll(ClassEnroll classEnroll) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CLASS_ENROLL SET   CLASS_ENROLL.CLASS_OFFER_ID =?, \n"
                    + "    CLASS_ENROLL.STUDENT_ID =?, \n"
                    + "    CLASS_ENROLL.TUITION_ID =?, \n"
                    + "    CLASS_ENROLL.USER_ID=?  WHERE CLASS_ENROLL.ID = ?");
            ps.setLong(1, classEnroll.getClassOffer().getId());
            ps.setLong(2, classEnroll.getStudent().getId());
            ps.setLong(3, classEnroll.getTuition().getId());
            ps.setLong(4, classEnroll.getUserAccount().getId());
            ps.setLong(5, classEnroll.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClassEnroll(ClassEnroll course) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CLASS_ENROLL SET DELETED =? WHERE CLASS_ENROLL.ID = ? ");
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
    public ArrayList<ClassEnroll> selectStudentIFEnrolled(ClassEnroll classEnroll, SchoolYear schoolYear) {
        ArrayList<ClassEnroll> list = new ArrayList<>();
        try {
            String query = "SELECT CLASS_OFFER.ID,\n"
                    + "    CLASS_OFFER.CLASS_CODE, \n"
                    + "    SUBJECT.SUBJECT, \n"
                    + "    INSTRUCTOR.FIRSTNAME, \n"
                    + "    INSTRUCTOR.MIDDLENAME, \n"
                    + "    INSTRUCTOR.SURNAME, \n"
                    + "    ROOM.ROOM, \n"
                    + "    DAY.DAY, \n"
                    + "    TIME.TIME, \n"
                    + "    SUBJECT.UNIT \n"
                    + "FROM \n"
                    + "    CLASS_ENROLL \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON ( CLASS_ENROLL.CLASS_OFFER_ID = CLASS_OFFER.ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "INNER JOIN \n"
                    + "    INSTRUCTOR \n"
                    + "ON (CLASS_OFFER.INSTRUCTOR_ID = INSTRUCTOR.ID) \n"
                    + "INNER JOIN \n"
                    + "    ROOM \n"
                    + "ON (CLASS_OFFER.ROOM_ID = ROOM.ID) \n"
                    + "INNER JOIN \n"
                    + "    DAY \n"
                    + "ON  (CLASS_OFFER.DAY_ID = DAY.ID) \n"
                    + "INNER JOIN \n"
                    + "    TIME \n"
                    + "ON (CLASS_OFFER.TIME_ID = TIME.ID) WHERE CLASS_ENROLL.DROP_SUBJECT =FALSE    AND    CLASS_ENROLL.STUDENT_ID =" + classEnroll.getStudent().getId() + " AND CLASS_OFFER.SCHOOL_YEAR_ID =" + schoolYear.getId() + " ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
//              "CLASS OFFER ID", "#", "CODE", "SUBJECT", "INSTRUCTOR", "ROOM", "DAY", "TIME", "UNITS"
                ClassEnroll ce = new ClassEnroll();
                ClassOffer co = new ClassOffer();
                co.setId(rs.getLong("ID"));
                co.setClass_code(rs.getString("CLASS_CODE"));
                Instructor instructor = new Instructor();
                instructor.setFirstname(rs.getString("FIRSTNAME"));
                instructor.setSurname(rs.getString("SURNAME"));
                instructor.setMiddlename(rs.getString("MIDDLENAME"));
                Subject subject = new Subject();
                subject.setSubject(rs.getString("SUBJECT"));
                subject.setUnit(rs.getInt("UNIT"));
                Room room = new Room();
                room.setRoom(rs.getString("ROOM"));
                Day day = new Day();
                day.setDay(rs.getString("DAY"));
                Time time = new Time();
                time.setTime(rs.getString("TIME"));
                co.setTime(time);
                co.setDay(day);
                co.setRoom(room);
                co.setSubject(subject);
                co.setInstructor(instructor);
                ce.setClassOffer(co);
                list.add(ce);
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
    public ArrayList<ClassEnroll> selectClassEnroll(Long syID) {
        ArrayList<ClassEnroll> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    CLASS_ENROLL.STUDENT_ID, \n"
                    + "    STUDENT.FIRSTNAME, \n"
                    + "    STUDENT.MIDDLENAME, \n"
                    + "    STUDENT.SURNAME, \n"
                    + "    STUDENT.GENDER, \n"
                    + "    COURSE.COURSE, \n"
                    + "    SUBJECT.UNIT, \n"
                    + "    TUITION.TUITION_FEE,\n"
                    + "    sum(SUBJECT.UNIT) AS UNITS,  \n"
                    + "    sum(SUBJECT.UNIT*TUITION.TUITION_FEE) AS TOTAL_TUTITION \n"
                    + "FROM \n"
                    + "    CLASS_ENROLL \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON  (CLASS_ENROLL.CLASS_OFFER_ID = CLASS_OFFER.ID) \n"
                    + "INNER JOIN \n"
                    + "    SCHOOL_YEAR \n"
                    + "ON (CLASS_OFFER.SCHOOL_YEAR_ID = SCHOOL_YEAR.ID) \n"
                    + "INNER JOIN \n"
                    + "    TUITION \n"
                    + "ON (SCHOOL_YEAR.TUITION_ID = TUITION.ID) \n"
                    + "INNER JOIN \n"
                    + "    STUDENT \n"
                    + "ON (CLASS_ENROLL.STUDENT_ID = STUDENT.ID) \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON (STUDENT.COURSE_ID = COURSE.ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) WHERE CLASS_ENROLL.DROP_SUBJECT =FALSE AND  CLASS_OFFER.SCHOOL_YEAR_ID= " + syID + "  \n"
                    + "   GROUP BY CLASS_ENROLL.STUDENT_ID, \n"
                    + "    STUDENT.FIRSTNAME, \n"
                    + "    STUDENT.MIDDLENAME, \n"
                    + "    STUDENT.SURNAME, \n"
                    + "    STUDENT.GENDER, \n"
                    + "    COURSE.COURSE, \n"
                    + "    SUBJECT.UNIT, \n"
                    + "    TUITION.TUITION_FEE ORDER BY STUDENT.SURNAME ASC";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ClassEnroll ce = new ClassEnroll();
                ClassOffer co = new ClassOffer();
                Student student = new Student();
                Course course = new Course();
                Subject subject = new Subject();
                Tuition tuition = new Tuition();
                SchoolYear sy = new SchoolYear();
                student.setId(rs.getLong("STUDENT_ID"));
                student.setFirstname(rs.getString("FIRSTNAME"));
                student.setMiddlename(rs.getString("MIDDLENAME"));
                student.setSurname(rs.getString("SURNAME"));
                student.setGender(rs.getString("GENDER"));
                ce.setStudent(student);//STUDENT
                course.setCourse(rs.getString("COURSE"));
                co.setCourse(course);
                subject.setUnit(rs.getInt("UNITS"));
                co.setSubject(subject);
                tuition.setTuitionFee(rs.getFloat("TUITION_FEE"));
                sy.setTuition(tuition);
                co.setSchoolYear(sy);
                ce.setClassOffer(co);//COURSE,UNITS

                list.add(ce);
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
    public void comboBoxClassEnroll() {
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
    public void comboBoxInstructorSubject(Long instructorId, Long syId) {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT \n"
                    + "    CLASS_ENROLL.CLASS_OFFER_ID, \n"
                    + "    CLASS_OFFER.CLASS_CODE, \n"
                    + "    SUBJECT.SUBJECT\n"
                    + "\n"
                    + "\n"
                    + "FROM \n"
                    + "    CLASS_ENROLL \n"
                    + "INNER JOIN \n"
                    + "    STUDENT \n"
                    + "ON (CLASS_ENROLL.STUDENT_ID = STUDENT.ID) \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON (CLASS_ENROLL.CLASS_OFFER_ID = CLASS_OFFER.ID) \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON (STUDENT.COURSE_ID = COURSE.ID) \n"
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
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "INNER JOIN \n"
                    + "    TIME \n"
                    + "ON (CLASS_OFFER.TIME_ID = TIME.ID) \n"
                    + "INNER JOIN \n"
                    + "    CIVIL_STATUS \n"
                    + "ON (STUDENT.CIVIL_STATUS_ID = CIVIL_STATUS.ID) \n"
                    + "INNER JOIN \n"
                    + "    CITIZENSHIP \n"
                    + "ON (INSTRUCTOR.CITIZENSHIP_ID = CITIZENSHIP.ID) \n"
                    + "INNER JOIN \n"
                    + "    SCHOOL_YEAR \n"
                    + "ON (CLASS_OFFER.SCHOOL_YEAR_ID = SCHOOL_YEAR.ID) \n"
                    + "INNER JOIN \n"
                    + "    TUITION \n"
                    + "ON (SCHOOL_YEAR.TUITION_ID = TUITION.ID) \n"
                    + "INNER JOIN \n"
                    + "    SEMESTER \n"
                    + "ON (SCHOOL_YEAR.SEMESTER_ID = SEMESTER.ID) \n"
                    + "WHERE \n"
                    + "    CLASS_OFFER.INSTRUCTOR_ID= " + instructorId + " AND CLASS_OFFER.SCHOOL_YEAR_ID="+syId+"\n"
                    + "    AND CLASS_ENROLL.DROP_SUBJECT = FALSE \n"
                    + "\n"
                    + "GROUP BY \n"
                    + "   CLASS_ENROLL.CLASS_OFFER_ID, \n"
                    + "    CLASS_OFFER.CLASS_CODE, \n"
                    + "    SUBJECT.SUBJECT ORDER BY CLASS_OFFER.CLASS_CODE ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("CLASS_OFFER_ID");
                String codel = rs.getString("CLASS_CODE");
                String subjectl = rs.getString("SUBJECT");
                this.getList().add(new ComboBoxList(idl, "[" + codel + "]-" + subjectl));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void comboBoxInstructor() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT \n"
                    + "    INSTRUCTOR.ID, \n"
                    + "    INSTRUCTOR.FIRSTNAME, \n"
                    + "    INSTRUCTOR.SURNAME, \n"
                    + "    INSTRUCTOR.MIDDLENAME \n"
                    + "FROM \n"
                    + "    CLASS_ENROLL \n"
                    + "INNER JOIN \n"
                    + "    STUDENT \n"
                    + "ON (CLASS_ENROLL.STUDENT_ID = STUDENT.ID) \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON (CLASS_ENROLL.CLASS_OFFER_ID = CLASS_OFFER.ID) \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON (STUDENT.COURSE_ID = COURSE.ID) \n"
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
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "INNER JOIN \n"
                    + "    TIME \n"
                    + "ON (CLASS_OFFER.TIME_ID = TIME.ID) \n"
                    + "INNER JOIN \n"
                    + "    CIVIL_STATUS \n"
                    + "ON (STUDENT.CIVIL_STATUS_ID = CIVIL_STATUS.ID) \n"
                    + "INNER JOIN \n"
                    + "    CITIZENSHIP \n"
                    + "ON (INSTRUCTOR.CITIZENSHIP_ID = CITIZENSHIP.ID) \n"
                    + "INNER JOIN \n"
                    + "    SCHOOL_YEAR \n"
                    + "ON (CLASS_OFFER.SCHOOL_YEAR_ID = SCHOOL_YEAR.ID) \n"
                    + "INNER JOIN \n"
                    + "    TUITION \n"
                    + "ON (SCHOOL_YEAR.TUITION_ID = TUITION.ID) \n"
                    + "INNER JOIN \n"
                    + "    SEMESTER \n"
                    + "ON (SCHOOL_YEAR.SEMESTER_ID = SEMESTER.ID) \n"
                    + "WHERE CLASS_ENROLL.DROP_SUBJECT = FALSE \n"
                    + "\n"
                    + "GROUP BY \n"
                    + "    INSTRUCTOR.ID, \n"
                    + "    INSTRUCTOR.FIRSTNAME, \n"
                    + "    INSTRUCTOR.SURNAME, \n"
                    + "    INSTRUCTOR.MIDDLENAME  ORDER BY INSTRUCTOR.SURNAME ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String firstnamel = rs.getString("FIRSTNAME");
                String surnamel = rs.getString("SURNAME");
                String middlenamel = rs.getString("MIDDLENAME");
                this.getList().add(new ComboBoxList(idl, surnamel + ", " + firstnamel + " " + middlenamel.substring(0, 1) + "."));
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
                    + "    MAX(CLASS_ENROLL.ID) AS ID  \n"
                    + "FROM \n"
                    + "    CLASS_ENROLL ");
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
    public Long selectStudentSubject(ClassEnroll classEnroll) {
        Long ce = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT CLASS_OFFER.ID,\n"
                    + "    CLASS_OFFER.CLASS_CODE, \n"
                    + "    SUBJECT.SUBJECT, \n"
                    + "    INSTRUCTOR.FIRSTNAME, \n"
                    + "    INSTRUCTOR.MIDDLENAME, \n"
                    + "    INSTRUCTOR.SURNAME, \n"
                    + "    ROOM.ROOM, \n"
                    + "    DAY.DAY, \n"
                    + "    TIME.TIME, \n"
                    + "    SUBJECT.UNIT \n"
                    + "FROM \n"
                    + "    CLASS_ENROLL \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON (CLASS_ENROLL.CLASS_OFFER_ID = CLASS_OFFER.ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "INNER JOIN \n"
                    + "    INSTRUCTOR \n"
                    + "ON (CLASS_OFFER.INSTRUCTOR_ID = INSTRUCTOR.ID) \n"
                    + "INNER JOIN \n"
                    + "    ROOM \n"
                    + "ON (CLASS_OFFER.ROOM_ID = ROOM.ID) \n"
                    + "INNER JOIN \n"
                    + "    DAY \n"
                    + "ON  (CLASS_OFFER.DAY_ID = DAY.ID) \n"
                    + "INNER JOIN \n"
                    + "    TIME \n"
                    + "ON (CLASS_OFFER.TIME_ID = TIME.ID) WHERE CLASS_ENROLL.STUDENT_ID =" + classEnroll.getStudent().getId() + " ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ce = rs.getLong("ID");
            }
            rs.close();
            statement.close();
//            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ce;
    }

    @Override
    public Float selectStudentByTuition(Long studentID) {
        Float tuition = 0f;
        try {
            getDBConn();
            SchoolYear currentSY = new SchoolYearDAOImpl().getCurrentSchoolYearIDAndTuitionID();
            System.out.println("current SY:" + currentSY.getId());
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    SUM(SUBJECT.UNIT*TUITION.TUITION_FEE) AS TUITIONFEE \n"
                    + "FROM \n"
                    + "    CLASS_ENROLL \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON  (CLASS_ENROLL.CLASS_OFFER_ID = CLASS_OFFER.ID) \n"
                    + "INNER JOIN \n"
                    + "    SCHOOL_YEAR \n"
                    + "ON (CLASS_OFFER.SCHOOL_YEAR_ID = SCHOOL_YEAR.ID) \n"
                    + "INNER JOIN \n"
                    + "    TUITION \n"
                    + "ON (SCHOOL_YEAR.TUITION_ID = TUITION.ID) \n"
                    + "INNER JOIN \n"
                    + "    STUDENT \n"
                    + "ON (CLASS_ENROLL.STUDENT_ID = STUDENT.ID) \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON (STUDENT.COURSE_ID = COURSE.ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) WHERE CLASS_ENROLL.DROP_SUBJECT =FALSE AND  CLASS_OFFER.SCHOOL_YEAR_ID =" + currentSY.getId() + " AND CLASS_ENROLL.STUDENT_ID =" + studentID + " ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tuition = rs.getFloat("TUITIONFEE");
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tuition;
    }

    @Override
    public Boolean selectDisableRegister(Long studentID, Long classOfferId) {
        Boolean selectStudentIfEnrolled = false;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "   CLASS_ENROLL.STUDENT_ID, \n"
                    + "   CLASS_ENROLL.CLASS_OFFER_ID \n"
                    + "FROM \n"
                    + "    CLASS_ENROLL \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON (CLASS_ENROLL.CLASS_OFFER_ID = CLASS_OFFER.ID)  WHERE CLASS_ENROLL.STUDENT_ID =" + studentID + " AND CLASS_ENROLL.CLASS_OFFER_ID =" + classOfferId + " ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("STUDENT_ID");
                if (idl == 0) {
                    selectStudentIfEnrolled = false;
                } else {
                    selectStudentIfEnrolled = true;
                }
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectStudentIfEnrolled;
    }

    @Override
    public void updateDropSubject(Long studentId, Long classOfferId) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CLASS_ENROLL SET CLASS_ENROLL.DROP_SUBJECT =TRUE WHERE CLASS_ENROLL.STUDENT_ID = ? and CLASS_ENROLL.CLASS_OFFER_ID =?");
            ps.setLong(1, studentId);
            ps.setLong(2, classOfferId);
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ClassEnroll> selectInstructorsSubject(Long instructorId, Long syId) {
        ArrayList<ClassEnroll> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    CLASS_OFFER.CLASS_CODE, \n"
                    + "    SUBJECT.SUBJECT, \n"
                    + "    SUBJECT.UNIT, \n"
                    + "    DAY.DAY, \n"
                    + "    TIME.TIME, \n"
                    + "    ROOM.ROOM, CLASS_OFFER.INSTRUCTOR_ID,\n"
                    + "    CLASS_OFFER.ID,CLASS_OFFER.SCHOOL_YEAR_ID,\n"
                    + "    COURSE.COURSE ,COUNT(CLASS_ENROLL.ID) AS NO_STUDENTS\n"
                    + "    \n"
                    + "FROM \n"
                    + "    CLASS_ENROLL \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON (CLASS_ENROLL.CLASS_OFFER_ID = CLASS_OFFER.ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON (CLASS_OFFER.COURSE_ID = COURSE.ID) \n"
                    + "INNER JOIN \n"
                    + "    DAY \n"
                    + "ON (CLASS_OFFER.DAY_ID = DAY.ID) \n"
                    + "INNER JOIN \n"
                    + "    TIME \n"
                    + "ON (CLASS_OFFER.TIME_ID = TIME.ID) \n"
                    + "INNER JOIN \n"
                    + "    ROOM \n"
                    + "ON (CLASS_OFFER.ROOM_ID = ROOM.ID) \n"
                    + "INNER JOIN \n"
                    + "    INSTRUCTOR \n"
                    + "ON (CLASS_OFFER.INSTRUCTOR_ID = INSTRUCTOR.ID) WHERE CLASS_OFFER.INSTRUCTOR_ID =" + instructorId + " AND  CLASS_ENROLL.DROP_SUBJECT =FALSE AND CLASS_OFFER.SCHOOL_YEAR_ID =" + syId + " \n"
                    + "   GROUP BY   CLASS_OFFER.CLASS_CODE, \n"
                    + "    SUBJECT.SUBJECT, \n"
                    + "    SUBJECT.UNIT, \n"
                    + "    DAY.DAY, \n"
                    + "    TIME.TIME, \n"
                    + "    ROOM.ROOM, CLASS_OFFER.INSTRUCTOR_ID,\n"
                    + "    CLASS_OFFER.ID,\n"
                    + "    COURSE.COURSE,CLASS_OFFER.SCHOOL_YEAR_ID   \n"
                    + "        ORDER BY COURSE.COURSE,SUBJECT.SUBJECT ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ClassEnroll ce = new ClassEnroll();
                ClassOffer co = new ClassOffer();
                Student student = new Student();
                Course course = new Course();
                Subject subject = new Subject();
                SchoolYear schoolYear = new SchoolYear();
                Instructor instructor = new Instructor();
                instructor.setId(instructorId);
                co.setInstructor(instructor);
                Room room = new Room();
                Day day = new Day();
                Time time = new Time();
                ce.setStudent(student);//STUDENT
                co.setId(rs.getLong("ID"));
                schoolYear.setId(rs.getLong("SCHOOL_YEAR_ID"));
                co.setSchoolYear(schoolYear);
                course.setCourse(rs.getString("COURSE"));
                co.setCourse(course);
                co.setInstructor(instructor);
                subject.setUnit(rs.getInt("UNIT"));
                subject.setSubject(rs.getString("SUBJECT"));
                room.setRoom(rs.getString("ROOM"));
                day.setDay(rs.getString("DAY"));
                time.setTime(rs.getString("TIME"));
                co.setClass_code(rs.getString("CLASS_CODE"));
                co.setSubject(subject);
                co.setRoom(room);
                co.setDay(day);
                co.setTime(time);
                ce.setNoOfStudents(rs.getInt("NO_STUDENTS"));
                ce.setClassOffer(co);//COURSE,UNITS

                list.add(ce);
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
    public ArrayList<ClassEnroll> selectInstructorsSubject() {
        ArrayList<ClassEnroll> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    CLASS_OFFER.CLASS_CODE, \n"
                    + "    SUBJECT.SUBJECT, \n"
                    + "    SUBJECT.UNIT, \n"
                    + "    DAY.DAY, \n"
                    + "    TIME.TIME, \n"
                    + "    ROOM.ROOM, CLASS_OFFER.INSTRUCTOR_ID,\n"
                    + "    CLASS_OFFER.ID,\n"
                    + "    COURSE.COURSE ,CLASS_OFFER.SCHOOL_YEAR_ID,COUNT(CLASS_ENROLL.ID) AS NO_STUDENTS\n"
                    + "    \n"
                    + "FROM \n"
                    + "    CLASS_ENROLL \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON (CLASS_ENROLL.CLASS_OFFER_ID = CLASS_OFFER.ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON (CLASS_OFFER.COURSE_ID = COURSE.ID) \n"
                    + "INNER JOIN \n"
                    + "    DAY \n"
                    + "ON (CLASS_OFFER.DAY_ID = DAY.ID) \n"
                    + "INNER JOIN \n"
                    + "    TIME \n"
                    + "ON (CLASS_OFFER.TIME_ID = TIME.ID) \n"
                    + "INNER JOIN \n"
                    + "    ROOM \n"
                    + "ON (CLASS_OFFER.ROOM_ID = ROOM.ID) \n"
                    + "INNER JOIN \n"
                    + "    INSTRUCTOR \n"
                    + "ON (CLASS_OFFER.INSTRUCTOR_ID = INSTRUCTOR.ID) WHERE   CLASS_ENROLL.DROP_SUBJECT =FALSE \n"
                    + "   GROUP BY   CLASS_OFFER.CLASS_CODE, \n"
                    + "    SUBJECT.SUBJECT, \n"
                    + "    SUBJECT.UNIT, \n"
                    + "    DAY.DAY, \n"
                    + "    TIME.TIME, \n"
                    + "    ROOM.ROOM, CLASS_OFFER.INSTRUCTOR_ID ,\n"
                    + "    CLASS_OFFER.ID, \n"
                    + "    COURSE.COURSE, CLASS_OFFER.SCHOOL_YEAR_ID \n"
                    + "        ORDER BY COURSE.COURSE,SUBJECT.SUBJECT ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ClassEnroll ce = new ClassEnroll();
                ClassOffer co = new ClassOffer();
                Student student = new Student();
                Course course = new Course();
                Subject subject = new Subject();
                Room room = new Room();
                Day day = new Day();
                Time time = new Time();
                Instructor instructor = new Instructor();
                instructor.setId(rs.getLong("INSTRUCTOR_ID"));
                co.setInstructor(instructor);
                co.setId(rs.getLong("ID"));
                ce.setStudent(student);//STUDENT
                course.setCourse(rs.getString("COURSE"));
                co.setCourse(course);
                co.setInstructor(instructor);
                subject.setUnit(rs.getInt("UNIT"));
                subject.setSubject(rs.getString("SUBJECT"));
                room.setRoom(rs.getString("ROOM"));
                day.setDay(rs.getString("DAY"));
                time.setTime(rs.getString("TIME"));
                co.setClass_code(rs.getString("CLASS_CODE"));
                co.setSubject(subject);
                co.setRoom(room);
                co.setDay(day);
                co.setTime(time);
                ce.setNoOfStudents(rs.getInt("NO_STUDENTS"));
                ce.setClassOffer(co);//COURSE,UNITS

                list.add(ce);
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
    public ArrayList<ClassEnroll> selectEnrolledStudents(Long schoolYear) {
        ArrayList<ClassEnroll> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    STUDENT.ID,\n"
                    + "    STUDENT.FIRSTNAME, \n"
                    + "    STUDENT.MIDDLENAME, \n"
                    + "    STUDENT.SURNAME, \n"
                    + "    COURSE.COURSE, \n"
                    + "    CITIZENSHIP.CITIZENSHIP, \n"
                    + "    CIVIL_STATUS.CIVIL_STATUS, \n"
                    + "    STUDENT.GENDER, \n"
                    + "    sum(SUBJECT.UNIT) AS UNITS \n"
                    + "FROM \n"
                    + "    SCHOOL_YEAR \n"
                    + "INNER JOIN \n"
                    + "    SEMESTER \n"
                    + "ON (SCHOOL_YEAR.SEMESTER_ID = SEMESTER.ID) \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON (SCHOOL_YEAR.ID = CLASS_OFFER.SCHOOL_YEAR_ID) \n"
                    + "INNER JOIN \n"
                    + "    CLASS_ENROLL \n"
                    + "ON (CLASS_OFFER.ID = CLASS_ENROLL.CLASS_OFFER_ID) \n"
                    + "INNER JOIN \n"
                    + "    STUDENT \n"
                    + "ON ( CLASS_ENROLL.STUDENT_ID = STUDENT.ID) \n"
                    + "INNER JOIN \n"
                    + "    CITIZENSHIP \n"
                    + "ON (STUDENT.CITIZENSHIP_ID = CITIZENSHIP.ID) \n"
                    + "INNER JOIN \n"
                    + "    CIVIL_STATUS \n"
                    + "ON (STUDENT.CIVIL_STATUS_ID = CIVIL_STATUS.ID) \n"
                    + "INNER JOIN \n"
                    + "    COURSE \n"
                    + "ON (STUDENT.COURSE_ID = COURSE.ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "WHERE   CLASS_OFFER.SCHOOL_YEAR_ID = " + schoolYear + " AND CLASS_ENROLL.DROP_SUBJECT = FALSE \n"
                    + " GROUP BY  STUDENT.ID,\n"
                    + "    STUDENT.FIRSTNAME, \n"
                    + "    STUDENT.MIDDLENAME, \n"
                    + "    STUDENT.SURNAME, \n"
                    + "    COURSE.COURSE, \n"
                    + "    CITIZENSHIP.CITIZENSHIP, \n"
                    + "    CIVIL_STATUS.CIVIL_STATUS, \n"
                    + "    STUDENT.GENDER\n"
                    + "    ORDER BY STUDENT.SURNAME ASC  ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ClassEnroll ce = new ClassEnroll();
                ClassOffer co = new ClassOffer();
                Student student = new Student();
                Course course = new Course();
                CivilStatus civilStatus = new CivilStatus();
                Citizenship citizenship = new Citizenship();
                Subject subject = new Subject();
                student.setId(rs.getLong("ID"));
                student.setFirstname(rs.getString("FIRSTNAME"));
                student.setMiddlename(rs.getString("MIDDLENAME"));
                student.setSurname(rs.getString("SURNAME"));
                student.setGender(rs.getString("GENDER"));
                civilStatus.setCivilStatus(rs.getString("CIVIL_STATUS"));
                student.setCivilStatus(civilStatus);
                citizenship.setCitizenship(rs.getString("CITIZENSHIP"));
                student.setCitizenship(citizenship);
                course.setCourse(rs.getString("COURSE"));
                student.setCourse(course);
                ce.setStudent(student);
                subject.setUnit(rs.getInt("UNITS"));
                co.setSubject(subject);
                ce.setClassOffer(co);

                list.add(ce);
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
    public ArrayList<ClassEnroll> selectTotalTuition(Long schoolYear) {
        ArrayList<ClassEnroll> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    STUDENT.FIRSTNAME, \n"
                    + "    STUDENT.MIDDLENAME, \n"
                    + "    STUDENT.SURNAME, \n"
                    + "    STUDENT.GENDER, \n"
                    + "    SEMESTER.SEMESTER, \n"
                    + "    SCHOOL_YEAR.SCHOOL_YEAR, \n"
                    + "    TUITION.TUITION_FEE, \n"
                    + "    SUBJECT.UNIT,\n"
                    + "    sum(SUBJECT.UNIT) as UNITS,\n"
                    + "    (sum(SUBJECT.UNIT)* TUITION.TUITION_FEE)AS TOTAL_TUITION \n"
                    + "    \n"
                    + "FROM \n"
                    + "    SCHOOL_YEAR \n"
                    + "INNER JOIN \n"
                    + "    TUITION \n"
                    + "ON (SCHOOL_YEAR.TUITION_ID = TUITION.ID) \n"
                    + "INNER JOIN \n"
                    + "    SEMESTER \n"
                    + "ON (SCHOOL_YEAR.SEMESTER_ID = SEMESTER.ID) \n"
                    + "INNER JOIN \n"
                    + "    CLASS_OFFER \n"
                    + "ON (SCHOOL_YEAR.ID = CLASS_OFFER.SCHOOL_YEAR_ID) \n"
                    + "INNER JOIN \n"
                    + "    SUBJECT \n"
                    + "ON (CLASS_OFFER.SUBJECT_ID = SUBJECT.ID) \n"
                    + "INNER JOIN \n"
                    + "    CLASS_ENROLL \n"
                    + "ON (CLASS_OFFER.ID = CLASS_ENROLL.CLASS_OFFER_ID) \n"
                    + "INNER JOIN \n"
                    + "    STUDENT \n"
                    + "ON (CLASS_ENROLL.STUDENT_ID = STUDENT.ID)  \n"
                    + "WHERE   CLASS_OFFER.SCHOOL_YEAR_ID = " + schoolYear + " AND CLASS_ENROLL.DROP_SUBJECT = FALSE\n"
                    + "   GROUP BY \n"
                    + "    STUDENT.FIRSTNAME, \n"
                    + "    STUDENT.MIDDLENAME, \n"
                    + "    STUDENT.SURNAME, \n"
                    + "    STUDENT.GENDER, \n"
                    + "    SEMESTER.SEMESTER, \n"
                    + "    SCHOOL_YEAR.SCHOOL_YEAR, \n"
                    + "    TUITION.TUITION_FEE, \n"
                    + "    SUBJECT.UNIT order by STUDENT.SURNAME ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ClassEnroll ce = new ClassEnroll();
                ClassOffer co = new ClassOffer();
                Student student = new Student();
                Subject subject = new Subject();
                SchoolYear sy = new SchoolYear();
                Semester semester = new Semester();
                Tuition tuition = new Tuition();
                tuition.setTuitionFee(rs.getFloat("TUITION_FEE"));

                semester.setSemester(rs.getString("SEMESTER"));
                sy.setSemester(semester);
                sy.setSchoolYear(rs.getString("SCHOOL_YEAR"));
                sy.setTuition(tuition);
                co.setSchoolYear(sy);

                student.setFirstname(rs.getString("FIRSTNAME"));
                student.setMiddlename(rs.getString("MIDDLENAME"));
                student.setSurname(rs.getString("SURNAME"));
                student.setGender(rs.getString("GENDER"));

                ce.setStudent(student);
                subject.setUnit(rs.getInt("UNITS"));
                co.setSubject(subject);
                ce.setClassOffer(co);

                list.add(ce);
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
    public void comboBoxInstructorSubject(Long instructorId) {
    }

}
