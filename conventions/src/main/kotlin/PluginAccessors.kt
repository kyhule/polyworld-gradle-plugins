import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.`polyworld-root`: PluginDependencySpec
    get() = polyworld("root")
internal fun PluginDependenciesSpec.polyworld(
    name: String,
    version: String? = null
): PluginDependencySpec =
    if (version.isNullOrBlank()) id("com.github.kyhule.polyworld.build.$name")
    else id("com.xfinity.dh.build.$name").version(version)
