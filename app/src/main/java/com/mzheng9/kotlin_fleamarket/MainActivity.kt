package com.mzheng9.kotlin_fleamarket

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)
        if (savedInstanceState == null) {
            if (prefs.getBoolean(SHOW_MESSAGE_AT_START, false)) {
                welcomeAlert()
            }
        }
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.let {
                it.title = when (destination.id) {

                    R.id.productDisplayFragment -> getString(R.string.display_frag_textview)
                    R.id.dataEntryFragment -> getString(R.string.data_entry_frag_textview)

                    else -> getString(R.string.app_name)
                }
            }
        }
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

    private fun welcomeAlert() {
        val msg = resources.getString(R.string.message_body)
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(R.string.welcome)
            setMessage(msg)
            setIcon(R.drawable.logo)
            setPositiveButton(R.string.ok, null)
            show()
        }
    }


    companion object {
        const val SHOW_MESSAGE_AT_START = "show_message_at_start"
        const val SHOW_NOW_IMAGE = "show_now_image"
        const val EFFECT_SELECTION = "effect_selection"
    }
}