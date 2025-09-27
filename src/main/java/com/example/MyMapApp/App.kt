package com.example.MyMapApp

import android.app.Application
import ru.dgis.sdk.DGis
import ru.dgis.sdk.Context as DgisContext

import ru.dgis.sdk.PersonalDataCollectionConsent
import ru.dgis.sdk.platform.HttpOptions
import ru.dgis.sdk.platform.KeyFromAsset
import ru.dgis.sdk.platform.KeyFromString
import ru.dgis.sdk.platform.KeySource
import ru.dgis.sdk.platform.LogLevel
import ru.dgis.sdk.platform.LogOptions
import ru.dgis.sdk.positioning.DefaultLocationSource
import ru.dgis.sdk.positioning.LocationSource
import ru.dgis.sdk.positioning.registerPlatformLocationSource



class App : Application() {




    lateinit var locationProvider: DefaultLocationSource
        private set

    lateinit var sdkContext: DgisContext
        private set

    override fun onCreate() {
        super.onCreate()

        // Настройки журналирования
        val logOptions = LogOptions(
            LogLevel.VERBOSE
        )

// Настройки HTTP-клиента
        val httpOptions = HttpOptions(
            useCache = false
        )

// Согласие на сбор и отправку персональных данных
        val dataCollectConsent = PersonalDataCollectionConsent.GRANTED
        val keySource = KeySource(KeyFromAsset("dgissdk.key"))
        sdkContext = DGis.initialize(
            appContext = this,
            dataCollectConsent = dataCollectConsent,
            logOptions = logOptions,
            httpOptions = httpOptions,
            keySource = keySource
        )

        // Создание и регистрация в SDK источника данных о текущей геопозиции
        locationProvider = DefaultLocationSource(applicationContext)
        registerPlatformLocationSource(sdkContext, locationProvider)
    }
}