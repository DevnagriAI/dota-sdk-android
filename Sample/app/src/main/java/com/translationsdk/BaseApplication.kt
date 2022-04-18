package com.translationsdk

import android.app.Application
import com.devnagritranslationsdk.DevNagriTranslationSdk


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val strings=R.string::class.java.fields.map { it.name }
        val arrays=R.array::class.java.fields.map { it.name }


        val API_KEY="devnagri_eb93ac18b65d11ecb762021b05a03360"
        DevNagriTranslationSdk.init(applicationContext,API_KEY,strings,arrays)


    }

}