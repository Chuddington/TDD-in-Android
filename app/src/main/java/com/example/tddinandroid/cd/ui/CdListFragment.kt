package com.example.tddinandroid.cd.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tddinandroid.R
import com.example.tddinandroid.databinding.FragmentCdListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cdList.observeForever { cds ->
            activity?.let { fragActivity ->
                binding.cdRecycler.adapter =
                    CdViewAdapter(cds, findNavController())
                binding.cdRecycler.refreshDrawableState()
            }
        }

        viewModel.loadCds()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getCount(): Int = viewModel.getCount()
}