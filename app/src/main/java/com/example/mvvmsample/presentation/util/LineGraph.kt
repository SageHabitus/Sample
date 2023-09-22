package com.example.mvvmsample.presentation.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.mvvmsample.presentation.uistate.CurrencyItemUiState

class LineGraph @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val datas = mutableListOf<CurrencyItemUiState>()

    private val paint = Paint().apply {
        color = Color.WHITE
        strokeWidth = 2f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    fun setData(uiStates: List<CurrencyItemUiState>) {
        this.datas.clear()
        this.datas.addAll(uiStates)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (datas.isEmpty()) return

        val xInterval = width.toFloat() / (datas.size - 1)

        // Y축 최대값과 최소값을 계산
        var minY = datas.minByOrNull { it.tradeStanRate.toFloat() }?.tradeStanRate?.toFloat() ?: 0f
        var maxY = datas.maxByOrNull { it.tradeStanRate.toFloat() }?.tradeStanRate?.toFloat() ?: 0f

        if (minY == maxY) {
            minY -= 1f
            maxY += 1f
        }

        // 데이터를 그래프로 표시
        for (i in 0 until datas.size - 1) {
            val startX = i * xInterval
            val stopX = (i + 1) * xInterval

            // Y 값은 비율에 따라 조정
            val startY =
                height - ((datas[i].tradeStanRate.toFloat() - minY) / (maxY - minY)) * height

            val stopY =
                height - ((datas[i + 1].tradeStanRate.toFloat() - minY) / (maxY - minY)) * height

            canvas?.drawLine(startX, startY, stopX, stopY, paint)
        }
    }
}