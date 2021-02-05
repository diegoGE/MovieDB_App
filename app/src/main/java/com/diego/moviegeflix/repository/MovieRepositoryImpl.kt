package com.diego.moviegeflix.repository

import com.diego.moviegeflix.core.InternetChecking
import com.diego.moviegeflix.data.local.LocalMovieDataSource
import com.diego.moviegeflix.data.model.MovieList
import com.diego.moviegeflix.data.model.toMovieEntity
import com.diego.moviegeflix.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetChecking.isNetworkConnect()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }

    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetChecking.isNetworkConnect()){
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        }else{
            dataSourceLocal.getTopRatedMovies()
        }

    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetChecking.isNetworkConnect()){
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        }else{
            dataSourceLocal.getPopularMovies()
        }

    }

}