package project.penadidik.cleanarchitecture.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import javax.inject.Inject

class AppPrefs @Inject constructor(
    private val mContext: Context,
    private val gson: Gson
) : PrefHelper {

    private val sharedPreferences: SharedPreferences =
        mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)

    override fun setFirstRun(boolean: Boolean) {
        sharedPreferences.edit { putBoolean(FIRST_RUN, boolean) }
    }

    override fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(FIRST_RUN, true)
    }

    companion object {
        private const val FIRST_RUN = "first_run"
        private const val TOKEN = "token"
    }
}