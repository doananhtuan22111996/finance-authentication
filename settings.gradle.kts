import java.util.Properties

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url =  uri("https://jitpack.io") }
        maven {
            val ghUsername = System.getenv("USERNAME") ?: getLocalProperty("USERNAME")
            val ghPassword = System.getenv("TOKEN") ?: getLocalProperty("TOKEN")
            url = uri("https://maven.pkg.github.com/${ghUsername}/REPOSITORY")
            credentials {
                username = ghUsername
                password = ghPassword
            }
        }
    }
    versionCatalogs {
        create("mobilex") {
            from("vn.core.libx:versions:1.0.3")
        }
        create("fnlibs") {
            from("vn.finance.libs:versions:1.0.0")
        }
    }
}

fun getLocalProperty(propertyName: String): String {
    val localProperties = Properties().apply {
        val localPropertiesFile = File(rootDir, "local.properties")
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }

    return localProperties.getProperty(propertyName) ?: run {
        throw NoSuchFieldException("Not defined property: $propertyName")
    }
}

rootProject.name = "finance-authentication"
include(":demo")
include(":authentication")
include(":authentication:presentation")
include(":authentication:domain")
include(":authentication:data")
