package com.example.omdb.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.omdb.screen.MovieDetail
import com.example.omdb.R
import com.example.omdb.models.MovieDataClass
import com.squareup.picasso.Picasso


class RecyclerAdapter(
    private val context: Context,
    private val dataList: ArrayList<MovieDataClass>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val banner: ImageView = view.findViewById(R.id.iv_movie_thumb)
        val title: TextView = view.findViewById(R.id.tv_movie_name)
        val release: TextView = view.findViewById(R.id.tv_movie_release_date)
        val card: View = view.findViewById(R.id.list_card)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataList[position].title.toString()
        holder.release.text = "Release: ${dataList[position].released}"
        Picasso.with(context)
            .load(dataList[position].poster.toString())
            .error(R.drawable.broken_image)
            .placeholder(R.drawable.broken_image)
            .into(holder.banner)
        holder.card.setOnClickListener {
            var intent = Intent(context, MovieDetail::class.java)
            intent.putExtra("id", dataList[position].imdbID.toString())
            context.startActivity(intent)
        }
    }


}
