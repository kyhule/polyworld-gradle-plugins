package com.github.kyhule.polyworld.build

import com.github.kyhule.polyworld.build.util.isCi
import com.github.kyhule.polyworld.build.util.isGithubActions
import com.github.kyhule.polyworld.build.util.isLinux
import com.github.kyhule.polyworld.build.util.setDisallowChanges
import com.github.kyhule.polyworld.build.util.synchronousEnvProperty
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.findByType
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import kotlin.math.max
import kotlin.math.roundToInt

internal fun Test.configureTest(project: Project) {
    if (project.testVerboseLogging) {
        // Add additional logging on Jenkins to help debug hanging or OOM-ing unit tests.
        testLogging {
            showStandardStreams = true
            showStackTraces = true

            // Set options for log level LIFECYCLE
            events("started", "passed", "failed", "skipped")
            setExceptionFormat("short")

            // Setting this to 0 (the default is 2) will display the test executor that each test is running on.
            displayGranularity = 0
        }
    }

    // https://github.com/gradle/gradle/issues/5184#issuecomment-457865951
    extensions.findByType(JacocoTaskExtension::class)?.apply {
        isIncludeNoLocationClasses = true
        excludes = excludes.orEmpty() + listOf("jdk.internal.*")
    }

    minHeapSize = project.testMinHeapSize
    maxHeapSize = project.testMaxHeapSize

    // Run unit tests in parallel if multiple CPUs are available. Use at most half the available CPUs.
    maxParallelForks = maxForks(project).also { logger.debug("Setting maxParallelForks to $it") }

    // Helps when tests leak memory
    setForkEvery(project.testForkEvery)

    // Denote flaky failures as <flakyFailure> instead of <failure> in JUnit test XML files
    reports.junitXml.mergeReruns.setDisallowChanges(true)

    jvmArgs(
        // Cover for illegal reflection warnings
        // https://github.com/square/retrofit/issues/3557
        "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
        // Robolectric 4.9+ requires these --add-opens options.
        // https://github.com/robolectric/robolectric/issues/7456
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED",
        "-Xss1m", // Stack size
    )
    if (project.isCi) {

        // Improve JVM memory behavior in tests to avoid OOMs but only on Linux
        if (project.isLinux()) {
            jvmArgs("-XX:+UseContainerSupport")
        }

        val workspaceDir = when {
            project.isGithubActions -> project.synchronousEnvProperty("GITHUB_WORKSPACE")
            else -> project.rootProject.projectDir.absolutePath
        }
        jvmArgs(
            "-XX:+HeapDumpOnOutOfMemoryError", // Produce a heap dump when an OOM occurs
            "-XX:+CrashOnOutOfMemoryError", // Produce a crash report when an OOM occurs
            "-XX:+UseGCOverheadLimit",
            "-XX:GCHeapFreeLimit=10",
            "-XX:GCTimeLimit=20",
            "-XX:HeapDumpPath=$workspaceDir/fs_oom_err_pid<pid>.hprof",
            "-XX:ErrorFile=$workspaceDir/fs_oom_err_pid<pid>.log",
        )
    }
}

private fun maxForks(project: Project): Int {
    val multiplier = project.unitTestParallelismMultiplier
    return max((Runtime.getRuntime().availableProcessors() * multiplier).roundToInt(), 1)
}
