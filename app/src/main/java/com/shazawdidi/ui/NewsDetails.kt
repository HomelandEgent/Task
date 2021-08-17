package com.shazawdidi.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class NewsDetails : AppCompatActivity() {

    private lateinit var urlToImage: String
    private lateinit var title: String
    private lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_news)
        setSupportActionBar(findViewById(R.id.toolbar))


        /*
        intent.putExtra("urlToImage",ItemsViewModel.urlToImage)
        intent.putExtra("title",ItemsViewModel.title)
        intent.putExtra("description",ItemsViewModel.description)
        */
        iniIntentWithData()

    }


    // ini and receive data from NewsList activity
    private fun iniIntentWithData() {
        urlToImage = intent.getStringExtra("urlToImage").toString()
        title = intent.getStringExtra("title").toString()
        description = intent.getStringExtra("description").toString()

        // ini TextView
        var imgUrl: ImageView = findViewById(R.id.imgUrl)
        var titleTv:TextView = findViewById(R.id.title)
        var descriptionTv:TextView = findViewById(R.id.description)


        // set the data into textView
        titleTv.text = title
        descriptionTv.text = description

        // show and load image
        Glide.with(this)
            .load(urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            }).into(imgUrl)





    }
}