package com.example.handson5.ui.main

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.example.handson5.R
import com.example.handson5.data.manager.DownloadWorker

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        Glide.with(this).load(intent.getStringExtra("image"))
            .into(findViewById<ImageView>(R.id.image))
        findViewById<TextView>(R.id.description).text = intent.getStringExtra("description")
        findViewById<TextView>(R.id.title).text = intent.getStringExtra("title")

        val workManager = WorkManager.getInstance(this)
        val workRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java)
            .addTag(DOWNLOAD_WORKER)
            .build()

        findViewById<Button>(R.id.downloadBtn).setOnClickListener {
            replayAnimation()
            workManager.enqueueUniqueWork(DOWNLOAD_WORKER, ExistingWorkPolicy.REPLACE, workRequest)
        }

        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this, { res ->
            if (res.state == WorkInfo.State.SUCCEEDED) {
                findViewById<AppCompatImageView>(R.id.replay).visibility = View.GONE
                Toast.makeText(this, "download iniciado", Toast.LENGTH_SHORT).show()
                doneAnimation()
            }
        })

    }


    private fun replayAnimation() {
        findViewById<AppCompatImageView>(R.id.done).visibility = View.GONE
        findViewById<AppCompatImageView>(R.id.replay).visibility = View.VISIBLE

        ObjectAnimator.ofFloat(
            findViewById<AppCompatImageView>(R.id.replay),
            View.ROTATION,
            -360f
        )
            .apply {
                duration = 1000
                interpolator = DecelerateInterpolator()
                repeatMode = ValueAnimator.RESTART
                start()
            }
    }
    private fun doneAnimation(){
        findViewById<AppCompatImageView>(R.id.done).visibility = View.VISIBLE
        findViewById<AppCompatImageView>(R.id.replay).visibility = View.GONE

        ObjectAnimator.ofFloat(findViewById(R.id.done), View.ROTATION_X, 360f).apply {
            duration = 1000
            interpolator = BounceInterpolator()
            start()
        }
    }

    companion object {
        const val DOWNLOAD_WORKER = "DOWNLOAD_WORKER"
    }


}