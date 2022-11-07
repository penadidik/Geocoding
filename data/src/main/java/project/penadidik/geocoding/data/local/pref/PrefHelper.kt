package project.penadidik.geocoding.data.local.pref

interface PrefHelper {
    fun isFirstRun(): Boolean

    fun setFirstRun(boolean: Boolean)
}