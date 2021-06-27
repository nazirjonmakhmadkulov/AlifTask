package com.nazirjon.aliftask.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nazirjon.aliftask.R
import com.nazirjon.aliftask.utils.Constants.Companion.BASE_URL
import kotlinx.android.synthetic.main.fragment_details.*


class ItemDetailsFragment : Fragment(R.layout.fragment_details) {
    private val args: ItemDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView.webViewClient = WebViewClient()
        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.loadUrl(BASE_URL + args.guide.url)
    }
}