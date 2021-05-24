package com.soumyajit.dynamicfeature

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.soumyajit.core.CoreComponentProvider
import retrofit2.Retrofit
import javax.inject.Inject

class DynamicFeatureEntryActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofit: Retrofit
    lateinit var dynamicFeatureDiComponent: DynamicFeatureDiComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dynamicFeatureDiComponent =
            Initialiser.dynamicFeatureDaggerComponent(coreComponentProvider = (application as CoreComponentProvider))
        (dynamicFeatureDiComponent as DynamicFeatureDeps).inject(this)
        setContentView(R.layout.activity_dynamic_feature_entry)
        Toast.makeText(this, "Retrofit -> ${retrofit.hashCode()}", Toast.LENGTH_LONG).show()
    }
}