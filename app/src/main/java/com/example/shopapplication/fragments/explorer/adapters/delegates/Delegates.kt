package com.example.shopapplication.fragments.explorer.adapters.delegates

import android.content.res.ColorStateList
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.domain.impl.entities.ProductHot
import com.example.domain.impl.entities.ProductSeller
import com.example.domain.impl.entities.delegate.Delegate
import com.example.domain.impl.entities.delegate.data.CategoryDelegateImpl
import com.example.domain.impl.entities.delegate.data.HotDelegateImpl
import com.example.domain.impl.entities.delegate.data.SearchDelegateImpl
import com.example.domain.impl.entities.delegate.data.SellerDelegateImpl
import com.example.shopapplication.R
import com.example.shopapplication.databinding.*
import com.example.shopapplication.fragments.explorer.adapters.CategoryAdapter
import com.example.shopapplication.fragments.explorer.adapters.ItemHotAdapter
import com.example.shopapplication.fragments.explorer.adapters.SellerItemsAdapter
import com.example.shopapplication.model.categories.Category
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


object Delegates {
    fun categoryDelegate(
        onClick: (Int) -> Unit
    ) = adapterDelegateViewBinding<CategoryDelegateImpl, Delegate, LayoutCategoriesBinding>(
        { inflater, parent -> LayoutCategoriesBinding.inflate(inflater, parent, false) }
    ) {
        val categoryAdapter = CategoryAdapter(onClick = { onClick(it) })
        binding.recyclerCategories.adapter = categoryAdapter

        bind {
            categoryAdapter.items = item.categoryData
        }
    }

    fun categoryButtonsDelegate(
        onClick: (Int) -> Unit
    ) = adapterDelegateViewBinding<Category, Delegate, ExplorerRecyclerCategoryBinding>(
        { inflater, parent -> ExplorerRecyclerCategoryBinding.inflate(inflater, parent, false) }
    ) {
        binding.categoryButton.setOnClickListener {
            onClick(bindingAdapterPosition)
            bindingAdapter?.itemCount?.let { itemCount ->
                bindingAdapter?.notifyItemRangeChanged(
                    0,
                    itemCount
                )
            }
        }

        bind {
            binding.categoryText.text = item.name
            binding.categoryButton.setIconResource(item.iconId)

            if (item.isClicked) {
                binding.categoryButton.icon
                    .setTint(ContextCompat.getColor(context, R.color.buttons_background))
                binding.categoryButton.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.primaryColor))
                binding.categoryText
                    .setTextColor(ContextCompat.getColor(context, R.color.primaryColor))
            } else {
                binding.categoryButton.icon
                    .setTint(ContextCompat.getColor(context, R.color.buttons_icon_tint))
                binding.categoryButton.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.buttons_background
                        )
                    )
                binding.categoryText
                    .setTextColor(ContextCompat.getColor(context, R.color.secondaryColor))
            }
        }
    }

    val searchDelegate =
        adapterDelegateViewBinding<SearchDelegateImpl, Delegate, LayoutSearchBinding>(
            { inflater, parent -> LayoutSearchBinding.inflate(inflater, parent, false) }
        ) {
            binding.searchInput.editText?.setOnFocusChangeListener { _, focus ->
                binding.searchInput.isHintEnabled = !focus
            }

            binding.searchButtonQr.setOnClickListener {
                Toast.makeText(context, "Scanning...", Toast.LENGTH_SHORT).show()
            }
        }

    fun hotDelegate() = adapterDelegateViewBinding<HotDelegateImpl, Delegate, LayoutHotBinding>(
        { inflater, parent -> LayoutHotBinding.inflate(inflater, parent, false) }
    ) {
        val itemHotAdapter = ItemHotAdapter()

        binding.hotRecycler.adapter = itemHotAdapter

        bind {
            itemHotAdapter.items = item.hotData
        }
    }

    val itemHotDelegate =
        adapterDelegateViewBinding<ProductHot, Delegate, ExplorerRecyclerHotBinding>(
            { inflater, parent -> ExplorerRecyclerHotBinding.inflate(inflater, parent, false) }
        ) {
            bind {
                binding.hotIcon.visibility = if (item.isNew) View.VISIBLE else View.GONE
                binding.hotItemName.text = item.name
                binding.hotItemDescription.text = item.description
                binding.hotPictureItem.load(item.pictureUrl) {
                    transformations(RoundedCornersTransformation(radius = 20f))
                    placeholder(R.drawable.picture_iphone)
                }
            }
        }

    fun sellersDelegate(
        onFavoriteClick: (Int) -> Unit,
        onItemClick: (Int) -> Unit
    ) = adapterDelegateViewBinding<SellerDelegateImpl, Delegate, LayoutBestSellerBinding>(
        { inflater, parent -> LayoutBestSellerBinding.inflate(inflater, parent, false) }
    ) {
        val adapter = SellerItemsAdapter(
            onFavoriteClick = { onFavoriteClick(it) },
            onItemClick = { onItemClick(it) }
        )

        binding.sellerGrid.adapter = adapter

        bind {
            adapter.items = item.sellerData
        }
    }

    fun sellerItemsAdapter(
        onFavoriteClick: (Int) -> Unit,
        onItemClick: (Int) -> Unit
    ) = adapterDelegateViewBinding<ProductSeller, Delegate, ExplorerRecyclerSellerBinding>(
        { inflater, parent -> ExplorerRecyclerSellerBinding.inflate(inflater, parent, false) }
    ) {
        binding.sellerItemCard.setOnClickListener { onItemClick(bindingAdapterPosition) }
        binding.sellerButtonFavorite.setOnClickListener {
            onFavoriteClick(bindingAdapterPosition)
            bindingAdapter?.notifyItemChanged(
                bindingAdapterPosition
            )
        }

        bind {
            binding.sellerItemName.text = item.name
            binding.sellerItemDiscount.text =
                getString(R.string.seller_discount, item.discountPrice!!)
            binding.sellerPictureItem.load(item.pictureUrl) {
                transformations(RoundedCornersTransformation(radius = 10f))
                placeholder(R.drawable.icon_trash)
            }

            val string = SpannableString(getString(R.string.seller_price, item.price))
            string.setSpan(StrikethroughSpan(), 0, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            binding.sellerItemPrice.text = string

            if (item.isFavorite)
                binding.sellerButtonFavorite.setIconResource(R.drawable.icon_favorite_fill)
            else
                binding.sellerButtonFavorite.setIconResource(R.drawable.icon_favorite)
        }
    }
}