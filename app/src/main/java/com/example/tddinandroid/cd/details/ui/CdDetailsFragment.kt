package com.example.tddinandroid.cd.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tddinandroid.databinding.DialogCdDetailsBinding

class CdDetailsFragment : DialogFragment() {

    private var _binding: DialogCdDetailsBinding? = null
    private val binding: DialogCdDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCdDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun getPurchases(): Set<Int> {
        return setOf(1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}