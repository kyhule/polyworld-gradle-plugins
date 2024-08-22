import com.github.kyhule.polyworld.build.androidTargetSdk
import com.github.kyhule.polyworld.build.configureKotlinAndroid

plugins {
    id("com.android.library")
    id("org.gradle.android.cache-fix")
    id("kotlin-android")
}

android {
    configureKotlinAndroid(this)
    defaultConfig.targetSdk = androidTargetSdk
}

androidComponents {
    beforeVariants {
        it.androidTest.enable = it.androidTest.enable && project.projectDir.resolve("src/androidTest").exists()
    }
}
