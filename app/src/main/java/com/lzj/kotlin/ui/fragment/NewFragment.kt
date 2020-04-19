package com.lzj.kotlin.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.lzj.kotlin.R
import kotlinx.android.synthetic.main.fragment_new.*

/**
 * Description: 作用描述
 * Author: Lzj
 * CreateDate: 2020/4/19
 */
class NewFragment : Fragment() {
    private var index_fragment = 0
    private var index_subject: String? = null
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            index_fragment = arguments!!.getInt("indexFragment")
            index_subject = arguments!!.getString("subject")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new, container, false)
        var subject =view.findViewById(R.id.tvSubject) as TextView
        init(subject)
        return view
    }

    private fun init(subject :TextView) {
        subject.text=index_subject
        Log.i("test","-------------"+index_subject)
    }

    companion object {
        fun newInstance(
            subject: String?,
            indexFragment: Int
        ): NewFragment {
            val fragment = NewFragment()
            val bundle = Bundle()
            bundle.putInt("indexFragment", indexFragment)
            bundle.putString("subject", subject)
            fragment.arguments = bundle
            return fragment
        }
    }
}