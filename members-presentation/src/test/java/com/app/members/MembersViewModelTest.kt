package com.app.members

import com.app.members_domain.common.Resource
import com.app.members_domain.usecase.GetMembersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MembersViewModelTest {

    private lateinit var membersViewModel : MembersViewModel

    @Mock
    lateinit var getMembersUseCase : GetMembersUseCase

    @Before
    fun setUp() {
        membersViewModel = MembersViewModel(FakeGetMemberUseCase())
    }

    @Test
    fun `members retrieved from repository`() = runTest {
        Mockito.`when`(getMembersUseCase.getMembers()).then{
            Resource.Error("","")
        }
    }
}