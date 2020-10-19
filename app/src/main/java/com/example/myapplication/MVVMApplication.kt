package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.db.Appdatabase
import com.example.myapplication.data.network.MyApi
import com.example.myapplication.data.network.NetWorkConnectionIntercetper
import com.example.myapplication.data.repositories.UserRepository
import com.example.myapplication.ui.auth.AuthViewModelFactory
import com.example.myapplication.ui.auth.AuthviewModel
import com.example.myapplication.ui.home.profile.ProfilrViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


class MVVMApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetWorkConnectionIntercetper(instance()) }
        bind() from singleton {MyApi(instance())}
        bind() from singleton {Appdatabase(instance())}
        bind() from singleton {UserRepository(instance(),instance())}
        bind() from singleton {AuthViewModelFactory(instance())}
        bind() from singleton {ProfilrViewModelFactory(instance())}

    }
}