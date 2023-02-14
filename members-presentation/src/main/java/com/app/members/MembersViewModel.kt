package com.app.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.members_domain.common.Resource
import com.app.members_domain.model.Member
import com.app.members_domain.usecase.GetMembersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MembersViewModel @Inject constructor(
    private val getMembersUseCase: GetMembersUseCase
) : ViewModel() {

    private val _memberState = MutableStateFlow(MemberListState())
    val memberState = _memberState.asStateFlow()

    init {
        getMembers()
    }

    private fun getMembers() {
        viewModelScope.launch {

            getMembersUseCase().collectLatest { item ->

                when (item) {
                    is Resource.Error -> {
                        val message = item.message
                        Timber.d("error occurred with message $message")

                    }

                    is Resource.Loading -> {
                        Timber.d("loading members .....")
                    }


                    is Resource.Success -> {
                        val memberList = item.data
                        Timber.d("members received with size : ${memberList?.size}")
                        _memberState.value = memberState.value.copy(
                            members = item.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }
}

sealed class UiEvent {
    data class ToastEvent(val message: String) : UiEvent()
}
