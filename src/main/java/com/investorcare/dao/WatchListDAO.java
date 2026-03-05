/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.investorcare.dao;

import com.investorcare.model.WatchList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quyt2
 */
public class WatchListDAO {

    private final String INSERT = "insert into WATCHLIST(USER_ID,NAME) values (?,?)";
    private final String UPDATE = "update WATCHLIST SET NAME = ? WHERE WATCHLIST_ID = ?";
    private final String DELETE = "delete FROM WATCHLIST where WATCHLIST_ID = ?";
    private final String SELECT_BY_USER
            = "select WATCHLIST_ID, USER_ID, NAME, CREATED_AT FROM WATCHLIST WHERE USER_ID = ?";

    public boolean insert(WatchList watchList) throws SQLException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = JDBCUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setInt(1, watchList.getUserId());
                ptm.setString(2, watchList.getName());
                checkInsert = ptm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkInsert;
    }
    public String getWatchListNameById(int id) throws Exception {
        String name = "";
        String sql = "SELECT NAME FROM WATCHLIST WHERE WATCHLIST_ID = ?";
        try ( Connection con = JDBCUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("NAME");
                }
            }
        }
        return name;
    }
    public boolean updateNameWatchList(int watchListId, String nameUpdate) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = JDBCUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, nameUpdate);
                ptm.setInt(2, watchListId);
                checkUpdate = ptm.executeUpdate() > 0;

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkUpdate;
    }

    public boolean deleteWatchList(int watchListId) throws SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = JDBCUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setInt(1, watchListId);
                checkDelete = ptm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkDelete;

    }

    public List<WatchList> getWatchListByUserId(int userId) throws SQLException {
        List<WatchList> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SELECT_BY_USER);
                ptm.setInt(1, userId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    WatchList watchList = new WatchList();
                    watchList.setUserId(userId);
                    watchList.setName(rs.getString("NAME"));
                    watchList.setCreateAt(rs.getTimestamp("CREATED_AT"));
                    watchList.setWatchListId(rs.getInt("WATCHLIST_ID"));
                    list.add(watchList);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;

    }

}
