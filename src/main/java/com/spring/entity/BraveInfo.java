package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BraveInfo {

    @Id
    @Column(nullable = false, unique = true)
    private String braveId;
    private double resourceANum;
    private double resourceBNum;
    private double damage;
    private int grade;
    private int histroyLevel;
    private String histroyLevelTime;

    public void setBraveId(String braveId) {
        this.braveId = braveId;
    }

    public void setResourceANum(double resourceANum) {
        this.resourceANum = resourceANum;
    }

    public void setResourceBNum(double resourceBNum) {
        this.resourceBNum = resourceBNum;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setHistroyLevel(int histroyLevel) {
        this.histroyLevel = histroyLevel;
    }

    public void setHistroyLevelTime(String histroyLevelTime) {
        this.histroyLevelTime = histroyLevelTime;
    }

    public String getBraveId() {
        return braveId;
    }

    public double getResourceANum() {
        return resourceANum;
    }

    public double getResourceBNum() {
        return resourceBNum;
    }

    public double getDamage() {
        return damage;
    }

    public int getGrade() {
        return grade;
    }

    public int getHistroyLevel() {
        return histroyLevel;
    }

    public String getHistroyLevelTime() {
        return histroyLevelTime;
    }
}
