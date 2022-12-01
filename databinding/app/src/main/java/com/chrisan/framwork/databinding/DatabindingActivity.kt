package com.chrisan.framwork.databinding

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.chrisan.framwork.R

class DatabindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDatabindingBinding
    private val myName: MyName = MyName("Chris An")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding) /* 바인딩 개체 생성 */
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

        binding.myName = myName
    }

    /**
     * Click handler for the Done button.
     */
    private fun addNickname(view: View) {
        binding.apply {
            myName?.nickname = binding.nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}