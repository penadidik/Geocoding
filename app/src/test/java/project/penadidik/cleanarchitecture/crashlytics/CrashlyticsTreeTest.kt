package project.penadidik.cleanarchitecture.crashlytics

import org.junit.Test
import project.penadidik.cleanarchitecture.crashlytics.CrashlyticsTree
import timber.log.Timber

class CrashlyticsTreeTest {
    private val crashlytics = CrashlyticsTree()

    @Test
    fun testDebug() {
        val throwable = Throwable("abc")
        Timber.d(throwable)
    }
}