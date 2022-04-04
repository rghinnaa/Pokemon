object Dependencies {

    object Plugins {
        const val application = "com.android.application"
        const val kotlinJetbrains = "org.jetbrains.kotlin.android"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinKapt = "kotlin-kapt"
        const val navSafeArgs = "androidx.navigation.safeargs"
        const val googleGms = "com.google.gms.google-services"
        const val firebaseCrashlytics = "com.google.firebase.crashlytics"
    }

    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val guava = "com.google.guava:guava:${Version.guava}"
    const val sdp = "com.intuit.sdp:sdp-android:${Version.sdp}"
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"

    object Network {
        const val koin = "io.insert-koin:koin-android:${Version.koin}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"

        val networkDependencies = mutableListOf(koin, retrofit, gson, okhttp, interceptor)
    }

    object JetPack {
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
        const val common = "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
        const val roomKtx = "androidx.room:room-ktx:${Version.room}"
        const val roomRuntime = "androidx.room:room-runtime:${Version.room}"
        const val roomPaging = "androidx.room:room-paging:${Version.room}"
        const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navRuntime = "androidx.navigation:navigation-runtime-ktx:${Version.navigation}"
        const val navUI = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefresh}"
        const val palette = "androidx.palette:palette:${Version.palette}"
        const val paging = "androidx.paging:paging-runtime:${Version.paging}"

        val jetPackDependencies = mutableListOf(
            runtime, viewModel, common, liveData, roomKtx, roomRuntime, roomPaging, navFragment,
            navRuntime, navUI, swipeRefresh, palette, paging
        )
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:${Version.firebaseBom}"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"

        val firebaseDependencies = mutableListOf(analytics, crashlytics)
    }

    object Kotlin {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
        const val playService = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.coroutines}"

        val kotlinDependencies = mutableListOf(core, android, playService)
    }

    object Testing {
        const val jUnit = "junit:junit:${Version.jUnit}"
        const val jUnitTest = "androidx.test.ext:junit:${Version.testJUnit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
    }

    object Kapt {
        const val glide = "com.github.bumptech.glide:compiler:${Version.glide}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.room}"

        val kaptImp = mutableListOf(glide, roomCompiler)
    }

}