package com.nzh.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.nzh.movies.R
import com.nzh.movies.remote.UpComingMovie

class UpComingMoviesAdapter(
    private var movies: List<UpComingMovie>
) : RecyclerView.Adapter<UpComingMoviesAdapter.MovieViewHolder>() {
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.upcomming_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun onClick(position: Int, model: UpComingMovie)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, movies[position] )
            }
        }
    }

    fun updateMovies(movies: List<UpComingMovie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.imgMovie)
        private val movieName = itemView.findViewById<TextView>(R.id.txtMovieName)
        private val movieOverview = itemView.findViewById<TextView>(R.id.txtMovieDesc)
        private val movieRate = itemView.findViewById<TextView>(R.id.txtRate)
        private val movieCommentCount = itemView.findViewById<TextView>(R.id.txtMovieCommentCount)

        fun bind(movie: UpComingMovie) {
            movieName.text = movie.title
            movieOverview.text = movie.overview
            movieRate.text = movie.rating.toString()
            movieCommentCount.text = movie.count.toString()

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(poster)
        }
    }
}