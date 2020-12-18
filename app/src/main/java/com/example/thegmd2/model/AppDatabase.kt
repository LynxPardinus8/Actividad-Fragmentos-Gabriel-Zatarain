package com.example.thegmd2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Student::class), version = 1)

abstract class AppDatabase : RoomDatabase() {
    companion object{
        var instance: AppDatabase?=null;
        fun getInstance(ctx: Context):AppDatabase
        {
            if(instance!=null)
            {
                return  instance as AppDatabase;
            }
            instance= Room.databaseBuilder(ctx,
                AppDatabase::class.java, "mydb").run { allowMainThreadQueries() }.build();

            return instance as AppDatabase;
        }
    }

    abstract fun StudentDao(): StudentDao
}