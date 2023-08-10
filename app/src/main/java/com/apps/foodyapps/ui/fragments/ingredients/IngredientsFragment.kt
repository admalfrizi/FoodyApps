package com.apps.foodyapps.ui.fragments.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.foodyapps.adapters.IngredientsAdapter
import com.apps.foodyapps.databinding.FragmentIngredientsBinding
import com.apps.foodyapps.models.Result
import com.apps.foodyapps.utils.Constants.Companion.RECIPE_RESULT_KEY

class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    private val mAdapter: IngredientsAdapter by lazy {
        IngredientsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIngredientsBinding.inflate(layoutInflater)
        val view = binding.root

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        setupRecyclerView(view)
        myBundle?.extendedIngredients?.let {
            mAdapter.setData(it)
        }

        return view
    }

    private fun setupRecyclerView(view: View){
        binding.ingredientsRv.adapter = mAdapter
        binding.ingredientsRv.layoutManager = LinearLayoutManager(requireContext())

    }

}