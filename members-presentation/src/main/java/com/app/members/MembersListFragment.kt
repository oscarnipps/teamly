package com.app.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.members_domain.model.Member
import com.app.teamly.members_presentation.R
import com.app.teamly.members_presentation.databinding.FragmentMembersListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MembersListFragment : Fragment(), MemberListAdapter.MemberItemListener {

    private val memberViewModel: MembersViewModel by viewModels()
    private lateinit var membersAdapter: MemberListAdapter
    private lateinit var binding: FragmentMembersListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_members_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        membersAdapter = MemberListAdapter(this)

        binding.memberListAdapter = membersAdapter

        collectUiState()

        collectUiEvent()
    }

    private fun collectUiEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                memberViewModel.memberUiEvent.collect { uiEvent ->
                    when (uiEvent) {
                        is UiEvent.ToastEvent -> {
                            Toast.makeText(requireContext(), uiEvent.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                }
            }
        }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                memberViewModel.memberState.collectLatest { memberItem ->
                    binding.isLoading = memberItem.loading
                    membersAdapter.submitList(memberItem.members)
                }
            }
        }
    }


    override fun onMemberItemClicked(currentItem: Member) {
        Toast.makeText(requireContext(), currentItem.name, Toast.LENGTH_SHORT).show()
    }
}