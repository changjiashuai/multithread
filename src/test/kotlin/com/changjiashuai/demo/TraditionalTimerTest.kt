package com.changjiashuai.demo

import org.junit.Before
import org.junit.Test

/**
 * changjiashuai@gmail.com.
 *
 *
 * Created by CJS on 2019-04-30.
 */
class TraditionalTimerTest {

    private lateinit var traditionalTimer: TraditionalTimer

    @Before
    fun setUp() {
        traditionalTimer = TraditionalTimer()
    }

    @Test
    fun newTimer() {
        traditionalTimer.newTimer()
    }
}