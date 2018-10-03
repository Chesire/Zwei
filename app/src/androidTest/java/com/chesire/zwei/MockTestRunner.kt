package com.chesire.zwei

import android.app.Application
import android.content.Context
import com.github.tmurakami.dexopener.DexOpenerAndroidJUnitRunner

@Suppress("unused")
class MockTestRunner : DexOpenerAndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, "com.chesire.zwei.MockApplication", context)
    }
}