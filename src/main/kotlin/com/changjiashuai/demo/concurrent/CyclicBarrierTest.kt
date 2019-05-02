package com.changjiashuai.demo.concurrent

import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors
import kotlin.random.Random

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-05-02.
 */
class CyclicBarrierTest {

    fun test() {
        val service = Executors.newCachedThreadPool()
        val cyclicBarrier = CyclicBarrier(3)
        for (i in 0 until 3) {
            val runnable = Runnable {
                Thread.sleep(Random.nextLong(1000))
                var numberWaiting = cyclicBarrier.numberWaiting + 1
                println("线程 ${Thread.currentThread().name} 即将到达集合点1， 当前已有 $numberWaiting 个已经到达， ${if (numberWaiting == 3) "都到齐了，继续走啊" else "正在等候"}")
                cyclicBarrier.await()

                Thread.sleep(Random.nextLong(1000))
                numberWaiting = cyclicBarrier.numberWaiting + 1
                println("线程 ${Thread.currentThread().name} 即将到达集合点2， 当前已有 $numberWaiting 个已经到达， ${if (numberWaiting == 3) "都到齐了，继续走啊" else "正在等候"}")
                cyclicBarrier.await()


                Thread.sleep(Random.nextLong(1000))
                numberWaiting = cyclicBarrier.numberWaiting + 1
                println("线程 ${Thread.currentThread().name} 即将到达集合点3， 当前已有 $numberWaiting 个已经到达， ${if (numberWaiting == 3) "都到齐了，继续走啊" else "正在等候"}")
                cyclicBarrier.await()
            }
            service.execute(runnable)
        }
        service.shutdown()
    }
}

fun main() {
    CyclicBarrierTest().test()
}