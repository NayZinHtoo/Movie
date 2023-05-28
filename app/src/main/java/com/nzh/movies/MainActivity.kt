package com.nzh.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nzh.movies.adapter.RecommendedMoviesAdapter
import com.nzh.movies.adapter.UpComingMoviesAdapter
import com.nzh.movies.databinding.ActivityMainBinding
import com.nzh.movies.remote.UpComingMovie
import com.nzh.movies.viewmodel.RecommendMoviesViewModel
import com.nzh.movies.viewmodel.UpCommingMoviesModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recommendedMovies: RecyclerView
    private lateinit var recommendedMoviesAdapter: RecommendedMoviesAdapter

    private lateinit var upComingMovies: RecyclerView
    private lateinit var upComingMoviesAdapter: UpComingMoviesAdapter

    private lateinit var mRecommendedMoviesViewModel: RecommendMoviesViewModel

    private lateinit var mViewModel: UpCommingMoviesModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mRecommendedMoviesViewModel=ViewModelProvider(this).get(RecommendMoviesViewModel::class.java)
        mViewModel=ViewModelProvider(this).get(UpCommingMoviesModel::class.java)

        //Recommended
        recommendedMovies = findViewById(R.id.recommended_movies)
        recommendedMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recommendedMoviesAdapter = RecommendedMoviesAdapter(listOf())
        recommendedMovies.adapter = recommendedMoviesAdapter

        //UpComing
        upComingMovies = findViewById(R.id.upcomming_movies)
        upComingMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        upComingMoviesAdapter = UpComingMoviesAdapter(listOf())
        upComingMovies.adapter = upComingMoviesAdapter
        upComingMoviesAdapter.setOnClickListener(object :
            UpComingMoviesAdapter.OnClickListener {
            override fun onClick(position: Int, model: UpComingMovie) {
                val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                intent.putExtra("details", model)
                startActivity(intent)
            }
        })

        mRecommendedMoviesViewModel.getRecommendedMovies(1)
        mViewModel.getUpComingMovies(1)
        observe()
    }

    fun observe(){
        mRecommendedMoviesViewModel.mResponseRecommendedMoviesLD.observe(this){
            if(it.movies!=null){
                recommendedMoviesAdapter.updateMovies(it.movies)
            }
        }
        mViewModel.mResponseUpComingMoviesLD.observe(this){
            if(it.movies!=null){
                upComingMoviesAdapter.updateMovies(it.movies)
            }
        }
        mViewModel.mErrorLD.observe(this){
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}