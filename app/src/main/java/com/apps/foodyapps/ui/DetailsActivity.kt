package com.apps.foodyapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.apps.foodyapps.R
import com.apps.foodyapps.adapters.PagerAdapter
import com.apps.foodyapps.data.database.entities.FavoriteEntity
import com.apps.foodyapps.databinding.ActivityDetailsBinding
import com.apps.foodyapps.ui.fragments.ingredients.IngredientsFragment
import com.apps.foodyapps.ui.fragments.instructions.InstructionsFragment
import com.apps.foodyapps.ui.fragments.overview.OverviewFragment
import com.apps.foodyapps.utils.Constants.Companion.RECIPE_RESULT_KEY
import com.apps.foodyapps.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()
    private var recipeSaved = false
    private var savedRecipeId = 0

    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable(RECIPE_RESULT_KEY, args.result)

        val pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )
        binding.viewPager2.isUserInputEnabled = false
        binding.viewPager2.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2){tab, position ->
            tab.text = titles[position]
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        menuItem = menu!!.findItem(R.id.save_to_favorites_menu)
        checkSavedRecipes(menuItem)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        } else if(item.itemId == R.id.save_to_favorites_menu && !recipeSaved) {
            savedToFavorites(item)
        } else if(item.itemId == R.id.save_to_favorites_menu && recipeSaved){
            removeFromFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkSavedRecipes(menuItem: MenuItem) {

        mainViewModel.readFavoriteRecipes.observe(this){favoritesEntity ->
            try {
                for(savedRecipe in favoritesEntity) {
                    if(savedRecipe.result.id == args.result.id){
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                    }
                }
            } catch (e: Exception){
                Log.d("DetailsActivity", e.message.toString())
            }

        }
    }

    private fun savedToFavorites(item: MenuItem) {
        val favoritesEntity = FavoriteEntity(
            0,
            args.result
        )
        mainViewModel.insertFavoriteRecipes(favoritesEntity)
        changeMenuItemColor(item, R.color.yellow)
        showSnackBar("Recipes Saved")
        recipeSaved = true
    }

    private fun removeFromFavorites(item: MenuItem){
        val favoriteEntity = FavoriteEntity(
            savedRecipeId,
            args.result

        )
        mainViewModel.deleteFavoriteRecipes(favoriteEntity)
        changeMenuItemColor(item, R.color.white)
        showSnackBar("Removed From Favorites")
        recipeSaved = false
    }

    private fun showSnackBar(msg: String) {
       Snackbar.make(
           binding.detailsLayout,
           msg,
           Snackbar.LENGTH_SHORT
       ).setAction("Okay"){}.show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon?.setTint(ContextCompat.getColor(this,color))
    }

    override fun onDestroy() {
        super.onDestroy()
        changeMenuItemColor(menuItem, R.color.white)
    }
}