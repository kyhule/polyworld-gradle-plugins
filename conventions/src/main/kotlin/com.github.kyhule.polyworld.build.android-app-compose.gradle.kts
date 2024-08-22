import com.github.kyhule.polyworld.build.configureAndroidCompose

plugins {
    id("com.github.kyhule.polyworld.build.android-app")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    configureAndroidCompose(this)
}
