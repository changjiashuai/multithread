package com.changjiashuai.demo.concurrent

import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.thread

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-05-02.
 */
class ThreadLock {

    private val lock = ReentrantLock()

    fun output(name: String) {
        lock.lock()
        try {
            name.forEach { c ->
                print("$c")
            }
            println()
        } finally {
            lock.unlock()
        }
    }
}

class Cache {

    private val map = hashMapOf<String, Any>()
    private val rw1 = ReentrantReadWriteLock()

    fun getData(key: String): Any? {
        var value: Any? = null
        try {
            rw1.readLock().lock()
            value = map[key]
            if (value == null) {
                try {
                    rw1.readLock().unlock()
                    rw1.writeLock().lock()//多个线程同时进入到这里，
                    // 其中一个线程写完，
                    // 释放写锁时，其他线程继续执行，需要判读是否已经写入值了
                    if (value == null) {
                        value = "write data"
                        map[key] = value
                    }
                } finally {
                    rw1.writeLock().unlock()
                    rw1.readLock().lock()
                }
            }
        } finally {
            rw1.readLock().unlock()
        }
        return value
    }
}

fun main() {
//    val threadLock = ThreadLock()
//    Thread(Runnable { threadLock.output("Hello World") }).start()
//    Thread(Runnable { threadLock.output("你好啊") }).start()
    val cache = Cache()
    for (i in 0 until 10) {
        thread { println("$i result: ${cache.getData("x")}") }
    }
}