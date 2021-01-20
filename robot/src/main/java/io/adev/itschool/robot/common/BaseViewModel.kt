package io.adev.itschool.robot.common

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import summer.SummerViewModel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<TView> : SummerViewModel<TView>(), BaseViewModelController,
    CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job +
            CoroutineExceptionHandler { _, e ->
                onFailure(e)
            }

    open fun onFailure(e: Throwable) {
        Log.e(this::class.simpleName, Log.getStackTraceString(e))
    }

    override fun onDestroy() {
        job.cancel()
    }

    open fun onBackClick(): Boolean = false
}

interface BaseViewModelController {
    fun onDestroy()
}