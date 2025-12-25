import com.plana.products.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.room.runtime").get())
                "implementation"(libs.findLibrary("androidx.room.compiler").get())
                "implementation"(libs.findLibrary("androidx.room.ktx").get())
                "implementation"(libs.findLibrary("androidx.room.paging").get())
            }

        }
    }

}