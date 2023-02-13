package com.app.teamly.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.teamly.database.members.dao.MemberDao
import com.app.teamly.database.members.entity.MemberEntity
import com.app.teamly.database.relations.MemberTeamEntity
import com.app.teamly.database.teams.dao.TeamsDao
import com.app.teamly.database.teams.entity.TeamsEntity

@Database(
    entities = [
        MemberEntity::class,
        TeamsEntity::class,
        MemberTeamEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    //declare required dao's
    abstract fun memberDao() : MemberDao
    abstract fun teamsDao() : TeamsDao
}