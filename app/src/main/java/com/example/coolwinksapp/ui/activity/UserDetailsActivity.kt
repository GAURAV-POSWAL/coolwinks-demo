package com.example.coolwinksapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolwinksapp.R
import com.example.coolwinksapp.base.MyApplication
import com.example.coolwinksapp.model.Message
import com.example.coolwinksapp.ui.adapter.MessageAdapter
import com.example.coolwinksapp.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_user_details.*
import javax.inject.Inject

class UserDetailsActivity : AppCompatActivity() {

    companion object {
        const val MESSAGES_DATA = "MessagesData"
    }

    private lateinit var detailsViewModel: DetailsViewModel

    /**
     * Injecting via dagger
     * Profit is that we don't need to create every different viewModelFactory class with different params
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var recyclerAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        detailsViewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]
        showLoader(true)
        val usersMessagesData = intent.getParcelableArrayListExtra<Message>(MESSAGES_DATA)
        detailsViewModel.setUsersMessagesData(usersMessagesData)
        init()
        populateNewsList(detailsViewModel.getUsersMessagesData())
    }

    private fun init() {
        setUpToolBar()
        setupRecyclerView()
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolbar)

        //home navigation
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "User Messages"

        toolbar.setNavigationOnClickListener {
            //do something you want
            finish()
        }
    }

    private fun setupRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        rvUsersMessages.layoutManager = linearLayoutManager
        recyclerAdapter = MessageAdapter(this)
        rvUsersMessages.adapter = recyclerAdapter
        // for line separation between items
        val dividerItemDecoration =
            DividerItemDecoration(rvUsersMessages.context, linearLayoutManager.orientation)
        rvUsersMessages.addItemDecoration(dividerItemDecoration)
    }

    private fun populateNewsList(dataList: List<Message>?) {
        showLoader(false)
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