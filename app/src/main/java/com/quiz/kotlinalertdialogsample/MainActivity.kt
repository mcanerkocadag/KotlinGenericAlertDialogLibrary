package com.quiz.kotlinalertdialogsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.quiz.genericalertdialoglibrary.GenericAlertDialog
import com.quiz.genericalertdialoglibrary.enums.AlertStatus
import com.quiz.kotlinalertdialogsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.defaultBtnBtn.setOnClickListener {
            GenericAlertDialog
                .cancelable(true)
                .show(this)

        }

        binding.oneactionBtn.setOnClickListener {
            GenericAlertDialog.show(this, AlertStatus.ONEACTION, "Welcome")
        }

        binding.twoactionBtn.setOnClickListener {
            GenericAlertDialog.show(
                this,
                AlertStatus.TWOACTION,
                null,
                "Welcome",
                "Ok Button",
                "No Button"
            )
        }

        binding.threeactionBtn.setOnClickListener {
            GenericAlertDialog.show(
                this,
                AlertStatus.THREEACTION,
                null,
                "Welcome",
                "Ok Button",
                "No Button",
                "Cancel Button"
            )
        }

        binding.actionClickBtn.setOnClickListener {
            GenericAlertDialog.show(
                this,
                AlertStatus.THREEACTION,
                object : GenericAlertDialog.Companion.DialogListener {
                    override fun onPositiveButton(dialog: AlertDialog) {
                        Toast.makeText(this@MainActivity, "Ok button click !", Toast.LENGTH_LONG)
                            .show()
                    }
                },
                "Welcome",
                "Ok Button",
                "No Button",
                "Cancel Button"
            )
        }
    }
}
