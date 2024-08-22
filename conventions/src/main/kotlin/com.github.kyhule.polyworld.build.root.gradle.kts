plugins {
    id("com.github.kyhule.polyworld.build.dependency-analysis")
    id("com.github.kyhule.polyworld.build.doctor")
}

allprojects {
    apply(plugin = "com.squareup.sort-dependencies")
}
