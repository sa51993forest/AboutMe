package com.example.aboutme

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var myName: MyName = MyName("Timmy","boy")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding效果等同findViewById但不會花那麼多的ＣＯＳＴ
        binding.doneButton.setOnClickListener() {
            addNickname(it) //it代表button
        }

        binding.nicknameText.setOnClickListener {
            updateNickname(it)
        }

        binding.myName = myName
    }

    private fun addNickname(view: View){
        //上面兩種findViewById結果皆相同
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            //invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE //因為使用button參數呼叫fun view直接代表button
            nicknameText.visibility = View.VISIBLE
        }
        // 隱藏鍵盤
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View){
        val editText = binding.nicknameEdit
        val doneButton = binding.doneButton

        binding.nicknameEdit.visibility = View.VISIBLE
        binding.doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Set the focus to the edit text.
        editText.requestFocus()

        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}