package com.nzh.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.nzh.movies.databinding.ActivityMovieDetailBinding
import com.nzh.movies.remote.UpComingMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var movie: UpComingMovie? = null


        if (intent.hasExtra("details")) {

            movie =
                intent.getSerializableExtra("details") as UpComingMovie
        }

        if (movie != null) {
            binding.txtMoviesTitle.text = movie.title
            binding.txtRate.text = movie.rating.toString()
            binding.txtVote.text = movie.count.toString()
            binding.txtReleaseDate.text = movie.releaseDate
            binding.txtDesc.text = movie.overview
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(binding.imgMovie)
        }


    }
}