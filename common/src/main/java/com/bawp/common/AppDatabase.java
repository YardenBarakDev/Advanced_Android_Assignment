package com.bawp.common;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {AppsTimeInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TimeDao timeDao();

}
