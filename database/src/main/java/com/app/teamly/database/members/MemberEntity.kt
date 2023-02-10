package com.app.teamly.database.members

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemberEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="memberId")
    val id : String,
    val name : String,
    val email : String,
    val imageUrl : String
)