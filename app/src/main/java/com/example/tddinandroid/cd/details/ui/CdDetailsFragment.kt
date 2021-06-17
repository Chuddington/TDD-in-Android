package com.example.tddinandroid.cd.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tddinandroid.R
import com.example.tddinandroid.databinding.DialogCdDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CdDetailsFragment : DialogFragment() {

    private var _binding: DialogCdDetailsBinding? = null
    private val binding: DialogCdDetailsBinding get() = _binding!!

    private val viewModel: CdDetailsViewModel by viewModels()
    private val navigationArgs: CdDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCdDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.purchasedCds.observe(this) { purchasedCds ->
            if (purchasedCds.contains(navigationArgs.cdId)) {
                binding.cdDetailsPurchaseButton.text = getString(R.string.already_purchased_cd)
                binding.cdDetailsPurchaseButton.setOnClickListener { }
            } else {
                binding.cdDetailsPurchaseButton.text = getString(R.string.purchase_cd)
                binding.cdDetailsPurchaseButton.setOnClickListener {
                    viewModel.purchaseCd(navigationArgs.cdId)
                    dismiss()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}