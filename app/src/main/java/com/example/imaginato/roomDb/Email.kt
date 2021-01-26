package com.example.imaginato.roomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Email(

        @PrimaryKey
        val userId: String,
        val email: String

)
