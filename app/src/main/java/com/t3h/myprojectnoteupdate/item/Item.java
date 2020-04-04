package com.t3h.myprojectnoteupdate.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.t3h.myprojectnoteupdate.utils.TimestampConverter;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Item implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "color")
    private String draw;
    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    private Date createdAt;
    @ColumnInfo(name = "hoursOfDay")
    private int hour;
    @ColumnInfo(name = "dayOfMonth")
    private int day;
    @ColumnInfo(name = "monthOfYear")
    private int month;
    @ColumnInfo(name = "minus")
    private int minus;
    @ColumnInfo(name = "year")
    private int year;
    @ColumnInfo(name = "modified_at")
    @TypeConverters({TimestampConverter.class})
    private Date modifiedAt;
//    @ColumnInfo(name = "time")
//    private String time;
//    @ColumnInfo(name = "date")
//    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    //    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
}
