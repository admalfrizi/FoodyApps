package com.apps.foodyapps.data.database

import androidx.room.TypeConverter
import com.apps.foodyapps.models.FoodRecipes
import com.apps.foodyapps.models.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipes: FoodRecipes): String {
        return gson.toJson(foodRecipes)
    }

    @TypeConverter
    fun stringToFoodRecipes(data: String): FoodRecipes {
        val listType = object : TypeToken<FoodRecipes>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun resultToString(result: Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String): Result {
        val listType = object : TypeToken<Result>() {}.type
        return gson.fromJson(data,listType)
    }

}