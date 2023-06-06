plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.mavenPublish)
}

publishing {
    repositories {
        maven {
            name = "githubPackages"
            url = uri("https://maven.pkg.github.com/kyhule/polyworld-gradle-plugin")
            credentials(PasswordCredentials::class)
        }
    }
}

dependencies {
    compileOnly(gradleApi())
    implementation(libs.gradlePlugin.dependencyAnalysis)
    implementation(libs.gradlePlugin.doctor)
    implementation(libs.gradlePlugin.githubRelease)
    implementation(libs.gradlePlugin.mavenPublish)
    implementation(libs.gradlePlugin.reckon)
}
