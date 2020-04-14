//package com.example.coolwinksapp.ui.adapter
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.annotation.LayoutRes
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
//import com.example.coolwinksapp.R
//import com.gaurav.newsapp.R
//import com.gaurav.newsapp.data.Article
//import com.gaurav.newsapp.ui.activity.NewsDetailActivity
//import com.gaurav.newsapp.ui.activity.NewsDetailActivity.Companion.INTENT_DATA
//import kotlinx.android.synthetic.main.adapter_news.view.*
//
//
//class RecyclerAdapter(private val mContext: Context) :
//    RecyclerView.Adapter<RecyclerAdapter.NewsHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
//        val inflatedView = parent.inflate(R.layout.adapter_news, false)
//        return NewsHolder(inflatedView)
//    }
//
//    override fun getItemCount() = newsList.size
//
//    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
//        if (newsList.size > 0) {
//
//            val article = newsList[position]
//            holder.bindPhoto(article)
//        }
//    }
//
//    fun updateList(newData: ArrayList<Article>) {
//        newsList.clear()
//        newsList.addAll(newData)
//        notifyDataSetChanged()
//
//    }
//
//   inner class NewsHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
//        private var view: View = v
//
//        init {
//
//            v.setOnClickListener(this)
//        }
//
//        override fun onClick(v: View?) {
//            val intent = Intent(mContext, NewsDetailActivity::class.java)
//            intent.putExtra(INTENT_DATA, newsList[adapterPosition])
//            mContext.startActivity(intent)
//        }
//
//
//        fun bindPhoto(newsArticle: Article) {
//            view.tvTitle.text = newsArticle.title
//            view.tvSource.text = newsArticle.source?.name
//            view.tvContent.text = newsArticle.description
//            Glide.with(mContext).applyDefaultRequestOptions(RequestOptions().error(R.mipmap.ic_launcher)).load(newsArticle.urlToImage).into(view.ivSourceImage)
//        }
//    }
//
//
//}
//
//private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
//    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
//}
//
