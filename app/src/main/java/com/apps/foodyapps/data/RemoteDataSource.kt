package com.apps.foodyapps.data

import com.apps.foodyapps.models.FoodRecipes
import com.apps.foodyapps.data.network.FoodRecipesApi
import com.apps.foodyapps.models.FoodJoke
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
){
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipes> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>) : Response<FoodRecipes> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

    suspend fun getFoodJoke(apiKey : String): Response<FoodJoke> {
        return foodRecipesApi.getFoodJoke(apiKey)
    }

}