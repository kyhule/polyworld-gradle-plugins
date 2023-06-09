import com.xfinity.dh.build.ConventionPluginsVersions

plugins {
    id("com.github.kyhule.polyworld.build.android-app")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ConventionPluginsVersions.ComposeCompilerVersion
    }
}
