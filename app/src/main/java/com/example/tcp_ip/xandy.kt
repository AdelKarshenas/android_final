package com.example.tcp_ip


import kotlin.math.cos
import kotlin.math.sin

class xandy(private var size: Double?, private var angle:Double, var mx:Double, var my:Double, var type:String) {
    var x: Double = size!! * cos(angle)+mx
    var y:Double= size!! * sin(angle)+my

}
