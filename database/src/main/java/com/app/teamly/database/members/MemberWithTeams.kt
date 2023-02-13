package com.app.teamly.database.members

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.app.teamly.database.members.entity.MemberEntity
import com.app.teamly.database.relations.MemberTeamEntity
import com.app.teamly.database.teams.entity.TeamsEntity

data class MemberWithTeams(
    @Embedded
    val member: MemberEntity,

    @Relation(
        parentColumn = "memberId",
        entityColumn = "teamId",
        associateBy = Junction(MemberTeamEntity::class)
    )
    val teams: List<TeamsEntity>
)