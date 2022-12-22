package com.zoltanlorinczi.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController

import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.ui.AppBarConfiguration

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    //private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationView


        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.activitiesFragment,
                R.id.listFragment,
                R.id.myGroupsFragment,
                R.id.profileFragment

            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

//        navView.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.activitiesFragment -> {
//                    navController.navigate(R.id.activitiesFragment)
//                }
//                R.id.TasksListFragment -> {
//                    navController.navigate(R.id.TasksListFragment)
//                }
//                R.id.myGroupsFragment -> {
//                    navController.navigate(R.id.myGroupsFragment)
//                }
//                R.id.profileFragment -> {
//                    navController.navigate(R.id.profileFragment)
//                }
//            }
//            true
//        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    navView.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE
                }
            }
        }





    }




//    private fun initBottomNav(){
//        bottomNav.setOnNavigationItemSelectedListener() {item ->
//            when (item.itemId){
//                R.id.activitiesFragment ->{
//                    Navigator.replaceFragment(activitiesFragment(), true)
//                    true
//                }
//            }
//        }
//    }
//
//    fun replaceFragment(newFragment: Fragment, addToBackStack: Boolean = false) {
//        if (fragmentManager != null) {
//            val transaction = fragmentManager!!.beginTransaction()
//            transaction.replace(fragmentContainerId!!, newFragment)
//            if(addToBackStack){
//                transaction.addToBackStack(newFragment.javaClass.name)
//            }
//            transaction.commit()
//        }
//    }

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
}