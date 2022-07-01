package com.devkey.apilogin

import android.app.Application

class LoginApplication: Application() {

    companion object {
        lateinit var reqResAPI: ReqResAPI
    }

    override fun onCreate() {
        super.onCreate()

        //Volley
        reqResAPI = ReqResAPI.getInstance(this)

    }

}