import org.gradle.api.JavaVersion

object Configs {
    const val namespace = "vn.finance.authentication"
    const val minSdk = 24
    const val targetSdk = 34
    const val compileSdk = 34
    const val kotlinCompilerExtensionVersion = "1.5.14"
    val javaVersion = JavaVersion.VERSION_21
    val jvmTarget = JavaVersion.VERSION_21.toString()
    const val mavenDomain = "https://maven.pkg.github.com"

    object BuildModule {
        const val authentication = ":authentication"
        const val authenticationPresentation = ":authentication:presentation"
        const val authenticationBusiness = ":authentication:business"
    }

    object Demo {
        const val namespace = "vn.finance.demo"
        const val applicationId = "vn.finance.demo"
        const val versionCode = 1
        const val versionName = "1.0.0"
    }

    object Business {
        const val namespace = "vn.finance.authentication.business"
    }

    object Artifact {
        const val groupId = "vn.finance.libs"
        const val artifactId = "feature-authentication"
        const val artifactBusinessId = "feature-authentication-business"
        const val version = "1.0.1"
    }
}


