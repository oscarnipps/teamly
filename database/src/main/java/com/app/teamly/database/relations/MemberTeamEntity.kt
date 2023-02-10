package com.app.teamly.database.relations

import androidx.room.Entity

@Entity(
    primaryKeys = ["memberId","teamId"]
)
data class MemberTeamEntity(
    val memberId: String,
    val teamId: String
)