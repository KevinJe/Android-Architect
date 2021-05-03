package com.kevin.android.android.architect.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kevin.android.android.architect.R
import com.kevin.android.android.hilibrary.log.*

class HiLogDemoActivity : AppCompatActivity() {
    var viewPrinter: HiViewPrinter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_log_demo)
        viewPrinter = HiViewPrinter(this)
        findViewById<Button>(R.id.btn_log).setOnClickListener {
            printLog()
        }
        viewPrinter!!.viewPrinterProvider.showFloatingView()
    }

    private fun printLog() {
        HiLogManager.getInstance().addPrinter(viewPrinter)
        HiLog.log(object : HiLogConfig() {
            override fun includeThread(): Boolean {
                return true
            }
        }, HiLogType.E, "------", "5566")
//        HiLog.a("9999")
    }
}