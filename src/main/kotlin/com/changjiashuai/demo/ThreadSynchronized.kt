package com.changjiashuai.demo

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2019-04-30.
 */
class ThreadSynchronized {

    fun output(name: String) {
        printChar(name)
    }

    fun output2(name: String) {
        synchronized(this) {
            printChar(name)
        }
    }

    @Synchronized
    fun output3(name: String) {
        printChar(name)
    }

    companion object {

        fun output4(name: String) {
            synchronized(ThreadSynchronized::class) {
                printChar(name)
            }
        }

        private fun printChar(name: String) {
            for (str in name) {
                print(str)
            }
            println()
        }
    }
}

class Alternate {

    private var shouldSub = true
    private val lock = Object()

    fun sub() {
        synchronized(lock) {
            while (!shouldSub) {
                lock.wait()
            }
            for (i in 0 until 5) {
                println("sub thread i=$i")
            }
            shouldSub = false
            lock.notifyAll()
        }
    }

    fun main() {
        synchronized(lock) {
            while (shouldSub) {
                lock.wait()
            }
            for (i in 0 until 10) {
                println("main thread i=$i")
            }
            shouldSub = true
            lock.notifyAll()
        }
    }
}