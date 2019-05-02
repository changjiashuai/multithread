package com.changjiashuai.demo.concurrent

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-05-02.
 */
class ConditionTest {
}

fun main() {
    val boundedBuffer = BoundedBuffer()
    for (i in 0 until 5) {
        thread {
            if (i % 2 == 0) {
                for (j in 0 until 200) {
                    boundedBuffer.put("data-$j")
                }
            } else {
                for (j in 0 until 200) {
                    println("$i = ${boundedBuffer.take()}")
                }
            }
        }
    }
}

class BoundedBuffer {
    private val lock = ReentrantLock()
    private val notFull = lock.newCondition()
    private val notEmpty = lock.newCondition()

    private val items = arrayOfNulls<Any>(100)
    private var putptr: Int = 0
    private var takeptr: Int = 0
    private var count: Int = 0

    fun put(obj: Any) {
        lock.lock()
        try {
            while (count == items.size) {
                //已满，等待
                notFull.await()
            }
            items[putptr] = obj
            putptr += 1
            if (putptr == items.size) {
                putptr = 0
            }
            count += 1
            //通知非空，可取
            notEmpty.signal()
        } finally {
            lock.unlock()
        }
    }

    fun take(): Any? {
        lock.lock()
        try {
            while (count == 0) {
                //已空，等待
                notEmpty.await()
            }
            val obj: Any? = items[takeptr]
            takeptr += 1
            if (takeptr == items.size) {
                takeptr = 0
            }
            count -= 1
            //通知未满，可放
            notFull.signal()
            return obj
        } finally {
            lock.unlock()
        }
    }
}