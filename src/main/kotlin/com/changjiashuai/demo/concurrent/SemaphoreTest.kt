package com.changjiashuai.demo.concurrent

import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import kotlin.random.Random

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-05-02.
 */
class SemaphoreTest {

    fun test() {
        val service = Executors.newCachedThreadPool()
        val semaphore = Semaphore(3)
        //10任务排队，当前只能同时3个任务一起工作
        for (i in 0 until 10) {
            val runnable = {
                semaphore.acquire()
                println("线程 ${Thread.currentThread().name} 进入，当前已有 ${3 - semaphore.availablePermits()} 个并发")
                Thread.sleep(Random.nextLong(1000))
                println("线程 ${Thread.currentThread().name} 即将离开")
                semaphore.release()
                println("线程 ${Thread.currentThread().name} 已离开， 当前还有 ${3 - semaphore.availablePermits()} 个并发")
            }
            service.execute(runnable)
        }
    }
}

fun main() {
    SemaphoreTest().test()
}