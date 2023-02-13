package com.app.members_data.models


import com.google.gson.annotations.SerializedName

data class MembersDto(
    @SerializedName("data")
    val memberDataDto: List<MemberDataDto>
)