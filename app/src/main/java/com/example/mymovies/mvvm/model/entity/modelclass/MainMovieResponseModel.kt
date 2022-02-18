package com.example.mymovies.mvvm.model.entity.modelclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class MainMovieResponseModel(
    @SerializedName("Search")
    var movieList : List<Movie>? = null,
    @SerializedName("Response")
    val response: String? = null,
    @SerializedName("totalResults")
    val results : String? = null
)
@Parcelize
@Entity(tableName = "movie" )
data class Movie (
    @ColumnInfo(name = "Title")
    @SerializedName("Title")
    val title : String? = null,
    @ColumnInfo(name = "Year")
    @SerializedName("Year")
    val year : String? = null,
    @PrimaryKey
     @ColumnInfo(name = "imdbID")
    @SerializedName("imdbID")
    val imdbID : String,
    @SerializedName("Type")
    val type : String? = null,
    @ColumnInfo(name = "poster_path")
    @SerializedName("Poster")
    val poster : String? = null
) : CommonResponseModel{
    @ColumnInfo(name = "page")
    var page : Int = 0
}
