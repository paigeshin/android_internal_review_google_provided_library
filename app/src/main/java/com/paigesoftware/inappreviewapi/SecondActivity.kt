package com.paigesoftware.inappreviewapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.OnCompleteListener

class SecondActivity : AppCompatActivity() {

    private lateinit var reviewManager: ReviewManager
    private var reviewInfo: ReviewInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        reviewManager = ReviewManagerFactory.create(this)

        val request = reviewManager.requestReviewFlow()
        request.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful) {
                reviewInfo = task.result
            }

        })

    }

    override fun onBackPressed() {

        if(reviewInfo != null) {
            val flow = reviewManager.launchReviewFlow(this, reviewInfo!!)
            flow.addOnCompleteListener(OnCompleteListener { task ->
                if(task.isSuccessful) {
                    Toast.makeText(this, "Review Successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }
            })
            super.onBackPressed()
        } else {
            super.onBackPressed()
        }

    }

}