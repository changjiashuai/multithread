package com.changjiashuai.demo

import java.util.*
import kotlin.concurrent.timerTask

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-04-30.
 */
class TraditionalTimer {

    fun newTimer() {
        Timer().schedule(timerTask {
            println("timer output")
        }, 100, 100)
        Thread.sleep(500)
    }
}