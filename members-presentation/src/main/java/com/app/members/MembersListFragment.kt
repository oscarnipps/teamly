package com.app.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.teamly.members_presentation.R
import com.app.teamly.members_presentation.databinding.FragmentMembersListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MembersListFragment : Fragment() {

    private val memberViewModel : MembersViewModel by viewModels()
    private lateinit var membersAdapter : MemberListAdapter
    private lateinit var binding : FragmentMembersListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_members_list,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = binding.rvMember

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                memberViewModel.memberState.collectLatest { memberItem ->
                    membersAdapter = MemberListAdapter(memberItem.members)

                    recyclerView.apply {
                         adapter = membersAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }

                }
            }
        }


    }
}