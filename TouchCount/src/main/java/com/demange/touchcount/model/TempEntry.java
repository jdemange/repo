/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.model;

/**
 *
 * @author jocel
 */
public class TempEntry {
    private int auditId;
    private String mapNumber;
    private String roomName;
    private String floorNumber;
    private String baseCode;
    private String extendedCode;
    private int quantity;
    private String comments;
    private String fixtureHeight;
    private String ceilingHeight;
    private String roomType;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public String getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(String mapNumber) {
        this.mapNumber = mapNumber;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getExtendedCode() {
        return extendedCode;
    }

    public void setExtendedCode(String extendedCode) {
        this.extendedCode = extendedCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFixtureHeight() {
        return fixtureHeight;
    }

    public void setFixtureHeight(String fixtureHeight) {
        this.fixtureHeight = fixtureHeight;
    }

    public String getCeilingHeight() {
        return ceilingHeight;
    }

    public void setCeilingHeight(String ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
    
}
