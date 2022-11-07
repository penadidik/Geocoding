package project.penadidik.geocoding

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import dagger.hilt.android.HiltAndroidApp
import io.fabric.sdk.android.Fabric
import project.penadidik.geocoding.crashlytics.CrashlyticsTree
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val crashlytics = CrashlyticsCore.Builder()
            .disabled(BuildConfig.DEBUG)
            .build()
        Fabric.with(this, Crashlytics.Builder()
            .core(crashlytics).build())

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.plant(CrashlyticsTree())
    }
}