package com.apps.foodyapps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.apps.foodyapps.R
import com.apps.foodyapps.databinding.IngredientsRowLayoutBinding
import com.apps.foodyapps.models.ExtendedIngredient
import com.apps.foodyapps.utils.Constants.Companion.BASE_IMAGE_URL
import com.apps.foodyapps.utils.RecipesDiffUtils
import java.util.Locale

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    inner class MyViewHolder(val binding: IngredientsRowLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = IngredientsRowLayoutBinding.inflate(view, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.ingredientsIv.load(BASE_IMAGE_URL + ingredientsList[position].image){
            crossfade(600)
            error(R.drawable.baseline_broken_image_24)
        }
        holder.binding.ingredientsName.text = ingredientsList[position].name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
        holder.binding.ingredientsAmount.text = ingredientsList[position].amount.toString()
        holder.binding.ingredientsUnit.text = ingredientsList[position].unit
        holder.binding.ingredientsConst.text = ingredientsList[position].consistency
        holder.binding.ingredientsOriginal.text = ingredientsList[position].original
    }

    fun setData(newIngredients: List<ExtendedIngredient>) {
        val ingredientsDiffUtils = RecipesDiffUtils(ingredientsList, newIngredients)
        val diffUtilsResult = DiffUtil.calculateDiff(ingredientsDiffUtils)

        ingredientsList = newIngredients
        diffUtilsResult.dispatchUpdatesTo(this)
    }

}