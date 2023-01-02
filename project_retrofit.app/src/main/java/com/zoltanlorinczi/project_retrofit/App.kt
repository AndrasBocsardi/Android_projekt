package com.zoltanlorinczi.project_retrofit

import android.app.Application
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import java.text.SimpleDateFormat
import java.util.*

/**
 * Base class of Android app, containing components like Activities and Services.
 * Application or its sub classes are instantiated before all the activities or any other application
 * objects have been created in Android app.
 *
 * Author:  Zoltan Lorinczi
 * Date:    11/22/2021
 */
class App : Application() {

    companion object {
        var selectedForDetail: TaskResponse = TaskResponse(
            0,
            "",
            "",
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            ""
        )
        
        lateinit var sharedPreferences: SharedPreferencesManager

        fun epochToDateString(epoch: Long): String {
            val date = Date(epoch * 1000)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            return dateFormat.format(date)
        }
    }


    override fun onCreate() {
        super.onCreate()
        sharedPreferences = SharedPreferencesManager(applicationContext)
    }

}