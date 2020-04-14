package com.example.coolwinksapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CoolMainActivity : AppCompatActivity() {
    lateinit var coolViewModel: CoolViewModel

    /**
     * Injecting via dagger
     * Profit is that we don't need to create every different viewModelFactory class with different params
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coolViewModel =
            ViewModelProvider(this, viewModelFactory)[CoolViewModel::class.java]
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

    private fun setupRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        rvUsersMessages.layoutManager = linearLayoutManager
        recyclerAdapter = RecyclerAdapter(this)
        rvUsersMessages.adapter = recyclerAdapter
        // for line separation between items
        val dividerItemDecoration =
            DividerItemDecoration(rvUsersMessages.context, linearLayoutManager.orientation)
        rvUsersMessages.addItemDecoration(dividerItemDecoration)
    }


    private fun getCoolApiData() {
        if (Utils.isOnline(this))
            coolViewModel.getUsersApiData().observe(
                this, Observer<List<CoolViewDataResponse>> {
                    showLoader(false)
                    Toast.makeText(this, "List size is ${it.size}", Toast.LENGTH_SHORT).show()
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
                rvUsersMessages.visibility = View.GONE
                tvNoData.visibility = View.VISIBLE
            }
        }
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
