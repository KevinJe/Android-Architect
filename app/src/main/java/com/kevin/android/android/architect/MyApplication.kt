package com.kevin.android.android.architect

import android.app.Application
import com.google.gson.Gson
import com.kevin.android.android.hilibrary.log.HiConsolePrinter
import com.kevin.android.android.hilibrary.log.HiLogConfig
import com.kevin.android.android.hilibrary.log.HiLogManager
import com.kevin.android.android.hilibrary.log.HiLogPrinter

/**
Author：kevin
Time：2021/2/14 1:44
Description：
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // 初始化HiLog
        HiLogManager.init(object : HiLogConfig() {
            override fun getGlobalTag(): String {
                return "MyApplication"
            }

            override fun enable(): Boolean {
                return true
            }

            override fun injectJsonParser(): JsonParser {
                return JsonParser { src -> Gson().toJson(src) }
            }

        }, HiConsolePrinter())
    }
}