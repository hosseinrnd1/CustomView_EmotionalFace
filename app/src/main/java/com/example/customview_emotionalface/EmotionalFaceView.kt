package com.example.customview_emotionalface

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    var happinessState = HAPPY
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // View size in pixels
    private var size = 0

    private var mouthPath = Path()


    init {
        setUpAttributes(attrs)
    }

    private fun setUpAttributes(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.EmotionalFaceView, 0, 0)
            .apply {
                try {
                    faceColor =
                        getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR)
                    eyesColor =
                        getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR)
                    mouthColor =
                        getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
                    borderColor =
                        getColor(R.styleable.EmotionalFaceView_borderColor, DEFAULT_BORDER_COLOR)
                    borderWidth = getDimension(
                        R.styleable.EmotionalFaceView_borderWidth,
                        DEFAULT_BORDER_WIDTH
                    )
                    happinessState =
                        getInt(R.styleable.EmotionalFaceView_state, HAPPY.toInt()).toLong()
                } finally {
                    recycle()
                }

            }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
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

        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)

        canvas.drawOval(leftEyeRect, paint)

        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)


    }

    private fun drawMouth(canvas: Canvas) {
        paint.color = mouthColor
        paint.style = Paint.Style.FILL

        mouthPath.moveTo(size * 0.22f, size * 0.70f)
        mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f)
        mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f)

        canvas.drawPath(mouthPath, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = Math.min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    companion object {
        const val DEFAULT_FACE_COLOR = Color.YELLOW
        const val DEFAULT_EYES_COLOR = Color.BLACK
        const val DEFAULT_MOUTH_COLOR = Color.BLACK
        const val DEFAULT_BORDER_COLOR = Color.BLACK
        const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L

    }

}