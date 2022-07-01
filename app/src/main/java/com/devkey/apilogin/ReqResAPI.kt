package com.devkey.apilogin

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class ReqResAPI constructor(contex: Context) {

    companion object {
        @Volatile
        private var INSTANCE: ReqResAPI? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this ){
            INSTANCE ?: ReqResAPI(context).also { INSTANCE = it}
        }
    }

    val requestQueue: RequestQueue by lazy{
        Volley.newRequestQueue(contex.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }

}