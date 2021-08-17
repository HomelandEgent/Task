package com.shazawdidi.ui

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import com.shazawdidi.adapter.NewsAdapter
import com.shazawdidi.newsViewModel.NewsViewModel
import com.shazawdidi.pojoClasses.Articles
import java.util.*


class NewsList : AppCompatActivity() {

    private val TAG = NewsList::class.qualifiedName // current Tag activity
    private var mContext: Context = this // current Context
    private lateinit var viewModelObject: NewsViewModel // ViewMode var
    private lateinit var loadingProgressBar: ProgressBar // Progress for loading


    private fun iniToolbar() {
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        toolbar.setTitle(resources.getString(R.string.newList))
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.getNavigationIcon()!!.setColorFilter(
            resources.getColor(R.color.colorPrimary),
            PorterDuff.Mode.SRC_ATOP
        )

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        // ini toolbar with header
        iniToolbar()

        // loading Progress
         loadingProgressBar = findViewById(R.id.loadingProgress)

        // ini View model class
        iniViewModeClass()

    }

    private fun iniViewModeClass() {

        viewModelObject = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModelObject.getNewList().observe(this, Observer {
            Log.e(TAG, " " + it);

            if (it == null) {
                showSnake()
            } else {
                iniRecyclerView(it.articles)
            }

        })



        viewModelObject.getIsLoading().observe(this, Observer {
            Log.e(TAG, "Is Data loading => " + it)
            if (it == true) {
                loadingProgressBar.visibility = View.VISIBLE
            } else {
                loadingProgressBar.visibility = View.GONE
            }
        })

    }

    // u can use binding rather than findVewById
    // ini RecyclerView
    private fun iniRecyclerView(articles: ArrayList<Articles>) {
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NewsAdapter(this, articles)
    }

    // Call snake when Error ocuur
    private fun showSnake() {

        val snackbar = Snackbar.make(
            findViewById(R.id.root),
            resources.getString(R.string.noConnection),
            Snackbar.LENGTH_INDEFINITE
        )
        val layout = snackbar.view as SnackbarLayout
        val textView = layout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.visibility = View.INVISIBLE
        val snackView: View =
            (mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.v_custome_snake_bar,
                null
            )
        val snackbar_action: TextView = snackView.findViewById(R.id.snackbar_action)
        snackbar_action.setOnClickListener {
            snackbar.dismiss()

            iniViewModeClass()
        }

        val textViewTop: TextView = snackView.findViewById(R.id.text)
        textViewTop.text = resources.getString(R.string.noConnection)
        textViewTop.setTextColor(Color.WHITE)
        layout.setPadding(0, 0, 0, 0)
        layout.addView(snackView, 0)
        snackbar.show()

    }


}