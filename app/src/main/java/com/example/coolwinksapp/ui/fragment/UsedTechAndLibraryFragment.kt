package com.example.coolwinksapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolwinksapp.R
import com.example.coolwinksapp.ui.activity.CoolMainActivity
import com.example.coolwinksapp.ui.adapter.TechnologyAdapter
import kotlinx.android.synthetic.main.fragment_technology.*

class UsedTechAndLibraryFragment : Fragment() {
    private lateinit var mContext: Context
    lateinit var technologyAdapter: TechnologyAdapter

    companion object {
        private const val FRAG_TAG = "DATA_TAG"
        fun getInstance(tag: String): Fragment {
            val fragment = UsedTechAndLibraryFragment()
            val bundle = Bundle()
            bundle.putString(FRAG_TAG, tag)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_technology, container, false)
    }


    override fun onAttach(context: Context) {
        mContext = if (context is CoolMainActivity) {
            context
        } else {
            activity!!
        }
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val techList = resources.getStringArray(R.array.technologyList)

        val linearLayoutManager = LinearLayoutManager(mContext)

        rvTechnology.layoutManager = linearLayoutManager
        technologyAdapter = TechnologyAdapter(mContext, techList)
        rvTechnology.adapter = technologyAdapter
    }
}