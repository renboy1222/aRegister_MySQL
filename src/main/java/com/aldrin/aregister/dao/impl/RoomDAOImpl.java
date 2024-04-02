/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.aregister.dao.impl;

import com.aldrin.aregister.dao.RoomDAO;
import com.aldrin.aregister.model.Room;
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
public class RoomDAOImpl extends DBConnection implements RoomDAO {

    @Override
    public void addRoom(Room room) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO ROOM (ID,ROOM,LOCATION) VALUES  (?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, room.getRoom());
            ps.setString(3, room.getLocation());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoom(Room room) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE ROOM SET ROOM =?, LOCATION =? WHERE ROOM.ID = ?");
            ps.setString(1, room.getRoom());
            ps.setString(2, room.getLocation());
            ps.setLong(3, room.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoom(Room room) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE ROOM SET DELETED =? WHERE ROOM.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, room.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<Room> selectRoom() {
        ArrayList<Room> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM ROOM WHERE ROOM.DELETED =FALSE  ORDER BY ROOM ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Room c = new Room();
                c.setId(rs.getLong("ID"));
                c.setRoom(rs.getString("ROOM"));
                c.setLocation(rs.getString("LOCATION"));
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
    public void comboBoxRoom() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM ROOM WHERE ROOM.DELETED =FALSE  ORDER BY ROOM ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String rooml = rs.getString("ROOM");
                String locationl = rs.getString("LOCATION");
                this.getList().add(new ComboBoxList(idl, rooml));
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
                    + "    MAX(ROOM.ID) AS ID  \n"
                    + "FROM \n"
                    + "    ROOM ");
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
