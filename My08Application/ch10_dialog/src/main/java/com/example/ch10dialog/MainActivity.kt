package com.example.ch10dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.ProgressDialog.show
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ch10dialog.databinding.ActivityMainBinding
import com.example.ch10dialog.databinding.DialogCustomBinding

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?){
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val dialog_binding = DialogCustomBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toastBtn.setOnClickListener{
            showToast("토스트 테스트 중...")
        }

        binding.dateBtn.setOnClickListener{
            DatePickerDialog(this,object:DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                   showToast("${year}년 ${month}월 ${dayOfMonth}일")
                }
            }, 2023, 3, 3).show()
        }

        binding.timeBtn.setOnClickListener{
            TimePickerDialog(this, object:TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    showToast("${hourOfDay}시 ${minute}분")
                }
            }, 15,0,true).show()
        }


        var mycolor = ""
        val alertHandler = object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE -> {
                        Log.d("mobileApp", " DialogInterface.BUTTON_POSITIVE")
                        showToast("${mycolor}이 최종선택되었습니다.")
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        Log.d("mobileApp", " DialogInterface.BUTTON_NEGATIVE")
                    }
                    DialogInterface.BUTTON_NEUTRAL -> {
                        Log.d("mobileApp", " DialogInterface.BUTTON_NEUTRAL")
                    }

                }
            }
        }


        binding.alertBtn.setOnClickListener{
            AlertDialog.Builder(this).run{
                setTitle("테스트 알림창")
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("Ok", alertHandler)
                setNegativeButton("Cancle", alertHandler)
                setNeutralButton("More", alertHandler)
                show()
            }
        }

        val items = arrayOf<String>("빨강", "노랑", "초록", "파랑")
        binding.itemBtn.setOnClickListener{
            AlertDialog.Builder(this).run{
                setTitle("테스트 알림창")
                setItems(items, object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("MobileApp", "${items[which]}색이 선택되었습니다.")
                        mycolor = items[which]
                    }
                })
                setPositiveButton("Yes", alertHandler)
                show()
            }
        }

        binding.singleBtn.setOnClickListener{
            AlertDialog.Builder(this).run{
                setTitle("테스트 알림창")
                setSingleChoiceItems(items, 1, object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("MobileApp", "${items[which]}색이 선택되었습니다.")
                    }
                })
                setPositiveButton("Yes", alertHandler)
                show()
            }
        }

        binding.multiBtn.setOnClickListener{
            AlertDialog.Builder(this).run{
                setTitle("테스트 알림창")
                setMultiChoiceItems(items, booleanArrayOf(true, false, true, false), object:DialogInterface.OnMultiChoiceClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked:Boolean) {
                        if(isChecked)
                            Log.d("MobileApp", "${items[which]}색이 선택되었습니다.")
                        else
                            Log.d("MobileApp", "${items[which]}색이 해제되었습니다.")
                        mycolor = items[which]
                    }
                })
                setPositiveButton("Yes", alertHandler)
                show()
            }
        }

        binding.customBtn.setOnClickListener(){
            AlertDialog.Builder(this).run{
                setTitle("테스트 알림창")
                setView(dialog_binding.root)
                setPositiveButton("Yes", alertHandler)
                show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun showToast(msg:String){
        val t = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        t.addCallback(
            object: Toast.Callback (){
                override fun onToastHidden(){
                    Log.d("mobileApp", "onToastHidden")
                }
                override fun onToastShown(){
                    Log.d("mobileApp", "onToastShown")
                }
            }
        )
        t.show()
        //Toast.makeText(this, "토스트 테스트 중...", Toast.LENGTH_LONG).show()
    }
}