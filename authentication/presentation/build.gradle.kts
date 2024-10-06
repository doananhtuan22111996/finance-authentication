plugins {
    alias(mobilex.plugins.androidLibrary)
    alias(mobilex.plugins.kotlinAndroid)
    id("kotlin-kapt")
    alias(mobilex.plugins.androidHilt)
    `maven-publish`
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Configs.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

publishing {
    val ghUsername = System.getenv("USERNAME")
    val ghPassword = System.getenv("TOKEN")
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${ghUsername}/finance-authentication")
            credentials {
                username = ghUsername
                password = ghPassword
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "vn.finance.libs" // Replace with your GitHub username
            artifactId = "feature-authentication"
            version = "1.0.0" // Set your desired version here
        }
    }
}

dependencies {
    implementation(project(Configs.BuildModule.authenticationDomain))
    implementation(project(Configs.BuildModule.authenticationData))
    implementation(mobilex.coreLibxUiComposex)
    implementation(mobilex.coreLibxDomain)
    implementation(mobilex.coreLibxData)

    implementation(fnlibs.financeTheme)
    implementation(fnlibs.financeNavigation)

    implementation(mobilex.bundles.coreAndroidComponents)
    implementation(platform(mobilex.androidxComposeBom))
    implementation(mobilex.bundles.jetpackComposeComponents)
    implementation(mobilex.androidxHilt)
    kapt(mobilex.androidxHiltCompiler)

    implementation(libs.ohteepee)
    implementation(mobilex.loggerTimber)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}