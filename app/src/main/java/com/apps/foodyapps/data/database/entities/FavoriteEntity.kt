package com.apps.foodyapps.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.foodyapps.models.Result
import com.apps.foodyapps.utils.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
) {
}