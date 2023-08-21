package com.example.viewpager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.viewpager.model.Favorite


@Dao
interface FavoriteDao {
    @Query("SELECT * FROM table_favorite ORDER BY id DESC")
    fun getAllFavoriteProducts(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteProduct(product: Favorite): Long

    @Delete
    fun deleteFavoriteProduct(product: Favorite)
}