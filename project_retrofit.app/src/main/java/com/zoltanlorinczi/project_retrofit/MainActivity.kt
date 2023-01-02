package com.zoltanlorinczi.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toolbar
import androidx.navigation.findNavController

import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    //private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<RelativeLayout>(R.id.toolbar)

        val navView: BottomNavigationView = binding.bottomNavigationView

        val navController = findNavController(R.id.nav_host_fragment)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.loginFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    navView.visibility = View.GONE
                    toolbar.visibility = View.GONE
                }

                R.id.myGroupsFragment -> {
                    toolbar.toolbarText.text = "My Groups"
                    bottomNavigationView.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                    toolbar.newTaskButton.visibility = View.GONE
                }

                R.id.activitiesFragment -> {
                    toolbar.toolbarText.text = "Activities"
                    bottomNavigationView.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                    toolbar.newTaskButton.visibility = View.GONE
                }

                R.id.profileFragment -> {
                    toolbar.toolbarText.text = "My Profile"
                    bottomNavigationView.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                    toolbar.newTaskButton.visibility = View.GONE
                }

                R.id.updateProfileFragment -> {
                    toolbar.toolbarText.text = "Udate Profile"
                    bottomNavigationView.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                    toolbar.newTaskButton.visibility = View.GONE
                }

                R.id.listFragment -> {
                    toolbar.toolbarText.text = "My Tasks"
                    toolbar.newTaskButton.visibility = View.VISIBLE
                    bottomNavigationView.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                }

                R.id.taskDetailFragment -> {
                    toolbar.toolbarText.text = "Task Description"
                    bottomNavigationView.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                    toolbar.newTaskButton.visibility = View.GONE
                }

                R.id.createTaskFragment -> {
                    toolbar.toolbarText.text = "Create new task"
                    bottomNavigationView.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                    toolbar.newTaskButton.visibility = View.GONE
                }

                R.id.departmentFragment -> {
                    toolbar.toolbarText.text = "My group members"
                    bottomNavigationView.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                    toolbar.newTaskButton.visibility = View.GONE
                }

                R.id.splashFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    navView.visibility = View.GONE
                    toolbar.visibility = View.GONE
                }

//                else -> {
//                    bottomNavigationView.visibility = View.VISIBLE
//                    navView.visibility = View.VISIBLE
//                }
            }
        }


        val newTaskButton: Button
        newTaskButton = findViewById(R.id.newTaskButton)
        newTaskButton.setOnClickListener { navController.navigate(R.id.createTaskFragment) }


//        if(actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true)
//        }


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called!")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called!")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called!")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

}