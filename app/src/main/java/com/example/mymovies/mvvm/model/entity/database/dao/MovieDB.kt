package com.android.myapplication.movies.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.myapplication.movies.persistence.dao.*
import com.example.mymovies.mvvm.model.entity.modelclass.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = true)
abstract class MovieDB : RoomDatabase() {

    abstract fun messageDao(): MovieAndDetailDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDB? = null

        fun getDatabase(context: Context): MovieDB? {
            if (INSTANCE == null) {
                synchronized(MovieDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MovieDB::class.java, "movies_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}