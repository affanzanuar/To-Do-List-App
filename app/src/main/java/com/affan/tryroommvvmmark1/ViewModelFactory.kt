package com.affan.tryroommvvmmark1

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.affan.tryroommvvmmark1.ui.insert.NoteUpdateViewModel
import com.affan.tryroommvvmmark1.ui.main.MainViewModel

class ViewModelFactory private constructor( private val mApplication: Application)
    : ViewModelProvider.NewInstanceFactory (){

        companion object {
            @Volatile
            private var instance : ViewModelFactory? = null

            @JvmStatic
            fun getInstance (application: Application) : ViewModelFactory {
                if (instance == null){
                    synchronized(ViewModelFactory::class.java) {
                        instance = ViewModelFactory(application)
                    }
                }
                return instance as ViewModelFactory
            }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(NoteUpdateViewModel::class.java)){
            return NoteUpdateViewModel(mApplication) as T
        }

        throw IllegalArgumentException ("Unknown class ViewModel : ${modelClass.name}")
    }

}