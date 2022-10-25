package project.penadidik.cleanarchitecture

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import dagger.hilt.android.HiltAndroidApp
import io.fabric.sdk.android.Fabric
import project.penadidik.cleanarchitecture.crashlytics.CrashlyticsTree
import project.penadidik.cleanarchitecture.BuildConfig
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