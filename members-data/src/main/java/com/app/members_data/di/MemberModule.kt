package com.app.members_data.di

import com.app.members_data.api.MemberService
import com.app.members_data.repo.MembersRepoImpl
import com.app.members_domain.repo.MembersRepo
import com.app.members_data.usecase.GetMembersUseCaseImpl
import com.app.members_domain.usecase.GetMembersUseCase
import com.app.teamly.database.AppDatabase
import com.app.teamly.database.members.dao.MemberDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MemberModule {

    @Provides
    @Singleton
    fun provideMemberDao(appDatabase: AppDatabase): MemberDao {
        return appDatabase.memberDao()
    }

    @Provides
    @Singleton
    fun providesMemberRepo (membersRepoImpl: MembersRepoImpl) : MembersRepo {
        return membersRepoImpl
    }

    @Provides
    @Singleton
    fun providesMemberService(retrofit : Retrofit): MemberService {
        return retrofit.create(MemberService::class.java)
    }


    @Provides
    @Singleton
    fun providesMemberUseCase (membersRepo: MembersRepo) : GetMembersUseCase {
        return GetMembersUseCaseImpl(membersRepo)
    }

}