package com.changjiashuai.demo.concurrent

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorCompletionService
import java.util.concurrent.Executors
import kotlin.random.Random

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-05-02.
 */
class ThreadPool {

    fun getResultFromGroupTask() {
        val completionService = ExecutorCompletionService<Int>(Executors.newFixedThreadPool(10))
        for (i in 0 until 10) {
            completionService.submit(Callable {
                Thread.sleep(Random.nextLong(2000))
                return@Callable i
            })
        }
        for (i in 0 until 10){
            val result = completionService.take().get()
            println("result: $result")
        }
    }
}

fun main() {
//    //指定线程池中线程的个数
//    val service = Executors.newFixedThreadPool(1)
//    val future: Future<String> = service.submit(Callable {
//        return@Callable "Hello"
//    })
//    println("result: ${future.get()}")
//    //不限线程个数，需要几个生成几个，空闲后一定时间会被回收
//    Executors.newCachedThreadPool()
//    //只会有一个线程，该线程死了，会立即新创建一个
//    Executors.newSingleThreadExecutor()
//    //定时任务线程池
//    Executors.newScheduledThreadPool(1)
    ThreadPool().getResultFromGroupTask()
}