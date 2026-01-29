package com.chauxdevapps.catwalletapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.chauxdevapps.catwalletapp.ui.catlist.CatListScreen
import com.chauxdevapps.catwalletapp.ui.theme.CatWalletAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatWalletAppTheme {
                CatListScreen(viewModel = hiltViewModel())
            }
        }
    }
}