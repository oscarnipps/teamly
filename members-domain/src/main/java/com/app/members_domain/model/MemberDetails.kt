package com.app.members_domain.model


data class MemberDetails(
    val imageUrl : String,
    val name : String,
    val email : String,
    val teams : List<String>
)
