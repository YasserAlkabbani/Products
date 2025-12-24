import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class FeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            with(pluginManager) {
                apply {
                    apply("plana.products.library")
                    apply("org.jetbrains.kotlin.plugin.serialization")
                }
            }

            dependencies {
                "api"(project(":core:navigation"))
            }

        }
    }
}