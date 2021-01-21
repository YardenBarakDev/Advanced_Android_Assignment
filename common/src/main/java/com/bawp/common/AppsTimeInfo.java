package com.bawp.common;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "time_table")
public class AppsTimeInfo {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "total_time")
    public long totalTime;
    @ColumnInfo(name = "entry_time")
    public String entryTime;
    @ColumnInfo(name = "exit_time")
    public String exitTime;


    public AppsTimeInfo() {

    }

    public AppsTimeInfo(long totalTime, String entryTime, String exitTime) {
        this.totalTime = totalTime;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public int getId() {
        return id;
    }

    public AppsTimeInfo setId(int id) {
        this.id = id;
        return this;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public AppsTimeInfo setTotalTime(long totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public AppsTimeInfo setEntryTime(String entryTime) {
        this.entryTime = entryTime;
        return this;
    }

    public String getExitTime() {
        return exitTime;
    }

    public AppsTimeInfo setExitTime(String exitTime) {
        this.exitTime = exitTime;
        return this;
    }
/**
     * long start = System.currentTimeMillis();
     *
     * Calendar rightNow = Calendar.getInstance();
     * int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY); // return the hour in 24 hrs format (ranging from 0-23)
     * int currentHourIn12Format = rightNow.get(Calendar.HOUR); // return the hour in 12 hrs format (ranging from 0-11)
     */
}
