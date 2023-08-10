package com.apps.foodyapps.data.network

import com.apps.foodyapps.models.FoodJoke
import com.apps.foodyapps.models.FoodRecipes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend  fun getRecipes(
        @QueryMap query: Map<String,String>
    ) : Response<FoodRecipes>

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap searchQuery: Map<String,String>
    ): Response<FoodRecipes>

    @GET("food/jokes/random")
    suspend fun getFoodJoke(
        @Query("apiKey") apiKey: String
    ): Response<FoodJoke>


}