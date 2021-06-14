package com.example.tddinandroid.cd.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tddinandroid.databinding.FragmentCdListBinding

interface Counter {
    fun getCount(): Int
}

class CdListFragment : Fragment(), Counter {

    private var _binding: FragmentCdListBinding? = null
    private val binding: FragmentCdListBinding get() = _binding!!
    private val viewModel: CdListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCdListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getCount(): Int = viewModel.getCount()
}