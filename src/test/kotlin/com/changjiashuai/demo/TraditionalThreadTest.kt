package com.changjiashuai.demo

import org.junit.Before
import org.junit.Test

/**
 * changjiashuai@gmail.com.
 *
 *
 * Created by CJS on 2019-04-30.
 */
class TraditionalThreadTest {

    private lateinit var traditionalThread: TraditionalThread

    @Before
    fun setup() {
        traditionalThread = TraditionalThread()
    }

    @Test
    fun testNewThread() {
        traditionalThread.newThread()
    }

    @Test
    fun testNewRunnable() {
        traditionalThread.newRunnable()
    }

    @Test
    fun testNewThreadByKotlinExt() {
        traditionalThread.newThreadByKotlinExt()
    }
}
