package com.example.android.trackmysleepquality.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSleepTrackerBinding

class SleepTrackerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_tracker, container, false)

        val application = requireNotNull(this.activity).application

        /** Create an instance of the ViewModel Factory */
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

        /** Get a reference to the ViewModel associated with this fragment */
        // lifecycle-extensions 모듈을 추가하지 않는 방법
        val sleepTrackerViewModel =
                ViewModelProvider(
                    this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        return binding.root
    }
}


/**
    1. ViewModelProvider 를 통해 ViewModel 인스턴스를 요청한다.
    2. ViewModelProvider 내부에서는 ViewModelStoreOwner 를 참조하여 ViewModelStore 를 가져온다.
    3. ViewModelStore 에게 이미 생성된(저장된) ViewModel 인스턴스를 요청한다.
    4. 만약 ViewModelStore 가 적합한 ViewModel 인스턴스를 가지고 있지 않다면,Factory 를 통해 ViewModel 인스턴스를 생성한다.
    5. 생성한 ViewModel 인스턴스를 ViewModeStore 에 저장하고 만들어진 ViewModel 인스턴스를 클라이언트에게 반환한다.
    6. 똑같은 ViewModel 인스턴스 요청이 들어온다면, 1~3번의 과정을 반복하게 된다.
 */
