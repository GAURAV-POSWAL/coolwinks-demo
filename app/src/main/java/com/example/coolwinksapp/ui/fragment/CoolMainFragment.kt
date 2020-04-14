package com.example.coolwinksapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolwinksapp.R
import com.example.coolwinksapp.base.MyApplication
import com.example.coolwinksapp.model.CoolViewDataResponse
import com.example.coolwinksapp.ui.adapter.RecyclerAdapter
import com.example.coolwinksapp.utils.Utils
import com.example.coolwinksapp.viewmodel.CoolViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class CoolMainFragment : Fragment() {
    lateinit var coolViewModel: CoolViewModel

    /**
     * Injecting via dagger
     * Profit is that we don't need to create every different viewModelFactory class with different params
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var recyclerAdapter: RecyclerAdapter


    companion object {
        private const val FRAG_TAG = "DATA_TAG"
        fun getInstance(tag: String): Fragment {
            val fragment = CoolMainFragment()
            val bundle = Bundle()
            bundle.putString(FRAG_TAG, tag)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coolViewModel = ViewModelProvider(this, viewModelFactory)[CoolViewModel::class.java]

        setupRecyclerView()

        // Handle api call on configuration changes.
        with(coolViewModel.getUsersData()) {
            if (this.isNullOrEmpty()) {
                showLoader(true)
                getCoolApiData()
            } else {
                coolViewModel.setUsersData(this)
                populateNewsList(this)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (context?.applicationContext as MyApplication).appComponent.inject(this)
        //tag = arguments?.get(FRAG_TAG) as String

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    private fun setupRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(context)
        rvUsersMessages.layoutManager = linearLayoutManager
        recyclerAdapter = RecyclerAdapter(context!!)
        rvUsersMessages.adapter = recyclerAdapter
        // for line separation between items
        val dividerItemDecoration =
            DividerItemDecoration(rvUsersMessages.context, linearLayoutManager.orientation)
        rvUsersMessages.addItemDecoration(dividerItemDecoration)
    }


    private fun getCoolApiData() {
        if (Utils.isOnline(context!!))
            coolViewModel.getUsersApiData().observe(
                this, Observer<List<CoolViewDataResponse>> {
                    showLoader(false)
                    if (it != null) {
                        showUserData(it)
                    }
//                    else showErrorScreen(true)
                })
//        else showErrorScreen(true, offline = true)

    }

    private fun showUserData(it: List<CoolViewDataResponse>?) {
        coolViewModel.setUsersData(it)
        populateNewsList(it)
    }

    private fun populateNewsList(dataList: List<CoolViewDataResponse>?) {
        dataList?.let {
            if (it.isNotEmpty()) {
                rvUsersMessages.visibility = View.VISIBLE
                tvNoData.visibility = View.GONE
                recyclerAdapter.updateList(it)
            } else {
                showErrorMessage()
            }
        }
    }

    private fun showErrorMessage() {
        rvUsersMessages.visibility = View.GONE
        tvNoData.visibility = View.VISIBLE
    }

    /**
     * this start animation when we show the loader
     * clear the animation when we don't show the loader
     */
    private fun showLoader(flag: Boolean) {
        if (flag) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

}