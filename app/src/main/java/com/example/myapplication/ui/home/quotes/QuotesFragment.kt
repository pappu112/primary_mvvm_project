package com.example.myapplication.ui.home.quotes

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.util.Coroutines
import com.example.myapplication.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    private lateinit var viewModel: QuotesViewModel

    private val factory: QuotesFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(QuotesViewModel::class.java)

        buildUI()

    }

    private fun buildUI() = Coroutines.main{
        viewModel.quotes.await().observe(this, Observer {


        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>){
        val adapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)

        }
    }

}