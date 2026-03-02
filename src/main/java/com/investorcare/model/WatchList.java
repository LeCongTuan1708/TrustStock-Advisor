/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.investorcare.model;
import java.sql.Timestamp;
/**
 *
 * @author quyt2
 */
public class WatchList {
    private int watchListId;
    private int userId;
    private String name;
    private Timestamp createAt;

    public WatchList() {
        this.watchListId = 0;
        this.userId = 0;
        this.name = "";
        this.createAt = null;
    }

    public WatchList(int watchListId, int userId, String name, Timestamp createAt) {
        this.watchListId = watchListId;
        this.userId = userId;
        this.name = name;
        this.createAt = createAt;
    }

    public int getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(int watchListId) {
        this.watchListId = watchListId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "WatchList{" + "watchListId=" + watchListId + ", userId=" + userId + ", name=" + name + ", createAt=" + createAt + '}';
    }
    
    
    
    
}
