package com.example.thegmd2.model

import androidx.room.*

@Dao
interface StudentDao {
    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(students: Student)

    @Delete
    fun delete(students: Student)
}