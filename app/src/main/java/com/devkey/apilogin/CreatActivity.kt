package com.devkey.apilogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.Request.Method.POST
import com.android.volley.toolbox.JsonObjectRequest
import com.devkey.apilogin.databinding.ActivityCreatBinding
import org.json.JSONObject

class CreatActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityCreatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCreatBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnCreatUser.setOnClickListener {

            val name = mBinding.etName.text.toString().trim()
            val job = mBinding.etJob.text.toString().trim()

            saveUser(name, job)
        }

    }

    private fun saveUser(name: String, job: String){
        val url = Constants.BASE_URL + Constants.API_PATH + Constants.CREAT_PATH

        val jsonParams = JSONObject()
        jsonParams.put(Constants.NAME_PARAMS, name)
        jsonParams.put(Constants.JOB_PARAMS, job)

        val jsonObjectRequest = object : JsonObjectRequest(POST, url, jsonParams, {response->
            val name = response.optString(Constants.NAME_PROPERTY, Constants.ERROR_VALUE)
            val job = response.optString(Constants.JOB_PROPERTY, Constants.ERROR_VALUE)
            val id = response.optString(Constants.ID_PROPERTY, Constants.ERROR_VALUE)
            val date = response.optString(Constants.DATE_PROPERTY, Constants.ERROR_VALUE)

            mBinding.tvResultMessage.text = getString(R.string.success_save_user)

            setData(id, name, job, date)
        },{
            it.printStackTrace()
            mBinding.tvResultMessage.text = getString(R.string.error_save_user)
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/json"
                return params
            }
        }

        LoginApplication.reqResAPI.addToRequestQueue(jsonObjectRequest)

    }

    private fun setData(id: String, name: String, job: String, date: String){
        with(mBinding){
            cvResult.visibility = View.VISIBLE
            tvId.text = id
            tvName.text = name
            tvJob.text = job
            tvDate.text = date
        }
    }
}