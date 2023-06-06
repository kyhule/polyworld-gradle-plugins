plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.mavenPublish)
}

dependencies {
    compileOnly(gradleApi())
    implementation(libs.gradlePlugin.dependencyAnalysis)
    implementation(libs.gradlePlugin.doctor)
    implementation(libs.gradlePlugin.githubRelease)
    implementation(libs.gradlePlugin.mavenPublish)
    implementation(libs.gradlePlugin.reckon)
}
