package com.changjiashuai.demo.concurrent

import java.util.concurrent.CountDownLatch

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-05-02.
 */
class CountDownLatchTest {

    fun test() {
        val startSignal = CountDownLatch(1)
        val doneSignal = CountDownLatch(3)
        for (i in 0 until 3){
            Thread(Worker(startSignal, doneSignal)).start()
        }
        println("don't let run yet")
        startSignal.countDown()
        println("all worker start work")
        doneSignal.await()
        println("all work done.")
    }
}

class Worker(
    private val startSignal: CountDownLatch,
    private val doneSignal: CountDownLatch
) : Runnable {

    override fun run() {
        startSignal.await()
        doWork()
        doneSignal.countDown()
    }

    private fun doWork() {
        println("do work something...")
    }
}

fun main(){
    CountDownLatchTest().test()
}