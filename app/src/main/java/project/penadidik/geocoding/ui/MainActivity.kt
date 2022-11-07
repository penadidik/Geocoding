package project.penadidik.geocoding.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import project.penadidik.geocoding.R
import project.penadidik.geocoding.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
    }
}