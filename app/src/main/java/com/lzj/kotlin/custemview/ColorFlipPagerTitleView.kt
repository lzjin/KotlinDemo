package com.lzj.kotlin.custemview

import android.content.Context
import android.graphics.Typeface
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 * Created by hackware on 2016/7/24.
 * 指示器 titleView的样式
 * 这里设置 加粗变大
 */
class ColorFlipPagerTitleView(context: Context?) :
    SimplePagerTitleView(context) {
    var changePercent = 0.5f
    override fun onLeave(
        index: Int,
        totalCount: Int,
        leavePercent: Float,
        leftToRight: Boolean
    ) {
        if (leavePercent >= changePercent) {
            setTextColor(mNormalColor)
            //setTypeface(Typeface.DEFAULT); //不加粗2
        } else {
            setTextColor(mSelectedColor)
            //setTypeface(Typeface.DEFAULT_BOLD); //加粗方式2
        }
    }

    override fun onEnter(
        index: Int,
        totalCount: Int,
        enterPercent: Float,
        leftToRight: Boolean
    ) {
        if (enterPercent >= changePercent) {
            setTextColor(mSelectedColor)
            //setTypeface(Typeface.DEFAULT_BOLD); //加粗方式2
        } else {
            setTextColor(mNormalColor)
            //setTypeface(Typeface.DEFAULT); //不加粗2
        }
    }

    /**
     * 选中
     * @param index
     * @param totalCount
     */
    override fun onSelected(index: Int, totalCount: Int) {
        this.textSize = 16f
        this.paint.isFakeBoldText = true //加粗1
        this.typeface = Typeface.DEFAULT_BOLD //加粗方式2
    }

    /**
     * 未选中
     * @param index
     * @param totalCount
     */
    override fun onDeselected(index: Int, totalCount: Int) {
        this.textSize = 15f
        this.paint.isFakeBoldText = false //不加粗1
        this.typeface = Typeface.DEFAULT //不加粗2
    }

}