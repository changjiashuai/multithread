package com.changjiashuai.demo.concurrent

import java.util.concurrent.Exchanger
import kotlin.random.Random

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-05-02.
 */
class ExchangerTest {

    fun test() {
        val exchanger = Exchanger<ArrayList<Int>>()
        val initialEmptyList = arrayListOf(1)
        val initialFullList = arrayListOf(1)
        Thread(FillingLoop(exchanger, initialFullList)).start()
        Thread(EmptyingLoop(exchanger, initialEmptyList)).start()
    }
}

class FillingLoop(
    private val exchanger: Exchanger<ArrayList<Int>>,
    private var currentList: ArrayList<Int>
) : Runnable {

    override fun run() {
        while (currentList.isNotEmpty()) {
            currentList.add(Random.nextInt(100))
            println("filling list exchange before=$currentList")
            if (currentList.size == 10) {
                currentList = exchanger.exchange(currentList)
                println("filling list exchange after=$currentList")
            }
        }
    }
}

class EmptyingLoop(
    private val exchanger: Exchanger<ArrayList<Int>>,
    private var currentList: ArrayList<Int>
) : Runnable {
    override fun run() {
        while (currentList.isNotEmpty()) {
            currentList.clear()
            println("empty list exchange before=$currentList")
            if (currentList.isEmpty()) {
                currentList = exchanger.exchange(currentList)
                println("empty list exchange after=$currentList")
                break
            }
        }
    }
}

fun main() {
    ExchangerTest().test()
}