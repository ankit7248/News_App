package com.example.newsapp
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.UTIL.Resource
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.uiViewModels.NewsViewModel



class BreakingNews : Fragment (R.layout.fragments_breaking_news){

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsRecyclerView: RecyclerView
    lateinit var paginationProgressBar : ProgressBar
    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        setupRecyclerView()


//        viewLifeCycleOwner is LifecycleOwner that represents the Fragment's View lifecycle.

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            // Observer always observe the breaking news fragment (viewLifecycleOwner) .
            // we can do  onCreate, on Resume , onPause in lifecycle Observer to observe the activity Owner
            when( response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG,"An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                else -> {}
            }
        })
        // we can use the breaking news in mainActivity
    }
    private fun hideProgressBar()
    {
     paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar()
    {
        paginationProgressBar.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragments_breaking_news,container,false)
        newsRecyclerView = v.findViewById(R.id.RecyclerView1)
        return v
    }
    private fun setupRecyclerView() {

        newsAdapter = NewsAdapter()
        newsRecyclerView.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}
