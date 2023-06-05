plugins {
    alias(libs.plugins.githubRelease)
    alias(libs.plugins.reckon)
}

githubRelease {
    token { System.getenv("GITHUB_TOKEN") }
    owner { "kyhule" }
    releaseName { version.toString() }
    tagName { version.toString() }
    generateReleaseNotes { true }
}

reckon {
    setDefaultInferredScope("minor")
    setScopeCalc(calcScopeFromProp())
    snapshots()
    setStageCalc(calcStageFromProp())
}
