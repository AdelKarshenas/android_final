package com.example.tcp_ip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_data.tabLayout
import kotlinx.android.synthetic.main.activity_data.viewpager
import kotlinx.android.synthetic.main.activity_device__info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



var status=fragment1()
var info1=fragment2()

class Device_Info : AppCompatActivity() {
    val loading = Loading(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device__info)
        val toolbar = toolbardeviceinfo
        setSupportActionBar(toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        getSupportActionBar()?.setTitle("")
        val adapter= myviewpageadap(supportFragmentManager)
        adapter.addfragment(status,"Status")
        adapter.addfragment(info1,"Info")
        viewpager.adapter=adapter
        tabLayout.setupWithViewPager(viewpager)
        loading.startLoading()
        CoroutineScope(Dispatchers.IO).launch {

            val ststs = dataexchange("192.168.160.1", 321, "deviceinfo")
            val gson = Gson()
            val userObject = gson.fromJson(ststs, Status_info::class.java)

            runOnUiThread{
                loading.isDismiss()
                status.settext(userObject)
                info1.settext(userObject)
            }
        }
    }
}