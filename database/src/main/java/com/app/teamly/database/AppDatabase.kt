package com.app.teamly.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.teamly.database.members.MemberEntity
import com.app.teamly.database.relations.MemberTeamEntity
import com.app.teamly.database.teams.TeamsEntity

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

    //todo: declare the respective abstract dao's here
}