package com.apps.foodyapps.ui.fragments.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.apps.foodyapps.R
import com.apps.foodyapps.bindingadapters.parseHtml
import com.apps.foodyapps.databinding.FragmentOverviewBinding
import com.apps.foodyapps.models.Result

class OverviewFragment : Fragment() {

    private lateinit var binding : FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOverviewBinding.inflate(layoutInflater)
        val root = binding.root

        val args = arguments
        val myBundle: Result = args!!.getParcelable<Result>("recipeBundle") as Result

        binding.mainIv.load(myBundle.image)
        binding.titleTv.text =  myBundle.title
        binding.likeTv.text = myBundle.aggregateLikes.toString()
        binding.timeView.text = myBundle.readyInMinutes.toString()

        parseHtml(binding.summaryTv, myBundle.summary)

        updateColors(myBundle.vegetarian, binding.vegetarianTv, binding.vegetarianIv)
        updateColors(myBundle.vegan, binding.veganTv, binding.veganIv)
        updateColors(myBundle.cheap, binding.cheapTv, binding.cheapIv)
        updateColors(myBundle.dairyFree, binding.dairyFreeTv, binding.dairyFreeIv)
        updateColors(myBundle.glutenFree, binding.gluttenFreeTv, binding.gluttenFreeIv)
        updateColors(myBundle.veryHealthy, binding.healthyTv, binding.healthyIv)


        return root
    }

    private fun updateColors(stateIsOn: Boolean, tv: TextView, iv: ImageView){
        if(stateIsOn){
            iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

}