package com.chauxdevapps.catwalletapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chauxdevapps.catwalletapp.MainActivity
import com.chauxdevapps.catwalletapp.R
import com.chauxdevapps.catwalletapp.ui.theme.PurplePrimary
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            SplashScreen(
                onSplashComplete = {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            )
        }
    }
}

@Composable
fun SplashScreen(onSplashComplete: () -> Unit) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.purple_cat_splash)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,  // Play once
        speed = 1.0f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PurplePrimary),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize(0.5f)
        )
    }

    // Navigate to main activity when animation finishes
    LaunchedEffect(progress) {
        if (progress >= 0.99f) {
            delay(300) // Brief pause at end
            onSplashComplete()
        }
    }
}
