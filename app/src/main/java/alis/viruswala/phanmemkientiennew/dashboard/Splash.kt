package alis.viruswala.phanmemkientiennew.dashboard

import alis.viruswala.phanmemkientiennew.R
import alis.viruswala.phanmemkientiennew.jumpcode.presentation.NoInternetActivity
import alis.viruswala.phanmemkientiennew.jumpcode.presentation.WebViewActivity
import alis.viruswala.phanmemkientiennew.jumpcode.utils.UiState
import alis.viruswala.phanmemkientiennew.jumpcode.utils.isNetworkConnected
import alis.viruswala.phanmemkientiennew.jumpcode.viewmodel.JumpCodeViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class Splash : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[JumpCodeViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.urlResponse.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
                    if (state.data.code == "0") {
                        Log.d("JumpCode", state.data.data?.jumpAddress ?: "")
                        if (state.data.data?.jump == true) {
                            toNextActivity(state.data.data.jumpAddress)
                        } else {
                            toNextActivity()
                        }
                    } else errorHandling(state.data.msg ?: "")
                }
                is UiState.Error -> errorHandling(state.exception.localizedMessage ?: "")
            }
        }
        if (isNetworkConnected()) viewModel.getJumpUrl("123456")
        else toNoInternetActivity()
    }

    private fun toNextActivity(url: String? = null) {
        if(url != null){
            startActivity(
                WebViewActivity.createIntent(this, url))
        }else{
            startActivity(MainActivity.getStartIntent(this))
        }
        finish()
    }

    private fun errorHandling(message: String) {
        Log.d("Error", message)
        toNextActivity()
    }
    private fun toNoInternetActivity() {
        startActivity(NoInternetActivity.createIntent(this))
        finish()
    }
}