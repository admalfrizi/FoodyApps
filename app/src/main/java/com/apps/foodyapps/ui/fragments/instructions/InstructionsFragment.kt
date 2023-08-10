package com.apps.foodyapps.ui.fragments.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.apps.foodyapps.databinding.FragmentInstructionsBinding
import com.apps.foodyapps.models.Result
import com.apps.foodyapps.utils.Constants

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInstructionsBinding.inflate(layoutInflater)
        val view = binding.root

        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)

        binding.instructionWebView.webViewClient = object : WebViewClient() {

        }
        val webSiteUrl : String = myBundle!!.sourceUrl
        binding.instructionWebView.loadUrl(webSiteUrl)

        return view
    }

}