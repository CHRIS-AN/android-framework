package com.example.android.trackmysleepquality.coroutine_example

import android.util.Log
import kotlinx.coroutines.*

object Coroutine {
    fun exampleSuspend() {

        val job3 = CoroutineScope(Dispatchers.IO).async {
            // 2. IO Thread 에서 작업3을 수행한다.
            (1..10000).sortedDescending()
            // 5. 작업 3이 완료 됨
        }

        val job1 = CoroutineScope(Dispatchers.Main).launch {
            // 1. Main Thread 에서 작업1을 수행
            Log.d("flow", "Job1 수행시작")

            // 3. 작업1의 남은 작업을 위해 작업 3으로부터 결과 값이 필요하기 때문에 Main Thread 작업 1을 중단.
            val job3Result = job3.await()
            // 6. 작업 3으로부터 결과를 전달 받는다.

            // 7. 작업 1이 재개 된다.
            job3Result.forEach {
                Log.d("flow", "$it")
            }
            Log.d("flow", "Job3 수행완료")
            Log.d("flow", "Job1 수행완료")
        }

        val job2 = CoroutineScope(Dispatchers.Main).launch {
            Log.d("flow", "Job2 수행완료")
        }
    }
}