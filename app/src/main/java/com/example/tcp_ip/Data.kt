package com.example.tcp_ip

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.IllegalStateException
import java.net.Socket


//var sock1: Socket?=null
var output :DataOutputStream?=null
val mHandler= Handler()

var v_and_i=VandU()
var pf_and_i=PfAndI()
var ep_and_qq=EqandEp()
var pa_and_qa=PaAndQa()

class Data : AppCompatActivity() {
    val loading = Loading(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        val toolbar = toolbarmontoring
        setSupportActionBar(toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        getSupportActionBar()?.setTitle("")
        val adapter= myviewpageadap(supportFragmentManager)
        // Adding the fragments to the toolbar
        adapter.addfragment(v_and_i, "V & F")
        adapter.addfragment(pf_and_i, "I & PF")
        adapter.addfragment(pa_and_qa, "P & Q")
        adapter.addfragment(ep_and_qq, "Eq & Ep")
        viewpager.adapter=adapter
        tabLayout.setupWithViewPager(viewpager)

                tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    @SuppressLint("SetTextI18n")
                    override fun onTabSelected(tab: TabLayout.Tab?) {

                        if (tab!!.text == "V & F") {
                            tablayout_title.text = "Voltage and Frequency"
                        } else if (tab!!.text == "P & Q") {
                            tablayout_title.text = "Power and Reactive Power"
                        } else if (tab!!.text == "I & PF") {
                            tablayout_title.text = "Current and Power Factor"
                        }
                        if (tab!!.text == "Eq & Ep") {
                            tablayout_title.text = "Energy and Reactive Energy"
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }
                })
      loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },5000)
        mHandler.postDelayed(mToastRunnable, 1);



    }

    override fun onDestroy() {
        mHandler.removeCallbacks(mToastRunnable);
        super.onDestroy()
    }



    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {
            CoroutineScope(IO).launch {
                val Json_from_the_board=dataexchange("192.168.160.1", 321,"monitoring")
                val gson = Gson()
                val monitoring_data = gson.fromJson(Json_from_the_board, info::class.java)
                loading.isDismiss()
                runOnUiThread{
                    //Updating the data
                    try {
                        v_and_i.settext(monitoring_data)
                        v_and_i.setunit(monitoring_data)
                    }catch (e:IllegalStateException){

                    }
                    try {
                        pf_and_i.settext(monitoring_data)
                        pf_and_i.setunit(monitoring_data)
                    }catch (e:IllegalStateException){

                    }
                    try {
                        pa_and_qa.settext(monitoring_data)
                        pa_and_qa.setunit(monitoring_data)
                    }catch (e:IllegalStateException){

                    }
                    try {
                        ep_and_qq.settext(monitoring_data)
                        ep_and_qq.setunit(monitoring_data)
                    }catch (e:IllegalStateException){

                    }
                }

            }
            mHandler.postDelayed(this, 1500)
        }
    }

}



class myviewpageadap(manager: FragmentManager) : FragmentPagerAdapter(manager){
    private val fragmentlist : MutableList<Fragment> = ArrayList()
    private val titletlist : MutableList<String> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return fragmentlist[position]
    }

    override fun getCount(): Int {
        return fragmentlist.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return titletlist[position]
    }
    fun addfragment(fragment: Fragment, title: String){
        fragmentlist.add(fragment)
        titletlist.add(title)
    }
}



public fun whatUnit(num :Long): String {
    var unittype=""
    if(num>999){
        unittype="K"
    }
    else if (num>999999)
    {
        unittype="M"
    }
    else if (num>999999999)
    {
        unittype="G"
    }
    else {
        unittype=""
    }
    return unittype
}
fun UnitFloat(num:Float): Float {
    var num1:Double= 0.0
    var num2:Float=0f
    if(num>0 && num<10)
    {
        num1=Math.round(num*1000.0)/1000.0
        num2=num1.toFloat()
        return num2
    }
    if(num>10 && num<100)
    {
        num1=Math.round(num*100.0)/100.0
        num2=num1.toFloat()
        return  num2
    }
    if(num>100 && num<1000)
    {
        num1=Math.round(num*10.0)/10.0
        num2=num1.toFloat()
        return num2
    }
    return num2

}