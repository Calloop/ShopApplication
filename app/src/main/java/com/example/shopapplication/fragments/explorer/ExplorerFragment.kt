package com.example.shopapplication.fragments.explorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopapplication.R
import com.example.shopapplication.databinding.FragmentExplorerBinding
import com.example.shopapplication.fragments.explorer.adapters.ExplorerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExplorerFragment : Fragment() {
    private val explorerViewModel by viewModel<ExplorerViewModel>()

    private var _binding: FragmentExplorerBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        ExplorerAdapter(
            onCategoryClick = { explorerViewModel.changeCategory(it) },
            onSellerFavoriteClick = { explorerViewModel.setFavorite(it) },
            onSellerItemClick = { onSellerItemClick() }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplorerBinding.inflate(inflater, container, false)

        binding.explorerButtonFilter.setOnClickListener {
            onExplorerButtonFilterClick()
        }
        binding.explorerTitleCity.setOnClickListener { cityPickerButtonClick() }
        binding.explorerRecycler.adapter = adapter
        binding.explorerRecycler.itemAnimator = null

        explorerViewModel.loadingData()

        observeData()
        observeError()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onExplorerButtonFilterClick() {
        findNavController().navigate(R.id.filter_dialog)
    }

    private fun onSellerItemClick() {
        findNavController().navigate(R.id.details_fragment)
    }


    private fun cityPickerButtonClick() {
        Toast.makeText(context, "Pick city!", Toast.LENGTH_SHORT).show()
    }

    private fun observeData() {
        explorerViewModel.data.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.items = it
            }
        }
    }

    private fun observeError() {
        explorerViewModel.error.observe(viewLifecycleOwner) {
            if (it != null)
                Toast.makeText(context, "$it!", Toast.LENGTH_SHORT).show()
        }
    }
}