package com.haikujam.assignment.samsruti

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_activity.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        navController = findNavController(R.id.nav_host_main_fragment)

        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupNavigation()
    }

    private fun setupNavigation() {
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.v("MainActivity", "Listener called")
            when(destination.id){
                R.id.mainFragment ->{
                    toolbar.visibility = View.GONE
                }
                R.id.onBoardingFragment ->{
                    toolbar.visibility = View.VISIBLE
                    toolbar.toolbar_title.text = destination.label

                }
                R.id.jammingFragment ->{
                    toolbar.visibility = View.VISIBLE
                    toolbar.toolbar_title.text = destination.label
                }
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
