package com.app.members_data.api

import com.app.members_data.models.MembersDto
import retrofit2.Response
import retrofit2.http.GET

interface MemberService {

    @GET("api/users?page=2")
    suspend fun getMembersFromApi() : MembersDto

}