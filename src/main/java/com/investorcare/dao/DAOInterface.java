/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.investorcare.dao;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface DAOInterface<T> {
    
    public ArrayList<T> selectAll();
    
    public T selectById(T t);
    
    public int insert(T t);
    
    public int insertAll(ArrayList<T> arr);
    
    public int delete(T t);
    
    public int deleteAll(ArrayList<T> arr);
    
    public int update(T t);
    
}
