// AdBannerAdapter.kt
package com.example.renttoorange.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.renttoorange.model.Ad
import com.example.renttoorange.R
import com.squareup.picasso.Picasso

class AdBannerAdapter(private val ads: List<Ad>) : RecyclerView.Adapter<AdBannerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewAd: ImageView = view.findViewById(R.id.imageViewAd)
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val textViewAdAddress: TextView = view.findViewById(R.id.textViewAdAddress)

        fun bind(ad: Ad) {
            // Load ad image using Picasso
            Picasso.get()
                .load(ad.imageUrl)
                .into(imageViewAd)

            textViewTitle.text = ad.title
            // Set ad address
            textViewAdAddress.text = ad.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ad_banner_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ads[position])
    }

    override fun getItemCount() = ads.size
}
