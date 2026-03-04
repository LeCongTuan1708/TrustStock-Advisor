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
public class WatchListItem {
    private int itemId;
    private int watchListId;
    private int assetId;
    private Timestamp addedAt;
    private Asset asset;

    public WatchListItem(int itemId, int watchListId, int assetId, Timestamp addedAt, Asset asset) {
        this.itemId = itemId;
        this.watchListId = watchListId;
        this.assetId = assetId;
        this.addedAt = addedAt;
        this.asset = asset;
    }

    public WatchListItem() {
        this.itemId = 0;
        this.watchListId = 0;
        this.assetId = 0;
        this.addedAt = null;
        this.asset = null;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(int watchListId) {
        this.watchListId = watchListId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @Override
    public String toString() {
        return "WatchListItem{" + "itemId=" + itemId + ", watchListId=" + watchListId + ", assetId=" + assetId + ", addedAt=" + addedAt + ", asset=" + asset + '}';
    }
    
    
    
    
}
