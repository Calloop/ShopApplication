package com.example.shopapplication.fragments.details

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.shopapplication.R
import com.example.shopapplication.databinding.FragmentDetailBinding
import com.example.shopapplication.fragments.details.DetailsViewModel.Companion.fakeProductId
import com.example.shopapplication.fragments.details.adapters.DetailsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {
    private val detailsViewModel by viewModel<DetailsViewModel>()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailsAdapter = DetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.detailButtonBack.setOnClickListener { backButtonClick() }
        binding.detailButtonCart.setOnClickListener { cartButtonClick() }
        binding.detailButtonFavorite.setOnClickListener { favoriteButtonClick() }
        binding.detailButtonAddToCart.setOnClickListener { addToCartButtonClick() }
        binding.detailRecycler.adapter = detailsAdapter

        binding.detailItemPrimaryColorPicker.setOnClickListener { primaryColorPickerClick() }
        binding.detailItemSecondaryColorPicker.setOnClickListener { secondaryColorPickerClick() }
        binding.detailItemPrimaryStorageSize.setOnClickListener { primaryStorageButtonClick() }
        binding.detailItemSecondaryStorageSize.setOnClickListener { secondaryStorageButtonClick() }

        PagerSnapHelper().attachToRecyclerView(binding.detailRecycler)
        detailsViewModel.loadingData(fakeProductId)

        observeData()
        observeError()
        observeLoading()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun secondaryStorageButtonClick() {
        binding.detailItemSecondaryStorageSize.backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(), R.color.primaryColor)
            )
        binding.detailItemSecondaryStorageSize.setTextColor(
            ContextCompat.getColor(requireContext(), R.color.text_color)
        )

        binding.detailItemPrimaryStorageSize.backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(), R.color.buttons_background)
            )
        binding.detailItemPrimaryStorageSize.setTextColor(
            ContextCompat.getColor(requireContext(), R.color.secondary_text_color)
        )
    }

    private fun primaryStorageButtonClick() {
        binding.detailItemPrimaryStorageSize.backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(), R.color.primaryColor)
            )
        binding.detailItemPrimaryStorageSize.setTextColor(
            ContextCompat.getColor(requireContext(), R.color.text_color)
        )

        binding.detailItemSecondaryStorageSize.backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(), R.color.buttons_background)
            )
        binding.detailItemSecondaryStorageSize.setTextColor(
            ContextCompat.getColor(requireContext(), R.color.secondary_text_color)
        )
    }

    private fun secondaryColorPickerClick() {
        binding.detailItemSecondaryColorPicker.setImageResource(R.drawable.icon_checkmark)
        binding.detailItemPrimaryColorPicker.setImageDrawable(null)
    }

    private fun primaryColorPickerClick() {
        binding.detailItemPrimaryColorPicker.setImageResource(R.drawable.icon_checkmark)
        binding.detailItemSecondaryColorPicker.setImageDrawable(null)
    }

    private fun favoriteButtonClick() {
        detailsViewModel.setFavorite()
    }

    private fun addToCartButtonClick() {
        Toast.makeText(context, "Successfully add to cart!", Toast.LENGTH_SHORT).show()
    }

    private fun cartButtonClick() {
        requireView().findNavController().navigate(R.id.cart_fragment)
    }

    private fun backButtonClick() {
        requireView().findNavController().navigate(R.id.explorer_fragment)
    }

    private fun observeData() {
        detailsViewModel.data.observe(viewLifecycleOwner) { detailData ->
            if (detailData != null) {
                detailsAdapter.submitList(detailData.pictureUrls)

                binding.detailTextItemName.text = detailData.name
                binding.detailItemRating.rating = detailData.rating

                binding.detailButtonFavorite.icon =
                    if (detailData.isFavorite)
                        ContextCompat.getDrawable(requireContext(), R.drawable.icon_favorite_fill)
                    else ContextCompat.getDrawable(requireContext(), R.drawable.icon_favorite)

                binding.detailItemTextCpu.text = detailData.specs.cpu
                binding.detailItemTextCamera.text = detailData.specs.camera
                binding.detailItemTextRam.text = detailData.specs.ram
                binding.detailItemTextStorage.text = detailData.specs.memory
            }
        }
    }

    private fun observeError() {
        detailsViewModel.error.observe(viewLifecycleOwner) {
            if (it != null)
                Toast.makeText(context, "$it!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeLoading() {
        detailsViewModel.loading.observe(viewLifecycleOwner) {
            if (it) Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
        }
    }
}