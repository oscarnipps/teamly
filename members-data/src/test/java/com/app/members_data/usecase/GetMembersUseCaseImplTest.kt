package com.app.members_data.usecase

import com.app.members_domain.repo.MembersRepo
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMembersUseCaseImplTest{

    @Mock
    private lateinit var membersRepo: MembersRepo

    private lateinit var getMembersUseCase : GetMembersUseCaseImpl

    @Before
    fun setUp() {
        getMembersUseCase = GetMembersUseCaseImpl(membersRepo)
    }

    @Test
    fun `get members limit with empty member name returns limit of 5`() = runTest{
        val name = ""

        val expected = 5

        val actual =  getMembersUseCase.getMembersLimit(name)

        Truth.assertThat(actual).isEqualTo(expected)
    }
}