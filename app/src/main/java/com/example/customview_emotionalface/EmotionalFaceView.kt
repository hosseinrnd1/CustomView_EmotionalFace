package com.example.customview_emotionalface

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK

    // Face border width in pixels
    private var borderWidth = 4.0f

    // View size in pixels
    private var size = 320


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        val radius = size / 2f

        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        paint.color = borderColor
        paint.style = Paint.Style.STROKE

        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth, paint)
    }


    private fun drawEyes(canvas: Canvas) {

        paint.color = eyesColor
        paint.style = Paint.Style.FILL

        val leftEyeRect = RectF(size * 0.32f, size * 0.23f , size *0.43f,size*0.50f)

        canvas.drawOval(leftEyeRect,paint)

        val rightEyeRect = RectF(size * 0.57f, size * 0.23f , size *0.68f,size*0.50f)

        canvas.drawOval(rightEyeRect,paint)


    }

    private fun drawMouth(canvas: Canvas) {

    }

}