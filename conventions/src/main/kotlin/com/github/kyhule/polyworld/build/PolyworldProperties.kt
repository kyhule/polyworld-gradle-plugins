package com.github.kyhule.polyworld.build

import com.github.kyhule.polyworld.build.util.*
import com.github.kyhule.polyworld.build.util.booleanProperty
import com.github.kyhule.polyworld.build.util.longProperty
import org.gradle.api.Project


val Project.compileSdkVersion: Int
    get() = intProperty("polyworld.compileSdkVersion", defaultValue = 33)

val Project.minSdkVersion: Int
    get() = intProperty("polyworld.minSdkVersion", defaultValue = 24)

val Project.targetSdkVersion: Int
    get() = intProperty("polyworld.targetSdkVersion", defaultValue = 33)

val Project.jdkVersion: Int
    get() = intProperty("polyworld.jdkVersion", defaultValue = 17)

/** Flag to enable verbose logging in unit tests. */
val Project.testVerboseLogging: Boolean
    get() = booleanProperty("polyworld.test.verboseLogging")

/**
 * Property corresponding to the number unit tests to run on a fork before it is disposed.
 * This helps when tests leak memory.
 *
 * **See Also:** [Forking options](https://docs.gradle.org/current/userguide/performance.html#forking_options)
 */
val Project.testForkEvery: Long
    get() = longProperty("polyworld.test.forkEvery", defaultValue = 100L)
