package com.soumyajit.staticfeature

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import javax.inject.Inject

class StaticFeatureEntryActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as StaticFeatureDepsProvider).staticFeatureDeps().inject(this)
        setContentView(R.layout.activity_static_feature_entry)
        Toast.makeText(this, "Retrofit -> ${retrofit.hashCode()}", Toast.LENGTH_LONG).show()
    }
}