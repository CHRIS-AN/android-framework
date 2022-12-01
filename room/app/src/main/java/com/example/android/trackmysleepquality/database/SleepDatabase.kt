package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
    스키마 버전 기록 백업을 유지하지 않으려면 로 설정 exportSchema 를 false,
    스키마 버전은 1부터 스키마를 변경할 때마다 1씩 up
 */
@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {

    abstract val sleepDatabaseDao: SleepDatabaseDao

    companion object {

        /**
            @Volatile 은 휘발성 변수의 값이 캐시되지 않으며 모든 쓰기 및 읽기는 주 메모리에서 수행됩니다.
            INSTANCE 값이 항상 최신 상태이고 모든 실행 스레드에 대해 동일한지 확인하는 데 도움이 됩니다.
            즉, 한 스레드에서 변경한 내용을 INSTANCE다른 모든 스레드에서 즉시 볼 수 있으며,
            두 스레드가 각각 캐시에서 동일한 엔터티를 업데이트하여 문제를 일으키는 상황이 발생하지 않습니다.
         */
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getInstance(context: Context): SleepDatabase {
            /** synchronized 는 한 번에 하나의 실행 스레드만 이 코드 블록에 들어갈 수 있으므로 데이터베이스가 한 번만 초기화됩니다. */
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) { /** 이렇게 하면 계산 비용이 많이 드는 데이터베이스에 대한 연결을 반복적으로 여는 것을 방지할 수 있습니다 */
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}