package com.example.shopapplication.fragments.explorer.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.shopapplication.R
import com.example.shopapplication.databinding.DialogFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterDialog : BottomSheetDialogFragment() {

    private var _binding: DialogFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFilterBinding.inflate(inflater, container, false)

        binding.filterButtonPositive.setOnClickListener { acceptButtonClick() }
        binding.filterButtonNegative.setOnClickListener { cancelButtonClick() }

        setFilters()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFilters() {
        val brands = listOf("All", "Samsung", "Iphone", "Xiaomi")
        val price = listOf(
            "All",
            "$0 - $500",
            "$500 - $1000",
            "$1000 - $1500",
            "$1500 - $2000",
            "$1500 - $2500",
            "$2500 - $3000",
            "$3000 - $3500",
            "$3500 - $4000",
            "$4000 - $4500",
            "$4500 - $5000",
            "$5000 - $10000",
        )

        val brandsAdapter = ArrayAdapter(requireContext(), R.layout.explorer_menu_item, brands)
        val priceAdapter = ArrayAdapter(requireContext(), R.layout.explorer_menu_item, price)

        (binding.filterInputBrand.editText as? AutoCompleteTextView)?.setAdapter(brandsAdapter)
        (binding.filterInputPrice.editText as? AutoCompleteTextView)?.setAdapter(priceAdapter)
    }

    private fun cancelButtonClick() {
        dismiss()
    }

    private fun acceptButtonClick() {
        dismiss()
    }
}