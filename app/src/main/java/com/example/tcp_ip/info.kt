package com.example.tcp_ip

//class info(var va:Int,var vb:Int,var vc:Int,var ia:Int,var ib:Int,var ic:Int,var vab:Int,var vbc:Int,var vca:Int,var pa:Int,var pb:Int,
//           var pc:Int,var qa:Int,var qb:Int,var qc:Int,var pfa:Int,var pfb:Int,var pfc:Int
//           ,var vp1:Int,var vp1a:Int,var vp2:Int,var vp2a:Int,var vp3:Int,var vp3a:Int,var ip1:Int,var ip1a:Int,var ip2:Int,var ip2a:Int,var ip3:Int,var ip3a:Int,
//        var pp1:Int,var pp1a:Int,var pp2:Int,var pp2a:Int,var pp3:Int,var pp3a:Int)


class info(var va:Double,var vb:Double,var vc:Double,var vab:Double,var vbc:Double,var vca:Double,var uv:Double,var f:Double,var ia:Double,var ib:Double,var ic:Double,var In:Double,var pfa:Double,var pfb:Double,var pfc:Double,var ui:Double
           ,var pa:Double,var pb:Double,var pc:Double,var pt:Double,var qa:Double,var qb:Double,var qc:Double,var qt:Double,var epa:Double,var epb:Double,var epc:Double,var ept:Double,var eqa:Double,var eqb:Double,var eqc:Double,var eqt:Double)

class phasor_data(var vlna_s:Double,var vlna_a:Double,var vlnb_s:Double,var vlnb_a:Double,var vlnc_s:Double,var vlnc_a:Double,var vlla_s:Double,var vlla_a:Double,
                 var vllb_s:Double,var vllb_a:Double,var vllc_s:Double,var vllc_a:Double,var ia_s:Double,var ia_a:Double,var ib_s:Double,var ib_a:Double,var ic_s:Double,var ic_a:Double)


class vector(var size:Double,var angle:Double ,var type:String)

class vector_with_xandy(var x: Double, var y: Double, var type:String)

class BarChartData(var VnnDates:Array<Float> , var VlnDates:Array<Float> , var IDates:Array<Float>,var vlna_s:Double,var vlna_a:Double,var vlnb_s:Double,var vlnb_a:Double,var vlnc_s:Double,var vlnc_a:Double,var vlla_s:Double,var vlla_a:Double,
                   var vllb_s:Double,var vllb_a:Double,var vllc_s:Double,var vllc_a:Double,var ia_s:Double,var ia_a:Double,var ib_s:Double,var ib_a:Double,var ic_s:Double,var ic_a:Double,var thd:Array<Double>)

class logging_data(var id:Int,val start_date:String,val end_date:String,val start_time:String,val end_time:String,var num_of_records:Int,var size:Int)

class logging_json(var logs:List<logging_data>)

class Status_info(var Records:Int , var Event:Int , var Alarms:Int , var Waveform:Int , var Battery:Int , var Memory:Int,
                  var Time:String,var FWversion:String,var Model:String,var SN:String , var MD:String, var Man:String,
                  var Wb:String , var Tel:String)



