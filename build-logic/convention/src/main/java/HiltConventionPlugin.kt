import com.plana.products.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {


            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
            }

            dependencies {
                "ksp"(libs.findLibrary("google.dagger.hilt.compiler").get())
                "implementation"(libs.findLibrary("google.dagger.hilt.android").get())
            }

        }
    }
}