package com.chauxdevapps.catwalletapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.PartySystem
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun ConfettiComponent(
    modifier: Modifier = Modifier,
    onFinished: () -> Unit = {}
) {
    val party = Party(
        speed = 10f,
        maxSpeed = 30f,
        damping = 0.9f,
        spread = 360,
        colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
        emitter = Emitter(duration = 1, TimeUnit.SECONDS).perSecond(50),
        position = Position.Relative(0.5, 0.3)
    )

    KonfettiView(
        parties = listOf(party),
        modifier = modifier,
        updateListener = object : nl.dionsegijn.konfetti.compose.OnParticleSystemUpdateListener {
            override fun onParticleSystemEnded(system: PartySystem, activeSystems: Int) {
                if (activeSystems == 0) {
                    onFinished()
                }
            }
        }
    )
}
