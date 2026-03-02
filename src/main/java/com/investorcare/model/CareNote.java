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
public class CareNote {
    private int noteId;
    private int userId;
    private int assetId;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Asset asset;

    
    public CareNote(int noteId, int userId, int assetId, String title, String content, Timestamp createdAt, Timestamp updatedAt) {
        this.noteId = noteId;
        this.userId = userId;
        this.assetId = assetId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.asset =null;
    }

    public CareNote() {
        this.noteId = 0;
        this.userId = 0;
        this.assetId = 0;
        this.title = "";
        this.content = "";
        this.createdAt = null;
        this.updatedAt = null;
        this.asset =null;
    }
    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CareNote{" + "noteId=" + noteId + ", userId=" + userId + ", assetId=" + assetId + ", title=" + title + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
    
    
}
