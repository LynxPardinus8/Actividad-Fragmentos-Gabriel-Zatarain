package com.example.thegmd2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey
    var codigo: Int = 0,
    var nombre: String? = null,
    var apellido: String? = null,
)