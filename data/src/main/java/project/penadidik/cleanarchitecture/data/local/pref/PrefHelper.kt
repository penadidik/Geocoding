package project.penadidik.cleanarchitecture.data.local.pref

interface PrefHelper {
    fun isFirstRun(): Boolean

    fun setFirstRun(boolean: Boolean)
}