package project.penadidik.geocoding.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations
import project.penadidik.geocoding.RxSchedulersOverrideRule

open class BaseViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    open fun clear() {}
}