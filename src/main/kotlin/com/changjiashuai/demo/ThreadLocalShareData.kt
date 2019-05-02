package com.changjiashuai.demo

import com.changjiashuai.demo.ThreadLocalShareData.Companion.data
import kotlin.concurrent.thread
import kotlin.random.Random

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-05-02.
 */

class ThreadLocalShareData {

    companion object {
        var data: Int = 0
    }

    fun setData() {
        for (i in 0..1) {
            thread {
                var data = Random.nextInt()
                ThreadLocalData.getThreadInstance().data = "data-$data"
                println("set data $data from ${Thread.currentThread().name}")
                println(A().toString())
                println(B().toString())
            }
        }
    }
}

class A {
    override fun toString(): String {
        println("${Thread.currentThread().name} data from A by ThreadLocal = ${ThreadLocalData.getThreadInstance().data}")
        return "${Thread.currentThread().name} data from A $data"
    }
}

class B {
    override fun toString(): String {
        println("${Thread.currentThread().name} data from B by ThreadLocal = ${ThreadLocalData.getThreadInstance().data}")
        return "${Thread.currentThread().name} data from B $data"
    }
}

class ThreadLocalData private constructor() {

    var data: String? = null

    companion object {

        private val map = ThreadLocal<ThreadLocalData>()

        fun getThreadInstance(): ThreadLocalData {
            var instance = map.get()
            if (instance == null) {
                instance = ThreadLocalData()
                map.set(instance)
            }
            return instance
        }
    }
}

fun main() {
    ThreadLocalShareData().setData()
}