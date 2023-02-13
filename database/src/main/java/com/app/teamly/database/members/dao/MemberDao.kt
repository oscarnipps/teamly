package com.app.teamly.database.members.dao

import androidx.room.*
import com.app.teamly.database.members.MemberWithTeams
import com.app.teamly.database.members.entity.MemberEntity

@Dao
interface MemberDao {

    @Transaction
    @Query("SELECT * FROM MemberEntity")
    suspend fun getMembersWithTeams(): List<MemberWithTeams>

    @Query("SELECT COUNT(*) FROM MemberEntity WHERE memberId = :memberId")
    suspend fun getMember(memberId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member : MemberEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMember(member : MemberEntity)


}