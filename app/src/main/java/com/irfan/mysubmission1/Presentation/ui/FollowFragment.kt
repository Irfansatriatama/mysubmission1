package com.irfan.mysubmission1.Presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.irfan.mysubmission1.Presentation.Adapter.FollowAdapter
import com.irfan.mysubmission1.Presentation.ViewModel.FollowViewModel
import com.irfan.mysubmission1.R
import com.irfan.mysubmission1.data.response.FollowResponse
import com.irfan.mysubmission1.databinding.FragmentFollowBinding


class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding
    private val followViewModel by viewModels<FollowViewModel>()

    companion object {
        const val ARG_POSITION = "section_number"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(layoutInflater)
        followViewModel.isLoading.observe(viewLifecycleOwner){ loader ->
            showLoadingProcess(loader)
        }
    }

    fun showLoadingProcess(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else {
            binding.progressBar.visibility = View.GONE
        }
    }

    fun loadAllFollow(follow: List<FollowResponse>){
        val followAdapter = FollowAdapter()
        followAdapter.submitList(follow)
        binding.rvFollow.adapter = followAdapter
    }
}