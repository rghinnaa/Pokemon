plugins {
    id(Dependencies.Plugins.application)
    id(Dependencies.Plugins.kotlinJetbrains)
    id(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.kotlinKapt)
    id(Dependencies.Plugins.navSafeArgs)
    id(Dependencies.Plugins.googleGms)
    id(Dependencies.Plugins.firebaseCrashlytics)
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.assignment.pokemon"
        versionCode = 1
        versionName = "1.0"

        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.guava)
    implementation(Dependencies.sdp)
    implementation(Dependencies.glide)

    Dependencies.Network.networkDependencies.forEach { dependency ->
        implementation(dependency)
    }

    Dependencies.JetPack.jetPackDependencies.forEach { dependency ->
        implementation(dependency)
    }

    Dependencies.Firebase.firebaseDependencies.forEach { dependency ->
        implementation(dependency)
    }

    Dependencies.Kotlin.kotlinDependencies.forEach { dependency ->
        implementation(dependency)
    }

    implementation(platform(Dependencies.Firebase.bom))

    Dependencies.Kapt.kaptImp.forEach { kaptImp ->
        kapt(kaptImp)
    }

    testImplementation(Dependencies.Testing.jUnit)
    androidTestImplementation(Dependencies.Testing.jUnitTest)
    androidTestImplementation(Dependencies.Testing.espresso)

}