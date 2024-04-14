package com.app.members

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.members_domain.common.Resource
import com.app.members_domain.usecase.GetMembersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MembersViewModel @Inject constructor(
    private val getMembersUseCase: GetMembersUseCase
) : ViewModel() {

    private val _memberState = MutableStateFlow(MemberListState())
    val memberState = _memberState.asStateFlow()

    private val _memberUiEvent = MutableSharedFlow<UiEvent>()
    val memberUiEvent = _memberUiEvent.asSharedFlow()

    init {
        getMembers()
    }

    private fun getMembers() {
        viewModelScope.launch {

            getMembersUseCase.getMembers().collectLatest { item ->
                when (item) {

                    is Resource.Error -> {
                        val message = item.message

                        Timber.d("error occurred with message $message")

                        _memberState.value = memberState.value.copy(loading = false)

                        _memberUiEvent.emit(UiEvent.ToastEvent(message ?: "error"))
                    }

                    is Resource.Loading -> {
                        Timber.d("loading members .....")
                        _memberState.value = memberState.value.copy(loading = true)
                    }


                    is Resource.Success -> {
                        val memberList = item.data
                        Timber.d("members received with size : ${memberList?.size}")
                        _memberState.value = memberState.value.copy(
                            members = item.data ?: emptyList(),
                            loading = false
                        )
                    }
                }
            }
        }
    }
}

sealed class UiEvent {
    //todo: add other ui events (i.e navigation)
    data class ToastEvent(val message: String = "") : UiEvent()
}
