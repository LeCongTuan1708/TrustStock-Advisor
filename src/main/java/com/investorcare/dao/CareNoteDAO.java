/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.investorcare.dao;

import com.investorcare.model.CareNote;
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
public class CareNoteDAO {
    private final String INSERT = "insert into CARE_NOTE(USER_ID,ASSET_ID,TITLE,[CONTENT]) values (?,?,?,?)";
    private final String UPDATE = "UPDATE CARE_NOTE SET TITLE = ?, CONTENT = ?, ASSET_ID = ?, UPDATED_AT = GETDATE() WHERE NOTE_ID = ?";
    private final String DELETE = "DELETE FROM CARE_NOTE WHERE NOTE_ID = ?";
    private final String SELECT_BY_USER
            = "SELECT c.NOTE_ID, c.USER_ID, c.ASSET_ID, c.TITLE, c.CONTENT, c.CREATED_AT, c.UPDATED_AT, "
            + "a.SYMBOL, a.NAME, a.TYPE "
            + "FROM CARE_NOTE c "
            + "JOIN ASSET a ON c.ASSET_ID = a.ASSET_ID "
            + "WHERE c.USER_ID = ?";
    private final String SELECT_BY_NOTEID = "SELECT NOTE_ID, USER_ID, ASSET_ID, TITLE, CONTENT FROM CARE_NOTE WHERE NOTE_ID = ?";
    
    public boolean insert(CareNote careNote) throws SQLException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = JDBCUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setInt(1, careNote.getUserId());
                ptm.setInt(2, careNote.getAssetId());
                ptm.setString(3, careNote.getTitle());
                ptm.setString(4, careNote.getContent());
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


    public boolean deleteCareNote(int careNoteId) throws SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = JDBCUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setInt(1, careNoteId);
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

    
    public List<CareNote> getCareNoteByUserId(int userId) throws SQLException {
        List<CareNote> list = new ArrayList<>();
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
                    CareNote careNote = new CareNote();
                    careNote.setUserId(userId);
                    careNote.setNoteId(rs.getInt("NOTE_ID"));
                    careNote.setAssetId(rs.getInt("ASSET_ID"));
                    careNote.setTitle(rs.getString("TITLE"));
                    careNote.setContent(rs.getString("CONTENT"));
                    careNote.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                    careNote.setUpdatedAt(rs.getTimestamp("UPDATED_AT"));

                    com.investorcare.model.Asset asset = new com.investorcare.model.Asset();
                    asset.setAssetId(rs.getInt("ASSET_ID"));
                    asset.setSymbol(rs.getString("SYMBOL"));
                    asset.setName(rs.getString("NAME"));
                    asset.setType(rs.getString("TYPE"));

                    careNote.setAsset(asset);

                    list.add(careNote);
                }
            }
        } catch (Exception e) { 
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
    
    
    public boolean updateCareNote(CareNote careNote) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = JDBCUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, careNote.getTitle());
                ptm.setString(2, careNote.getContent());
                ptm.setInt(3, careNote.getAssetId());
                ptm.setInt(4, careNote.getNoteId());
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
    
    
    public CareNote getCareNoteById(int noteId) throws SQLException {
        CareNote careNote = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SELECT_BY_NOTEID);
                ptm.setInt(1, noteId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    careNote = new CareNote();
                    careNote.setNoteId(rs.getInt("NOTE_ID"));
                    careNote.setUserId(rs.getInt("USER_ID"));
                    careNote.setAssetId(rs.getInt("ASSET_ID"));
                    careNote.setTitle(rs.getString("TITLE"));
                    careNote.setContent(rs.getString("CONTENT"));
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
        return careNote;
    }
    
    
}
