package com.apps.foodyapps.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.apps.foodyapps.data.database.entities.FavoriteEntity
import com.apps.foodyapps.data.database.entities.FoodJokeEntity
import com.apps.foodyapps.data.database.entities.RecipesEntity

@Database(
    entities = [RecipesEntity::class, FavoriteEntity::class, FoodJokeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipesDatabase : RoomDatabase(){

    abstract fun recipesDao() : RecipesDao
}