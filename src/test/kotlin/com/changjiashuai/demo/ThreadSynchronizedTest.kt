package com.changjiashuai.demo

import org.junit.Before
import org.junit.Test
import kotlin.concurrent.thread

/**
 * changjiashuai@gmail.com.
 *
 *
 * Created by CJS on 2019-04-30.
 */
class ThreadSynchronizedTest {

    private lateinit var threadSynchronized: ThreadSynchronized

    @Before
    fun setUp() {
        threadSynchronized = ThreadSynchronized()
    }

    @Test
    fun testNonSync() {
        testMethod({ threadSynchronized.output("AAAAxxxxxxx") }, { threadSynchronized.output("BBBBwwwwwwww") })
    }

    @Test
    fun testSync() {
        testMethod({ threadSynchronized.output2("AAAA") }, { threadSynchronized.output2("BBBB") })
    }

    @Test
    fun testSyncMethod() {
        thread {
            while (true) {
                threadSynchronized.output3("AAAA")
            }
        }
        thread {
            while (true){
                threadSynchronized.output3("QQQQ")
            }
        }
        Thread.sleep(50)
    }

    private fun testMethod(m1: ((String) -> Unit), m2: ((String) -> Unit)) {
        thread {
            while (true) {
                m1("AAAA")
            }
        }
        thread {
            while (true) {
                m2("BBBB")
            }
        }
        Thread.sleep(50)
    }

    @Test
    fun testAlternate(){
        val alternate = Alternate()
        thread {
            for (i in 0 until 4){
                alternate.sub()
            }
        }
        for (i in 0 until 4){
            alternate.main()
        }
        Thread.sleep(1000)
    }
}