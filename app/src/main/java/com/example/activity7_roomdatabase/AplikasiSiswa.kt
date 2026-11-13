package com.example.activity7_roomdatabase

import android.app.Application
import com.example.activity7_roomdatabase.repositori.ContainerApp
import com.example.activity7_roomdatabase.repositori.ContainerDataApp

class AplikasiSiswa : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}