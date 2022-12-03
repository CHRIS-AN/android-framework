package com.chrisan.snackbar_toast_diff

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.chrisan.snackbar_toast_diff.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** 1. 기본 다이얼로그 **/
        var normalListener = DialogInterface.OnClickListener { _, p1 ->
            when (p1) {
                DialogInterface.BUTTON_POSITIVE ->
                    binding.tvResult.text = "BUTTON_POSITIVE"
                DialogInterface.BUTTON_NEUTRAL ->
                    binding.tvResult.text = "BUTTON_NEUTRAL"
                DialogInterface.BUTTON_NEGATIVE ->
                    binding.tvResult.text = "BUTTON_NEGATIVE"
            }
        }
//
//        binding.btnDialog.setOnClickListener {
//            AlertDialog.Builder(this)
//                .setTitle("Title")
//                .setMessage("Content")
//                .setPositiveButton("confirm", normalListener)
//                .setNegativeButton("cancel", normalListener)
//                .setNeutralButton("retry", normalListener)
//                .show()
//        }


        /** 2. 커스텀 다이얼로그 **/
//        binding.btnDialog.setOnClickListener {
//            var builder = AlertDialog.Builder(this)
//            builder.setTitle("커스텀 다이얼로그")
//
//            var v1 = layoutInflater.inflate(R.layout.custom_dialog, null)
//            builder.setView(v1)
//
//            var customListener = DialogInterface.OnClickListener { p0, p1 ->
//                var alert = p0 as AlertDialog
//                var firstName: EditText? = alert.findViewById<EditText>(R.id.editText)
//                var lastName: EditText? = alert.findViewById<EditText>(R.id.editText2)
//
//                binding.tvResult.text = "${firstName?.text}"
//                binding.tvResult.append("${lastName?.text}")
//            }
//
//            builder.setPositiveButton("확인", customListener)
//            builder.setNegativeButton("취소", null)
//            builder.show()
//        }

        /** 3. 날짜 다이얼로그 **/
//        binding.btnDialog.setOnClickListener {
//            var calendar = Calendar.getInstance()
//            val year = calendar.get(Calendar.YEAR)
//            val month = calendar.get(Calendar.MONTH)
//            val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//            val calendarListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
//                binding.tvResult.text = "${year}년 ${month + 1} 월 ${day}일"
//            }
//
//            val picker = DatePickerDialog(this, calendarListener, year, month, day)
//            picker.show()
//        }

        /** 4. 시간 다이얼로그 **/
//        binding.btnDialog.setOnClickListener {
//            val calendar = Calendar.getInstance()
//            val hour = calendar.get(Calendar.HOUR)
//            val minute = calendar.get(Calendar.MINUTE)
//
//            val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
//                binding.tvResult.text = "${hour}시 ${minute}분"
//            }
//
//            val picker = TimePickerDialog(this, timeListener, hour ,minute, false) /* true 시, 24 시간제*/
//            picker.show()
//        }

        /** Toast **/
        /*
            Toast.LENGTH_SHORT : 2초
            Toast.LENGTH_LONG : 3.5초
        */
        binding.btnToast.setOnClickListener {
            val context = "토스트 입니다."
            Toast.makeText(this, context, Toast.LENGTH_SHORT).show()
        }

        /** 1. 기본 스낵바 **/
//        binding.btnSnackBar.setOnClickListener {
//            val snack = Snackbar.make(it, "기본 스낵바", Snackbar.LENGTH_SHORT)
//            snack.setTextColor(Color.WHITE)
//            snack.setBackgroundTint(Color.BLACK)
//            snack.animationMode = Snackbar.ANIMATION_MODE_FADE
//
//            /** +추가 Callback **/
//            val callback = object: BaseTransientBottomBar.BaseCallback<Snackbar>() {
//                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
//                    super.onDismissed(transientBottomBar, event)
//
//                    binding.tvResult.text = "SnackBar 가 사라졌습니다."
//                }
//
//                override fun onShown(transientBottomBar: Snackbar?) {
//                    super.onShown(transientBottomBar)
//
//                    binding.tvResult.text = "SnackBar 가 나타났습니다."
//                }
//            }
//
//            snack.addCallback(callback)
//            snack.show()
//        }

        /** 2. 커스텀 스낵바 **/
//        binding.btnSnackBar.setOnClickListener {
//            val snack = Snackbar.make(it, "커스텀 스낵바", Snackbar.LENGTH_SHORT)
//
//            val snackView = LayoutInflater.from(this).inflate(R.layout.custom_snackbar, null)
//            val snackBarLayout = snack.view as Snackbar.SnackbarLayout
//            snackBarLayout.addView(snackView)
//
//            val snackText = snackBarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
//            snackText.visibility = View.INVISIBLE
//
//            snack.show()
//        }

        /** 3. 커스텀 스낵바 **/
        binding.btnSnackBar.setOnClickListener {
            val snack = Snackbar.make(it, "커스텀 스낵바", Snackbar.LENGTH_INDEFINITE)
            snack.setAction("Click") {
                Toast.makeText(this, "확인 클릭 됨!", Toast.LENGTH_SHORT).show()
            }
            snack.show()
        }

    }
}