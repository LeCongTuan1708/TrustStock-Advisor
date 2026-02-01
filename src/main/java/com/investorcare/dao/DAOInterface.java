/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.investorcare.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface DAOInterface<T> {

    public ArrayList<T> selectAll() throws ClassNotFoundException, SQLException;
    
    public T selectById(T t)  throws ClassNotFoundException, SQLException;
    
    public int insert(T t) throws ClassNotFoundException, SQLException;
    
    public int insertAll(ArrayList<T> arr) throws ClassNotFoundException, SQLException;
    
    public int delete(T t) throws ClassNotFoundException, SQLException;
    
    public int deleteAll(ArrayList<T> arr) throws ClassNotFoundException, SQLException;
    
    public int update(T t) throws ClassNotFoundException, SQLException;

}
