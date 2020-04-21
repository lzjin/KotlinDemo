package com.lzj.kotlin.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.lzj.kotlin.R
import com.lzj.kotlin.adapter.TabLayoutStatePagerAdapter
import com.lzj.kotlin.custemview.ColorFlipPagerTitleView
import kotlinx.android.synthetic.main.fragment_home.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

import kotlinx.android.synthetic.main.fragment_home.view.*
import net.lucode.hackware.magicindicator.MagicIndicator

/**
 * Description: 作用描述
 * Author: Administrator
 * CreateDate: 2020/4/7
 *
 */
class HomeFragment : Fragment() {
   // val instance by lazy { activity } //这里使用了委托，表示只有使用到instance才会执行该段代码
  //  private val fragmentActicity : Fragment = activity
    private var mTitleList: List<String>? = null
    private var fragments:  List<Fragment>? = null
    private var mAdapter: TabLayoutStatePagerAdapter? = null
    private var index_fragment = 0
    private var index_subject: String? = null
    private var mViewPager: ViewPager? = null
    private var mIndicatorTitle : MagicIndicator? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView=inflater.inflate(R.layout.fragment_home, container, false)
        mViewPager =mView.findViewById(R.id.viewPager) as ViewPager
        mIndicatorTitle=mView.findViewById(R.id.indicatorTitle) as MagicIndicator
        initAll()
        return  mView
    }

    private fun initAll() {
        initData()
        initFragment()
        initFragmentTitle()
          var industryList: ArrayList<String> = arrayListOf()
    }

    companion object {
        @Volatile
        var instance: HomeFragment? = null
            get() {
                if (field == null) {
                    synchronized(HomeFragment::class.java) {
                        if (field == null) {
                            field = HomeFragment()
                        }
                    }
                }
                return field
            }
            private set
    }


    /**
     * 初始化Title
     */
    private fun initData() {
         mTitleList = arrayListOf()

        (mTitleList as ArrayList<String>).add("数学")
        (mTitleList as ArrayList<String>).add("英语")
        (mTitleList as ArrayList<String>).add("物理")
        (mTitleList as ArrayList<String>).add("化学")
        (mTitleList as ArrayList<String>).add("生物")
        (mTitleList as ArrayList<String>).add("历史")
        (mTitleList as ArrayList<String>).add("政治")
        initFragment()
        initFragmentTitle()

    }

    /**
     * 初始化 Fragment
     *
     */
    private fun initFragment() {
        fragments = ArrayList()
        for (i in mTitleList!!.indices) {
            val fragment: NewFragment = NewFragment.newInstance(mTitleList!![i], i)
            (fragments as ArrayList<Fragment>).add(fragment)
        }
        index_subject = mTitleList!![0]
        mAdapter = TabLayoutStatePagerAdapter(
            childFragmentManager, fragments,
            mTitleList!!
        )

        mViewPager!!.setAdapter(mAdapter)
        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }
            override fun onPageSelected(position: Int) {
                index_fragment = position
                index_subject = mTitleList!![position]
            }
        })
    }

    /**
     * 初始化 指示器
     */
    private fun initFragmentTitle() {
        val commonNavigator = CommonNavigator(activity)
        commonNavigator.scrollPivotX = 0.65f
        commonNavigator.isAdjustMode = true //是否均分长度
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return mTitleList?.size ?: 0
            }
            //字体样式 重写SimplePagerTitleView
            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView: SimplePagerTitleView = ColorFlipPagerTitleView(context)
                simplePagerTitleView.text = mTitleList!![index]

                activity?.let {
                    simplePagerTitleView.normalColor = ContextCompat.getColor(it, R.color.colorAccent)
                    simplePagerTitleView.selectedColor = ContextCompat.getColor(it, R.color.gray42)
                }
                simplePagerTitleView.textSize = 16f
                simplePagerTitleView.setOnClickListener {
                    mViewPager!!.setCurrentItem(index)
                    index_fragment = index
                    index_subject = mTitleList!![index]
                }
                return simplePagerTitleView
            }

            //下划线
            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                //设置模式 下划线为精确指定长度
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.lineHeight = UIUtil.dip2px(context, 2.0).toFloat()
                indicator.lineWidth = UIUtil.dip2px(context, 20.0).toFloat()
                indicator.roundRadius = UIUtil.dip2px(context, 3.0).toFloat()
                indicator.startInterpolator = AccelerateInterpolator()
                indicator.endInterpolator = DecelerateInterpolator(2.0f)
                activity?.let {
                    indicator.setColors(ContextCompat.getColor(it, R.color.rede04))
                }
                return indicator
            }
        }
        mIndicatorTitle!!.setNavigator(commonNavigator)
        ViewPagerHelper.bind(mIndicatorTitle, mViewPager)
    }
}