package com.example.tcp_ip

import Records_fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_data.tabLayout
import kotlinx.android.synthetic.main.activity_data.viewpager
import kotlinx.android.synthetic.main.activity_logging.*

class Logging : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)
        val adapter= myviewpageadap(supportFragmentManager)
        adapter.addfragment(Records_fragment(),"Records")
        adapter.addfragment(Eventsfrag(),"Events and Alarms")
        val toolbar = toolbarlogging
        setSupportActionBar(toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        getSupportActionBar()?.setTitle("")
        viewpager.adapter=adapter
        tabLayout.setupWithViewPager(viewpager)
    }
}