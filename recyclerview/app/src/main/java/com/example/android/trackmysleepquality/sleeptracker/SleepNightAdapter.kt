package com.example.android.trackmysleepquality.sleeptracker

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<SleepNight>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()


        // 뷰 홀더 재사용 함에 따라서, 결국 고품질 등급을 위해 빨간색 뷰 홀더 중 하나를 재사용하게 됩니다.
        // 그럴 때, 높은 등급이 빨간색으로 잘못 표시되는 경우가 생깁니다.
        if (item.sleepQuality <= 1) {
            holder.textView.setTextColor(Color.RED)
        }

        else {
            // reset
            // else 아래 구문을 작성해야, 두 조건이 모두 명시되어 있는 뷰 홀더를 각각 올바르게 텍스트 색상을 사용합니다.
            holder.textView.setTextColor(Color.BLACK)
        }
    }

    // 수면 night 목록의 크기를 반환하도록 재정의
    // 어댑터가 표시할 수 있는 항목 수를 알아야 함.
    override fun getItemCount() = data.size

}