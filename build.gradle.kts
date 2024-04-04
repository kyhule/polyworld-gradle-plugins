plugins {
    alias(libs.plugins.reckon)
    alias(libs.plugins.githubRelease)
}

reckon {
    setDefaultInferredScope("minor")
    setScopeCalc(calcScopeFromProp())
    snapshots()
    setStageCalc(calcStageFromProp())
}

githubRelease {
    token { System.getenv("GITHUB_TOKEN") }
    owner = "kyhule"
    releaseName = version.toString()
    tagName = version.toString()
    generateReleaseNotes = true
}
