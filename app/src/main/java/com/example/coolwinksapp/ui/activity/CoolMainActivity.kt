package com.example.coolwinksapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coolwinksapp.R
import com.example.coolwinksapp.ui.adapter.ViewPagerAdapter
import com.example.coolwinksapp.ui.fragment.CoolMainFragment
import com.example.coolwinksapp.ui.fragment.UsedTechAndLibraryFragment
import kotlinx.android.synthetic.main.activity_main.*

class CoolMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager()
        viewPagerMain.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPagerMain)
    }

    /**
     * set viewpager adapter here
     */
    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(
            CoolMainFragment.getInstance("TAB_1"),
            getString(R.string.tab_1)
        )
        adapter.addFragment(
            // Under Development
            Fragment(),
            getString(R.string.tab_2)
        )
        adapter.addFragment(
            UsedTechAndLibraryFragment.getInstance("TAB_3"),
            getString(R.string.tab_3)
        )

        viewPagerMain.adapter = adapter
    }

}
