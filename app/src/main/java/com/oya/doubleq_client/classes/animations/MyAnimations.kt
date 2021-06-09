package com.oya.doubleq.classes.animations
//
//import android.util.Log
//import androidx.compose.animation.core.*
//import androidx.compose.animation.transition
//import androidx.compose.material.MaterialTheme
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.ui.graphics.SolidColor
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.oya.doubleq.classes.animations.SimpleAnimationDefinition.States.END
//import com.oya.doubleq.classes.animations.SimpleAnimationDefinition.States.START
//
//object SimpleAnimationDefinition {
//    private const val TAG = "MyAnimations"
//    fun startAnimation() {
//        val color = MaterialTheme.colors.primary
//        val simpleAnim = transition(
//            definition = simpleDefinition,
//            initState = START,
//            toState = END
//        )
//        val magnitude = simpleAnim[simplePropKey]
//        Log.d(TAG, "startAnimation: $magnitude")
//        Canvas(
//            modifier = Modifier.fillMaxWidth().height(55.dp)
//        ) {
//            drawCircle(
//                radius = magnitude,
//                brush = SolidColor(color)
//            )
//        }
//    }
//
//    enum class States {
//        START, END
//    }
//
//    val simplePropKey = FloatPropKey("simpleKey")
//    val simpleDefinition = transitionDefinition<States> {
//        state(START) { this[simplePropKey] = 20f }
//        state(END) { this[simplePropKey] = 60f }
//        transition(
//            START to END
//        ) {
//            simplePropKey using infiniteRepeatable(
//                animation = tween(
//                    durationMillis = 1000,
//                    easing = FastOutSlowInEasing,
//                    delayMillis = 500
//                ),
//                repeatMode = RepeatMode.Reverse
//            )
//        }
//    }
//}