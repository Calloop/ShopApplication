package com.example.shopapplication.model.categories

import com.example.shopapplication.R

fun getCategories(): List<Category> {
    return listOf(
        Category(id = 1, name = "Phones", iconId = R.drawable.icon_smartphones, isClicked = true),
        Category(id = 2, name = "Computer", iconId = R.drawable.icon_computer, isClicked = false),
        Category(id = 3, name = "Health", iconId = R.drawable.icon_health, isClicked = false),
        Category(id = 4, name = "Books", iconId = R.drawable.icon_books, isClicked = false),
        Category(id = 5, name = "Tools", iconId = R.drawable.icon_tools, isClicked = false)
    )
}