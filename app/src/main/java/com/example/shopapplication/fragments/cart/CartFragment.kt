package com.example.shopapplication.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shopapplication.R
import com.example.shopapplication.MainActivity
import com.example.shopapplication.databinding.FragmentCartBinding
import com.example.shopapplication.fragments.cart.adapters.PurchaseAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.shopapplication.fragments.cart.CartViewModel.Companion.fakeUserId

class CartFragment: Fragment() {
    private val cartViewModel by viewModel<CartViewModel>()

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val purchaseAdapter = PurchaseAdapter(
        onCounterPlusClick = { cartViewModel.productCounterPlus(position = it) },
        onDeleteClick = { cartViewModel.deleteProduct(position = it) },
        onCounterMinusClick = { cartViewModel.productCountMinus(position = it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        cartViewModel.loadingData(fakeUserId)
        binding.cartButtonBack.setOnClickListener { backButtonClick() }
        binding.cartButtonLocation.setOnClickListener { locationButtonClick() }
        binding.cartButtonCheckout.setOnClickListener { checkoutButtonClick() }
        binding.cartRecyclerPurchase.adapter = purchaseAdapter

        observeData()
        observeError()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkoutButtonClick() {
        Toast.makeText(context, getString(R.string.checkout), Toast.LENGTH_SHORT).show()
    }

    private fun locationButtonClick() {
        Toast.makeText(context, getString(R.string.location), Toast.LENGTH_SHORT).show()
    }

    private fun backButtonClick() {
        requireView().findNavController().navigate(R.id.explorer_fragment)
    }

    private fun observeData() {
        cartViewModel.data.observe(viewLifecycleOwner) { cartData ->
            if (cartData != null) {
                purchaseAdapter.submitList(cartData.products)
                binding.cartTextDeliveryPrice.text = cartData.deliveryCost
                binding.cartTextTotalPrice.text = buildString {
                    append("$")
                    append(cartData.totalCost)
                    append(" us")
                }

                var productCount = 0
                cartData.products.forEach { productCount += it.count }
                (requireActivity() as MainActivity).changeCartProducts(productCount)
            }
        }
    }

    private fun observeError() {
        cartViewModel.error.observe(viewLifecycleOwner) {
            if (it != null)
                Toast.makeText(context, "$it!", Toast.LENGTH_SHORT).show()
        }
    }
}