// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Version.Classpath.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Classpath.kotlin}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Version.Classpath.navVersion}")
        classpath("com.google.gms:google-services:${Version.Classpath.googleGms}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${Version.Classpath.crashlytics}")
    }
}

allprojects {
    repositories {
        google()

        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}