package alis.viruswala.phanmemkientiennew.secondwebview

import alis.viruswala.phanmemkientiennew.databinding.FragmentIndexBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient


class IndexFragment : Fragment() {

    private var _binding : FragmentIndexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIndexBinding.inflate(inflater,container,false)
        val rootView = (binding.root)
        webView()
        return rootView
    }

    private fun webView(){

        val webView : WebView = binding.viewmoto
        webView.webViewClient = WebViewClient()
        webView.loadUrl("file:///android_asset/index.html")
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}