package com.example.mymovies.mvvm.model.entity.modelclass

import com.google.gson.annotations.SerializedName

data class MovieDetailResponseModel(
    @SerializedName("Title")
    val title : String? = null,
    @SerializedName("Year")
    val year : String? = null,
    @SerializedName("Rated")
    val rated : String? = null,
    @SerializedName("Released")
    val released : String? = null,
    @SerializedName("Runtime")
    val runtime : String? = null,
    @SerializedName("Genre")
    val genre : String? = null,
    @SerializedName("Director")
    val director : String? = null,
    @SerializedName("Writer")
    val writer : String? = null,
    @SerializedName("Actors")
    val actors : String? = null,
    @SerializedName("Plot")
    val plot : String? = null,
    @SerializedName("Language")
    val language : String? = null,
    @SerializedName("Country")
    val country : String? = null,
    @SerializedName("Awards")
    val awards : String? = null,
    @SerializedName("Poster")
    val poster : String? = null,
    @SerializedName("Metascore")
    val metascore : String? = null,
    @SerializedName("imdbRating")
    val imdbRating : String? = null,
    @SerializedName("imdbVotes")
    val imdbVotes : String? = null,
    @SerializedName("imdbID")
    val imdbID : String? = null,
    @SerializedName("Type")
    val type : String? = null,
    @SerializedName("DVD")
    val dvd : String? = null,
    @SerializedName("BoxOffice")
    val boxOffice : String?,
    @SerializedName("Production")
    val production : String? = null,
    @SerializedName("Website")
    val website : String? = null,
    @SerializedName("Response")
    val response : String? = null,
    @SerializedName("Ratings")
    val ratings: List<Ratings>? = null
)

data class Ratings(
    @SerializedName("Source")
    val source : String? = null,
    @SerializedName("Value")
    val value : String? = null
)