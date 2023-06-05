plugins {
    alias(libs.plugins.reckon)
}


reckon {
    setDefaultInferredScope("minor")
    setScopeCalc(calcScopeFromProp())
    snapshots()
    setStageCalc(calcStageFromProp())
}
