package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.R
import timber.log.Timber

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val appContext = applicationContext

        makeStatusNotification("Blurring image", appContext)

        return try {
            val picture = BitmapFactory.decodeResource(
                    appContext.resources,
                    R.drawable.test)

            val output = blurBitmap(picture, appContext)

            // Write bitmap to a temp file
            val outputUri = writeBitmapToFile(appContext, output)

            makeStatusNotification("Output is $outputUri", appContext)

            Result.success()
        } catch (throwable: Throwable) {
            Timber.e(throwable, "Error applying blur")
            Result.failure()
        }
    }

//    private val workManager = WorkManager.getInstance(application)

    internal fun applyBlur(blurLevel: Int) {
        workManager.enqueue(OneTimeWorkRequest.from(BlurWorker::class.java))
    }
}