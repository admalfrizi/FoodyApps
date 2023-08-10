package com.apps.foodyapps.data

import com.apps.foodyapps.data.database.RecipesDao
import com.apps.foodyapps.data.database.entities.FavoriteEntity
import com.apps.foodyapps.data.database.entities.FoodJokeEntity
import com.apps.foodyapps.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    fun readReadFavoriteRecipes(): Flow<List<FavoriteEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    fun readFoodJoke() : Flow<List<FoodJokeEntity>> {
        return recipesDao.readFoodJoke()
    }

    fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDao.insertRecipes(recipesEntity)
    }

    fun insertFavoriteRecipes(favoriteEntity: FavoriteEntity) {
        recipesDao.insertFavoriteRecipe(favoriteEntity)
    }

    fun insertFoodJoke(foodJokeEntity: FoodJokeEntity){
        recipesDao.insertFoodJoke(foodJokeEntity)
    }

    fun deleteFavoriteRecipe(favoriteEntity: FavoriteEntity) {
        recipesDao.deleteFavoriteRecipe(favoriteEntity)
    }

    fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipe()
    }
}