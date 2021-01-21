package com.bawp.common;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TimeDao {
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(AppsTimeInfo... appsTimeInfo);

    @Delete
    void delete(AppsTimeInfo appsTimeInfo);

    @Query("SELECT * FROM time_table")
    List<AppsTimeInfo> getAll();

    @Query("SELECT MAX(total_time) FROM time_table")
    long getTotalTime();

    @Update
    public void updateTime(AppsTimeInfo appsTimeInfo);
}
