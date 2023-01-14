package com.flow.moviesearchapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.flow.moviesearchapp.databinding.FragmentSearchBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Log.d("데이터 전송 테스트", "onViewCreated: ${args.webviewData?.link}")
        // Toast.makeText(requireContext(), "${args.webviewData?.link}", Toast.LENGTH_SHORT).show()

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