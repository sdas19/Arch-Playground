package com.soumyajit.arch_playground.dynamicfeaturemodule

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.play.core.splitcompat.SplitCompat
import com.soumyajit.core.CoreComponentProvider
import retrofit2.Retrofit
import javax.inject.Inject

class DynamicFeatureModuleEntryActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofit: Retrofit
    lateinit var dynamicFeatureDiComponent: DynamicFeatureDiComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dynamicFeatureDiComponent =
            Initialiser.dynamicFeatureDaggerComponent(coreComponentProvider = (application as CoreComponentProvider))
        (dynamicFeatureDiComponent as DynamicFeatureDeps).inject(this)
        setContentView(R.layout.activity_dynamic_feature_module_entry)
        Toast.makeText(this, "Retrofit -> ${retrofit.hashCode()}", Toast.LENGTH_LONG).show()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this);
    }
}