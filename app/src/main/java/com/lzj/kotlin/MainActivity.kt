package com.lzj.kotlin

import android.os.Bundle
import android.view.KeyEvent
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lzj.kotlin.ui.fragment.HomeFragment
import com.lzj.kotlin.ui.fragment.MyFragment
import com.lzj.kotlin.utils.FragmentUtil
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 主页面
 */
class MainActivity : AppCompatActivity() {
    var homeFragment: HomeFragment = HomeFragment.instance!!
    var myFragment: MyFragment = MyFragment.instance!!
    var indexFragment: Fragment = homeFragment!!
    var mExitTime :Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        FragmentUtil.addFragment(R.id.main_fragment, this, indexFragment)
        rbtHome.isChecked = true
        mainGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbtHome -> {
                    rbtHome.isChecked = true
                    FragmentUtil.setContentFragment(
                        R.id.main_fragment,
                        this@MainActivity,
                        homeFragment,
                        indexFragment
                    )
                    indexFragment = homeFragment
                }
                R.id.rbtMy -> {
                    rbtMy.isChecked = true
                    FragmentUtil.setContentFragment(
                        R.id.main_fragment,
                        this,
                        myFragment, indexFragment
                    )
                    indexFragment = myFragment
                }
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 3000) {
                finish()
               // toast!!.cancel()
            } else {
                mExitTime = System.currentTimeMillis()
              //  toast = showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
