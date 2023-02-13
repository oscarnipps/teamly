package com.app.teamly.database.teams.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="teamId")
    val id : String,
    val name : String
)