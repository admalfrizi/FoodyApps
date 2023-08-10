package com.apps.foodyapps.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.apps.foodyapps.data.database.entities.RecipesEntity
import com.apps.foodyapps.models.FoodRecipes
import com.apps.foodyapps.utils.NetworkResult

@BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
fun errorImageViewVisibility(
    imageView: ImageView,
    apiResponse : NetworkResult<FoodRecipes>?,
    database: List<RecipesEntity>?
) {
    if(apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
        imageView.visibility = View.VISIBLE
    }
    else if(apiResponse is NetworkResult.Loading){
        imageView.visibility = View.INVISIBLE
    }
    else if(apiResponse is NetworkResult.Success){
        imageView.visibility = View.INVISIBLE
    }
}

@BindingAdapter("readApiResponse2", "readDatabase2", requireAll = true)
fun errorTextView(
    textView: TextView,
    apiResponse : NetworkResult<FoodRecipes>?,
    database: List<RecipesEntity>?
) {
    if(apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
        textView.visibility = View.VISIBLE
        textView.text = apiResponse.message.toString()
    }
    else if(apiResponse is NetworkResult.Loading){
        textView.visibility = View.INVISIBLE
    }
    else if(apiResponse is NetworkResult.Success){
        textView.visibility = View.INVISIBLE
    }
}