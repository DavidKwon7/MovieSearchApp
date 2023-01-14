package com.flow.moviesearchapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.flow.moviesearchapp.databinding.FragmentWebviewBinding

class WebviewFragment : Fragment() {

    private var _binding: FragmentWebviewBinding? = null
    private val binding get() = _binding!!
    val args: WebviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebviewBinding.inflate(
            inflater, container, false
        )
        val view = binding.root
        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = args.webviewData?.link
        binding.webview.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            setSupportMultipleWindows(true)
        }
        binding.webview.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            if (url != null) {
                loadUrl(url)
            }
        }
    }
}