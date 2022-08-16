package com.example.tcp_ip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment2.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var axix= arrayOf(950f,200f,800f,200f,1000f,1000f,950f,200f,800f)
    var axixy= arrayOf(1000f,1000f,1400f,800f,1000f,1200f,1000f,1000f,1400f)
    var colorarray= arrayOf("#FF0000","#0000FF","#008000","#FF0000","#0000FF","#008000","#FF0000","#0000FF","#008000")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
    fun settext(Str :Status_info){
        fwtext.text=Str.FWversion.toString()
        modeltext.text=Str.Model.toString()
        sntext.text=Str.SN.toString()
        MDtext.text=Str.MD.toString()
        mantext.text=Str.Man.toString()
        teltext.text=Str.Tel.toString()
        wbtext.text=Str.Wb.toString()

    }
}