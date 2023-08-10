package com.apps.foodyapps.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.foodyapps.models.FoodJoke
import com.apps.foodyapps.utils.Constants.Companion.FOOD_JOKE_TABLE

@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJoke: FoodJoke,
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}