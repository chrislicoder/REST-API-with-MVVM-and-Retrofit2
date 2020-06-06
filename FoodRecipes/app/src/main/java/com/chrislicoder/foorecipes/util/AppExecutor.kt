package com.chrislicoder.foorecipes.util

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutors private constructor() {
    private val mNetworkIO =
        Executors.newScheduledThreadPool(3)

    fun networkIO(): ScheduledExecutorService {
        return mNetworkIO
    }

    companion object {
        val instance by lazy {
            AppExecutors()
        }
    }
}
