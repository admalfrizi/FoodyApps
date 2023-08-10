package com.apps.foodyapps.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apps.foodyapps.adapters.FavoriteRecipesAdapter
import com.apps.foodyapps.data.database.entities.FavoriteEntity

@BindingAdapter("viewVisibility", "setData", requireAll = false)
fun setDataAndViewVisibility(
    view: View,
    favoriteEntity: List<FavoriteEntity>?,
    mAdapter: FavoriteRecipesAdapter?
) {
    if(favoriteEntity.isNullOrEmpty()){
        when(view){
            is ImageView -> {
                view.visibility = View.VISIBLE
            }
            is TextView -> {
                view.visibility = View.VISIBLE
            }
            is RecyclerView -> {
                view.visibility = View.INVISIBLE
            }
        }
    } else {
        when(view){
            is ImageView -> {
                view.visibility = View.INVISIBLE
            }
            is TextView -> {
                view.visibility = View.INVISIBLE
            }
            is RecyclerView -> {
                view.visibility = View.VISIBLE
                mAdapter?.setData(favoriteEntity)
            }
        }
    }
}