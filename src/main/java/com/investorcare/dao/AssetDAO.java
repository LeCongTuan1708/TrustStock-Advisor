/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.investorcare.dao;

import com.investorcare.model.Asset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class AssetDAO implements DAOInterface<Asset> {

    private static final String SELECT_ALL = "SELECT * from ASSET";
    private static final String SELECT = "SELECT * FROM ASSET WHERE SYMBOL LIKE ? OR NAME LIKE ?;";
    private static final String INSERT = "INSERT INTO ASSET(TYPE,SYMBOL,EXCHANGE,NAME,STATUS) VALUES(?,?,?,?,?)";
    private static final String UPDATE = "UPDATE ASSET set TYPE = ?, SYMBOL = ?, EXCHANGE = ?, NAME = ?, STATUS =?";

    public ArrayList<Asset> selectAll() throws ClassNotFoundException, SQLException {
        ArrayList<Asset> kq = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JDBCUtils.getConnection();

            pst = conn.prepareStatement(SELECT_ALL);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int assetId = rs.getInt("ASSET_ID");
                String type = rs.getString("TYPE");
                String symbol = rs.getString("SYMBOL");
                String exchange = rs.getString("EXCHANGE");
                String name = rs.getString("NAME");
                String status = rs.getString("STATUS");

                Asset asset = new Asset(assetId, type, symbol, exchange, name, status);
                kq.add(asset);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return kq;
    }

    public ArrayList<Asset> selectById(String keyword) throws ClassNotFoundException, SQLException {
        ArrayList<Asset> kq = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JDBCUtils.getConnection();

            pst = conn.prepareStatement(SELECT);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int assetId = rs.getInt("ASSET_ID");
                String type = rs.getString("TYPE");
                String symbol = rs.getString("SYMBOL");
                String exchange = rs.getString("EXCHANGE");
                String name = rs.getString("NAME");
                String status = rs.getString("STATUS");

                Asset asset = new Asset(assetId, type, symbol, exchange, name, status);
                kq.add(asset);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return kq;
    }

    @Override
    public int insert(Asset t) throws ClassNotFoundException, SQLException {
        int kq = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JDBCUtils.getConnection();

            pst = conn.prepareStatement(INSERT);
            pst.setString(1, t.getType());
            pst.setString(2, t.getSymbol());
            pst.setString(3, t.getExchange());
            pst.setString(4, t.getName());
            pst.setString(5, t.getStatus());
            kq = pst.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return kq;
    }

    public int insertAll(ArrayList<Asset> arr) throws ClassNotFoundException, SQLException  {
        int dem = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JDBCUtils.getConnection();

            pst = conn.prepareStatement(INSERT);
            for (Asset t : arr) {
                pst.setString(1, t.getType());
                pst.setString(2, t.getSymbol());
                pst.setString(3, t.getExchange());
                pst.setString(4, t.getName());
                pst.setString(5, t.getStatus());
                
                int kq = pst.executeUpdate();
                if (kq > 0) {
                    dem++;
                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return dem;
    }

    public int delete(Asset t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int deleteAll(ArrayList<Asset> arr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int update(Asset t) throws ClassNotFoundException, SQLException   {
        int kq = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JDBCUtils.getConnection();

            pst = conn.prepareStatement(UPDATE);
            pst.setString(1, t.getType());
            pst.setString(2, t.getSymbol());
            pst.setString(3, t.getExchange());
            pst.setString(4, t.getName());
            pst.setString(5, t.getStatus());
            
            kq = pst.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AssetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return kq;
    }

    public Asset selectById(Asset t) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
