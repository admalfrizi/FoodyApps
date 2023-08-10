package com.apps.foodyapps.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.apps.foodyapps.R
import com.apps.foodyapps.models.Result
import com.apps.foodyapps.ui.fragments.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup

@BindingAdapter("onRecipeClickListener")
fun onRecipeClickListener(recipeRowLayout : ConstraintLayout, result: Result) {
    Log.d("onRecipeClickListener", "CALLED")
    recipeRowLayout.setOnClickListener {
        try {
            val action = RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
            recipeRowLayout.findNavController().navigate(action)
        } catch (e: Exception){
            Log.d("onRecipeClickListener", e.toString())
        }
    }
}

@BindingAdapter("loadImageFromUrl")
fun loadImageFromUrl(imageView: ImageView, imageUrl: String){
    imageView.load(imageUrl) {
        crossfade(600)
        error(R.drawable.baseline_broken_image_24)
    }
}

@BindingAdapter("setNumberOfLikes")
fun setNumberOfLikes(textView: TextView, likes: Int) {
    textView.text = likes.toString()
}

@BindingAdapter("setNumberOfMinutes")
fun setNumberOfMinutes(textView: TextView, minutes: Int){
    textView.text = minutes.toString()
}

@BindingAdapter("applyVeganColor")
fun applyVeganColor(view: View, vegan: Boolean) {
    if(vegan){
        when(view){
            is TextView -> {
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.green
                    )
                )
            }
            is ImageView -> {
                view.setColorFilter(
                    ContextCompat.getColor(
                        view.context,
                        R.color.green
                    )
                )
            }
        }
    }
}

@BindingAdapter("parseHtml")
fun parseHtml(textView: TextView, description: String?){
    if(description != null) {
        val desc = Jsoup.parse(description).text()
        textView.text = desc
    }
}


