package com.changjiashuai.demo

import kotlin.concurrent.thread

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-04-30.
 */
class TraditionalThread {

    fun newThread() {
        Thread {
            while (true) {
                println("newThread name = ${Thread.currentThread().name}")
            }
        }.start()
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            println("e=${e.message}")
        }
    }

    fun newRunnable() {
        val runnable = Runnable {
            println("newRunnable name = ${Thread.currentThread().name}")
        }
        Thread(runnable).start()
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            println("e=${e.message}")
        }
    }

    fun newThreadByKotlinExt(){
        thread {
//            Thread.sleep(500) //放在这里，导致主线程退出，改线程还未执行，所以看不到打印
            println("newThreadByKotlinExt name = ${Thread.currentThread().name}")
        }
    }
}