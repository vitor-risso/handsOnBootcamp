package com.example.animation

import android.animation.*
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var animation1: ImageView
    lateinit var animation2: ImageView
    lateinit var animation3: ImageView
    lateinit var animation4: ImageView
    lateinit var textAnimation: TextView
    lateinit var animation6: LinearLayout
    lateinit var animator1: ObjectAnimator
    lateinit var animator2: ObjectAnimator
    lateinit var animator3: ObjectAnimator
    lateinit var animator4: ObjectAnimator
    lateinit var animator6: ObjectAnimator
    lateinit var textAnimator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animation1 = findViewById<ImageView>(R.id.animation1)
        animation2 = findViewById<ImageView>(R.id.animation2)
        animation3 = findViewById<ImageView>(R.id.animation3)
        animation4 = findViewById<ImageView>(R.id.animation4)
        textAnimation = findViewById<TextView>(R.id.textAnimation)
        animation6 = findViewById<LinearLayout>(R.id.animation6)

        setUpAnimations()

        findViewById<AppCompatButton>(R.id.startBtn).setOnClickListener {
            start()
        }
    }


    fun setUpAnimations() {
        animator1 =
            ObjectAnimator.ofFloat(animation1, "translationX", 500f)
                .apply {
                    duration = 1500
                    interpolator = AccelerateDecelerateInterpolator()
                }

        animator2 =
            ObjectAnimator.ofFloat(animation2, "translationX", 500f)
                .apply {
                    duration = 100
                }

        animator3 = ObjectAnimator.ofFloat(animation3, "translationX", 500f).apply {
            duration = 2000
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    findViewById<ImageView>(R.id.animation3).background =
                        this@MainActivity.getDrawable(R.drawable.black_circle)
                }

                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    findViewById<ImageView>(R.id.animation3).background =
                        this@MainActivity.getDrawable(R.drawable.red_circle)
                }
            })
        }

        animator4 = ObjectAnimator.ofFloat(animation4, "translationX", 500f).apply {
            duration = 1500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
        }

        textAnimator = ObjectAnimator.ofFloat(textAnimation, "rotation", 360f).apply {
            duration = 1500

        }

        animator6 = ObjectAnimator.ofFloat(animation6, "translationY", 500f).apply {
            duration = 1500
            repeatCount = 5
            repeatMode = ValueAnimator.REVERSE
        }

    }

    private fun start() {
        AnimatorSet().apply {
            playSequentially(animator1,animator2,animator3,animator4, textAnimator, animator6)
//            play(animator1).before(animator2).before(animator3).before(animator4).before(textAnimator).before(animator6)
            start()
        }
    }
}
