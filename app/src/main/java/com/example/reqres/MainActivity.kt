package com.example.reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.reqres.databinding.ActivityMainBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var okHttpClient  = OkHttpClient()
    private val URL = "https://reqres.in/api/users/2"
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val request = Request.Builder()
            .url(URL)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.d(TAG, "onResponse: aaaaaaaaaaaaaaaa")
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    val myResponse = response.body.toString()

                    runOnUiThread {
                        binding.tvResult.text = myResponse
                    }

                }
            }
        })

    }
}