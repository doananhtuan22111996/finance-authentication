import org.gradle.api.JavaVersion

object Configs {
    const val namespace = "vn.finance.authentication"
    const val minSdk = 24
    const val targetSdk = 34
    const val compileSdk = 34
    const val jvmTarget = "21"
    const val kotlinCompilerExtensionVersion = "1.5.14"
    val javaVersion = JavaVersion.VERSION_21
    const val mavenDomain = "https://maven.pkg.github.com"

    object BuildModule {
        const val authentication = ":authentication"
        const val authenticationPresentation = ":authentication:presentation"
        const val authenticationDomain = ":authentication:domain"
        const val authenticationData = ":authentication:data"
    }

    object Demo {
        const val namespace = "vn.finance.demo"
        const val applicationId = "vn.finance.demo"
        const val versionCode = 1
        const val versionName = "1.0.0"
    }

    object Authentication {
        const val namespaceData = "finance.authentication.data"
    }
}


