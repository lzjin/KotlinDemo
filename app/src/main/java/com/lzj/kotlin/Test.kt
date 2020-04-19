package com.lzj.kotlin

/**
 * Description: 作用描述
 * Author: Lzj
 * CreateDate: 2020/4/19
 */
class Test {
    private fun ms(k: Int) {
        for (i in 0..19) {
            if (k == i) {
                return
            }
        }
    }
}