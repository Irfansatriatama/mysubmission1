package com.irfan.mysubmission1.Presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.mysubmission1.Presentation.Adapter.FollowAdapter
import com.irfan.mysubmission1.Presentation.ViewModel.FollowViewModel
import com.irfan.mysubmission1.data.response.FollowResponse
import com.irfan.mysubmission1.databinding.FragmentFollowBinding


class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding
    private val followViewModel by viewModels<FollowViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(layoutInflater)
        followViewModel.isLoading.observe(viewLifecycleOwner){ loader ->
            showLoading(loader)
        }
        followViewModel.followers.observe(viewLifecycleOwner){ follow ->
            if (follow != null){
                loadAllFollow(follow)
            }
        }
        followViewModel.followings.observe(viewLifecycleOwner){ follow ->
            if (follow != null){
                loadAllFollow(follow)
            }
        }

        binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = arguments?.getInt(ARG_POSITION, 0)

        if (index == 1) {
            if (followViewModel.followers.value == null) {
                followViewModel.getListFollow(
                    DetailActivity.getUsername(),
                    "followers"
                )
            }
        } else {
            if (followViewModel.followings.value == null) {
                followViewModel.getListFollow(
                    DetailActivity.getUsername(),
                    "followings"
                )
            }
        }


    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

    fun loadAllFollow(follow: List<FollowResponse>?){
        val followAdapter = FollowAdapter()
        followAdapter.submitList(follow)
        binding.rvFollow.adapter = followAdapter
    }

    companion object {
        const val ARG_POSITION = "section_number"
    }
}