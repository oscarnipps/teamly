package com.app.members

import com.app.members_domain.model.Member

data class MemberListState(
    var members : List<Member> = emptyList(),
    var loading : Boolean = false
)
