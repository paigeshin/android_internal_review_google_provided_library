- 실제 앱을 출시한 후에나 볼 수 있다.

[https://developer.android.com/guide/playcore/in-app-review/kotlin-java](https://developer.android.com/guide/playcore/in-app-review/kotlin-java)

[https://developer.android.com/guide/playcore#java-kotlin](https://developer.android.com/guide/playcore#java-kotlin)

[https://developer.android.com/ndk/guides](https://developer.android.com/ndk/guides)

[https://www.youtube.com/watch?v=UguDy5-6HaI](https://www.youtube.com/watch?v=UguDy5-6HaI)

### Dependency

```kotlin
implementation 'com.google.android.play:core:1.9.0'
```

### Source

```kotlin
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
```