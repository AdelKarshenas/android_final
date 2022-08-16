package com.example.tcp_ip

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import java.lang.Float.max
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt








class phasorclass : View {
    val strokearrow= resources.displayMetrics.widthPixels.toFloat()
    var axisx: ArrayList<vector_with_xandy> = arrayListOf()
   // var axisx: Array<Float> = emptyArray()
    var colorarray: Array<String> = emptyArray()
    var statuskey = 2
    var statuskey2=-1
    var baseX=resources.displayMetrics.widthPixels.toFloat()
    var baseY= resources.displayMetrics.heightPixels.toFloat()
    var k= (baseX/baseY).toFloat()
    var kComparison = (k/0.49).toFloat()
    //var kComparison = 1f
    val Width = this.resources.displayMetrics.widthPixels.toFloat()
    val height = this.resources.displayMetrics.heightPixels.toFloat()
    val mWidth = this.resources.displayMetrics.widthPixels.toFloat() * (0.25+0.375)
    val z= (this.resources.displayMetrics.widthPixels.toFloat()*(0.375)) / (this.resources.displayMetrics.heightPixels)
    val mHeight = this.resources.displayMetrics.heightPixels* (z)
    val radius= (this.resources.displayMetrics.widthPixels*(0.40)/kComparison).toFloat()
    val m1 =(this.resources.displayMetrics.widthPixels/2).toFloat()
    val m2 =(this.resources.displayMetrics.heightPixels/3.5).toFloat()
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        // first case is current
        // second case is voltage
        // third case is power
        // fourth case is none above
        val paint = Paint()
        paint.strokeWidth = (1/kComparison).toFloat()
        paint.color=(Color.parseColor("#008000"))
        paint.setColor(Color.parseColor("#D8D8D8"))
        paint.setStyle(Paint.Style.STROKE)
//        paint.strokeWidth = (5/kComparison).toFloat()
        paint.strokeWidth=5f
        //Drawing the circles
        for (i in 1..5){

            canvas.drawCircle(m1,m2,radius*i/6,paint)
        }
/*        canvas.drawCircle(m1,m2.toFloat(),radius/6,paint)
        canvas.drawCircle(m1,m2.toFloat(),radius*2/6,paint)
        canvas.drawCircle(m1,m2.toFloat(),radius*3/6,paint)
        canvas.drawCircle(m1,m2.toFloat(),radius*4/6,paint)
        canvas.drawCircle(m1,m2.toFloat(),radius*5/6,paint)*/
        canvas.drawLine(m1,m2+radius/6,m1,m2+radius,paint)
        canvas.drawLine(m1,m2-radius/6,m1,m2-radius,paint)
        canvas.drawLine(m1+radius/6,m2,m1+radius,m2,paint)
        canvas.drawLine(m1-radius/6,m2,m1-radius,m2,paint)
//        paint.strokeWidth = (8/kComparison).toFloat()
        val a:Double=width/1442.0
        paint.strokeWidth= (a*8).toFloat()
        for (i in 0..11){
            var degree = 30.0*i
            canvas.drawLine((m1+radius/6* cos(Math.toRadians(degree))).toFloat(),(m2+radius/6* sin(Math.toRadians(degree))).toFloat(),(m1+radius* cos(Math.toRadians(degree))).toFloat(),(m2+radius* sin(Math.toRadians(degree))).toFloat(),paint)

        }



        //        canvas.drawLine((m1+radius/6* cos(0.523599)).toFloat(),(m2+radius/6* sin(0.523599)).toFloat(),(m1+radius* cos(0.523599)).toFloat(),(m2+radius* sin(0.523599)).toFloat(),paint)
        //        canvas.drawLine((m1+radius/6* cos(0.785398)).toFloat(),(m2+radius/6* sin(0.785398)).toFloat(),(m1+radius* cos(0.785398)).toFloat(),(m2+radius* sin(0.785398)).toFloat(),paint)
        //        canvas.drawLine((m1+radius/6* cos(1.0472)).toFloat(),(m2+radius/6* sin(1.0472)).toFloat(),(m1+radius* cos(1.0472)).toFloat(),(m2+radius* sin(1.0472)).toFloat(),paint)
        //        canvas.drawLine((m1+radius/6* cos(1.0472)).toFloat(),(m2+radius/6* sin(1.0472)).toFloat(),(m1+radius* cos(1.0472)).toFloat(),(m2+radius* sin(1.0472)).toFloat(),paint)


        //VL-N
        if(statuskey==3||statuskey2==3)
        {
            val Width = this.resources.displayMetrics.widthPixels.toFloat()
            val height = this.resources.displayMetrics.heightPixels.toFloat()
            paint.color=(Color.parseColor("#008000"))

            for (i in 0..2) {
                //last try
                paint.setColor(Color.parseColor(colorarray[i]))
                val end_p=Point(axisx[i].x.toInt(), axisx[i].y.toInt())
                val start_p=Point(m1.toInt(), m2.toInt())
                val returnlist = arrow(start_p,end_p,width = Width,height = height)

                canvas.drawLine(m1, m2, returnlist[4],returnlist[5] , paint)
                paint.setStyle(Paint.Style.FILL)
                val a= Point(returnlist[0].toInt(),returnlist[1].toInt())
                val b= Point(returnlist[2].toInt(),returnlist[3].toInt())
                var path = Path()
                path= Path().apply { fillType = Path.FillType.EVEN_ODD}
                path.moveTo(b.x.toFloat(), b.y.toFloat())
                path.lineTo(a.x.toFloat(), a.y.toFloat())
                path.lineTo(b.x.toFloat(), b.y.toFloat())
                path.lineTo(axisx[i].x.toFloat(),axisx[i].y.toFloat())
                path.lineTo(a.x.toFloat(), a.y.toFloat())
                path.close()
                canvas.drawPath(path,paint)
//                canvas.drawLine(returnlist[0],returnlist[1],axisx[i],axisx[i] , paint)
//                canvas.drawLine(returnlist[2], returnlist[3], axisx[i],axisx[i] , paint)
//                canvas.drawLine(returnlist[2], returnlist[3], returnlist[0],returnlist[1] , paint)


            }

            paint.setStyle(Paint.Style.STROKE)
            // maxdistance= sqrt(maxdistance)
            paint.setColor(Color.parseColor("#909090"))
            var maxdistance=m2/2
        }
        //VL-L
        if(statuskey==2||statuskey2==2)
        {
            val Width = this.resources.displayMetrics.widthPixels.toFloat()
            val height = this.resources.displayMetrics.heightPixels.toFloat()
            paint.color=(Color.parseColor("#008000"))

            for (i in 0..2) {

                //last try
                paint.setColor(Color.parseColor(colorarray[i+2]))
                val end_p=Point(axisx[i+3].x.toInt(), axisx[i+3].y.toInt())
                val start_p=Point(m1.toInt(), m2.toInt())
                val returnlist = arrow(start_p,end_p,width = Width,height = height)

                canvas.drawLine(m1, m2, returnlist[4],returnlist[5] , paint)
                paint.setStyle(Paint.Style.FILL)
                val a= Point(returnlist[0].toInt(),returnlist[1].toInt())
                val b= Point(returnlist[2].toInt(),returnlist[3].toInt())
                var path = Path()
                path= Path().apply { fillType = Path.FillType.EVEN_ODD}
                path.moveTo(b.x.toFloat(), b.y.toFloat())
                path.lineTo(a.x.toFloat(), a.y.toFloat())
                path.lineTo(b.x.toFloat(), b.y.toFloat())
                path.lineTo(axisx[i+3].x.toFloat(),axisx[i+3].y.toFloat())
                path.lineTo(a.x.toFloat(), a.y.toFloat())
                path.close()
                canvas.drawPath(path,paint)
            }
            paint.setStyle(Paint.Style.STROKE)
            paint.setColor(Color.parseColor("#909090"))
            var maxdistance=m2/2
        }
        // I
        if(statuskey==1||statuskey2==1)
        {
            for (i in 0..2) {
                val deltaX: Float = ((axisx[i+6].x - m1).toFloat())
                val deltay: Float = ((axisx[i+6].y - m2).toFloat())
                val frac = 0.1.toFloat()

                val point_x_1: Float = m1 + ((1 - frac)  *deltaX + frac  *deltay)
                val point_y_1: Float = m2 + ((1 - frac)  *deltay - frac  *deltaX)

                val point_x_2 = axisx[i+6]
                val point_y_2: Float = axisx[i+6].y.toFloat()

                val point_x_3: Float = m1 + ((1 - frac)  *deltaX - frac  *deltay)
                val point_y_3: Float = m2 + ((1 - frac)  *deltay + frac  *deltaX)
                val end_p=Point(axisx[i+6].x.toInt(), axisx[i+6].y.toInt())
                val start_p=Point(m1.toInt(), m2.toInt())
                val returnlist = arrow(start_p,end_p,width = Width,height = height)
//                paint.strokeWidth = 5f
                paint.setColor(Color.parseColor(colorarray[i+6]))
                canvas.drawLine(m1,m2,returnlist[4],returnlist[5] , paint)

                canvas.drawLine(returnlist[0],returnlist[1],
                    axisx[i+6].x.toFloat(), axisx[i+6].y.toFloat(), paint)
                canvas.drawLine(returnlist[2], returnlist[3],
                    axisx[i+6].x.toFloat(), axisx[i+6].y.toFloat(), paint)
                canvas.drawLine(returnlist[2], returnlist[3], returnlist[0],returnlist[1] , paint)

            }
            paint.setStyle(Paint.Style.STROKE)
            //       maxdistance= sqrt(maxdistance)
            paint.setColor(Color.parseColor("#909090"))


        }
        paint.setStyle(Paint.Style.STROKE)
        canvas.drawCircle(m1,m2.toFloat(),radius,paint)



    }

    fun setPointA(x0: ArrayList<vector_with_xandy>) {
        axisx = x0
    }

//    fun setPointB(y0:Array<Float>) {
//        axisx = y0
//    }
    fun setColor(color1:Array<String>){
        colorarray=color1
    }
    fun setStatus(flag:Int)
    {
        statuskey=flag
    }

    fun setStatus2(flag:Int)
    {
        statuskey2=flag
    }
    fun getstatus2(): Int {
        return statuskey2
    }

    fun draw() {
        invalidate()
        requestLayout()
    }
}

fun arrow(pstart: Point, psecond:Point,width:Float,height:Float ): List<Float> {
    var Shib1 = (psecond.y - pstart.y) / (pstart.x - psecond.x).toFloat()
    var Shib2 = -(pstart.x - psecond.x) / (psecond.y - pstart.y).toFloat()
    var constantArrow = 0.04 * height * 0.04 * height
    var constantArrow2 = 0.008 * height * 0.008 * height
    var Arrow2 = sqrt((constantArrow / (1 + (Shib1 * Shib1))).toFloat())
    var Arrow3 = sqrt((constantArrow2 / (1 + (Shib2 * Shib2))).toFloat())
    var p1x=0.0.toFloat()
    var p1y=0.0.toFloat()
    var p2x=0.0.toFloat()
    var p2y=0.0.toFloat()
    var p3x=0.0.toFloat()
    var p3y=0.0.toFloat()
    if(psecond.y-pstart.y==0){
        if(psecond.x>pstart.x){
            p1x=psecond.x-sqrt(constantArrow).toFloat()
            p1y=psecond.y.toFloat()
            p2x=psecond.x-sqrt(constantArrow).toFloat()
            p2y=psecond.y-sqrt(constantArrow2).toFloat()
            p3x=psecond.x-sqrt(constantArrow).toFloat()
            p3y=psecond.y+sqrt(constantArrow2).toFloat()
        }else{
            p1x=psecond.x+sqrt(constantArrow).toFloat()
            p1y=psecond.y.toFloat()
            p2x=psecond.x+sqrt(constantArrow).toFloat()
            p2y=psecond.y-sqrt(constantArrow2).toFloat()
            p3x=psecond.x+sqrt(constantArrow).toFloat()
            p3y=psecond.y+sqrt(constantArrow2).toFloat()
        }
    }
    else if(psecond.x-pstart.x==0){
        if(psecond.y<pstart.y){
            p1x=psecond.x.toFloat()
            p1y=psecond.y+sqrt(constantArrow).toFloat()
            p2x=psecond.x-sqrt(constantArrow2).toFloat()
            p2y=psecond.y+sqrt(constantArrow).toFloat()
            p3x=psecond.x+sqrt(constantArrow2).toFloat()
            p3y=psecond.y+sqrt(constantArrow).toFloat()
        }
        else{
            p1x=psecond.x.toFloat()
            p1y=psecond.y-sqrt(constantArrow).toFloat()
            p2x=psecond.x-sqrt(constantArrow2).toFloat()
            p2y=psecond.y-sqrt(constantArrow).toFloat()
            p3x=psecond.x+sqrt(constantArrow2).toFloat()
            p3y=psecond.y-sqrt(constantArrow).toFloat()
        }
    }

    else if(psecond.x>pstart.x&&psecond.y<pstart.y) {

        p1x = psecond.x - Arrow2
        p1y = psecond.y + (Shib1 * Arrow2)
        p2x = p1x - Arrow3
        p2y = p1y + (Shib2 * Arrow3)
        p3x = p1x + Arrow3
        p3y = p1y + (-Shib2 * Arrow3)
    }
    else if(psecond.x>pstart.x&&psecond.y>pstart.y){

        p1x = psecond.x - Arrow2
        p1y = psecond.y + (Shib1 * Arrow2)
        p2x = p1x + Arrow3
        p2y = p1y - (Shib2 * Arrow3)
        p3x = p1x - Arrow3
        p3y = p1y + (Shib2 * Arrow3)
    }
    else if(psecond.x<pstart.x&&psecond.y<pstart.y){

        p1x = psecond.x + Arrow2
        p1y = psecond.y - (Shib1 * Arrow2)
        p2x = p1x - Arrow3
        p2y = p1y + (Shib2 * Arrow3)
        p3x = p1x + Arrow3
        p3y = p1y + (-Shib2 * Arrow3)
    }else {

        p1x = psecond.x + Arrow2
        p1y = psecond.y - (Shib1 * Arrow2)
        p2x = p1x - Arrow3
        p2y = p1y + (Shib2 * Arrow3)
        p3x = p1x + Arrow3
        p3y = p1y + (-Shib2 * Arrow3)
    }
    var plist= listOf<Float>(p2x,p2y,p3x,p3y,p1x,p1y)
    return plist

}



