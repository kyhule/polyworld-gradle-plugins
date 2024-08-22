import com.github.kyhule.polyworld.build.androidTargetSdk
import com.github.kyhule.polyworld.build.configureKotlinAndroid

plugins {
    id("com.android.application")
    id("org.gradle.android.cache-fix")
    id("kotlin-android")
}


android {
    configureKotlinAndroid(this)
    defaultConfig.targetSdk = androidTargetSdk
}
