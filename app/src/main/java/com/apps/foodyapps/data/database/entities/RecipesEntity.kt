package com.apps.foodyapps.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.foodyapps.models.FoodRecipes
import com.apps.foodyapps.utils.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipes: FoodRecipes
){
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0
}