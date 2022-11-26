package com.simplemobiletools.calendar.pro

import androidx.multidex.MultiDexApplication
import com.pointzi.Pointzi
import com.simplemobiletools.commons.extensions.checkUseEnglish

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        checkUseEnglish()
        Pointzi.setUserId("danielquah148@yahoo.com")
    }
}
