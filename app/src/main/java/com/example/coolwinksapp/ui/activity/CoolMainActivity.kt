package com.example.coolwinksapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolwinksapp.R
import com.example.coolwinksapp.base.MyApplication
import com.example.coolwinksapp.model.CoolApiViewDataResponse
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
//    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coolViewModel =
            ViewModelProvider(this, viewModelFactory)[CoolViewModel::class.java]
        setupRecyclerView()

        showLoader(true)
        getCoolApiData()
    }

    private fun setupRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        rvUsersMessages.layoutManager = linearLayoutManager
//        recyclerAdapter = RecyclerAdapter(this)
//        rvUsersMessages.adapter = recyclerAdapter
        // for line separation between items
        val dividerItemDecoration =
            DividerItemDecoration(rvUsersMessages.context, linearLayoutManager.orientation)
        rvUsersMessages.addItemDecoration(dividerItemDecoration)
    }


    private fun getCoolApiData() {
        if (Utils.isOnline(this))
            coolViewModel.getUsersData().observe(
                this, Observer<CoolApiViewDataResponse> {
                    showLoader(false)
                    if (it != null) {
                        coolViewModel.setUsersData(it)
//                        populateNewsList(it.articles)
                    }
//                    else showErrorScreen(true)
                })
//        else showErrorScreen(true, offline = true)

    }

//    private fun populateNewsList(newData: ArrayList<Article>) {
//        rvUsersMessages.visibility = View.VISIBLE
//        recyclerAdapter.updateList(newData)
//    }

    /**
     * this start animation when we show the loader
     * clear the animation when we don't show the loader
     */
    private fun showLoader(flag: Boolean) {
        if (flag) {

        } else {

        }
    }
}
