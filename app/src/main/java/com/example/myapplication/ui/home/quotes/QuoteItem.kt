package com.example.myapplication.ui.home.quotes

import com.example.myapplication.R
import com.example.myapplication.data.db.entites.Quotes
import com.example.myapplication.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
   private val quote: Quotes
): BindableItem<ItemQuoteBinding>() {
    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)

    }

    override fun getLayout() = R.layout.item_quote

}