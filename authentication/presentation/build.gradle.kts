plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
    alias(libs.plugins.androidHilt)
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
    publishing {
        multipleVariants("all") {
            allVariants()
            withSourcesJar()
        }
    }
}

publishing {
    val ghUsername = System.getenv("GH_USERNAME")
    val ghPassword = System.getenv("GH_TOKEN")
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("${Configs.mavenDomain}/${ghUsername}/finance-authentication")
            credentials {
                username = ghUsername
                password = ghPassword
            }
        }
    }
    publications {
        create<MavenPublication>("mavenAndroid") {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.groupId
            artifactId = Configs.Artifact.artifactId
            version = Configs.Artifact.version
        }
    }
}

dependencies {
    implementation(project(Configs.BuildModule.authenticationBusiness))
    implementation(libs.coreLibxUiComposex)
    implementation(libs.coreLibxDomain)
    implementation(libs.coreLibxData)
    implementation(libs.bundles.coreAndroidComponents)
    implementation(platform(libs.androidxComposeBom))
    implementation(libs.bundles.jetpackComposeComponents)
    implementation(libs.androidxHilt)
    kapt(libs.androidxHiltCompiler)
    implementation(libs.loggerTimber)
    testImplementation(libs.bundles.testComponents)
    androidTestImplementation(libs.bundles.androidTestComponents)

    implementation(fnlibs.financeTheme)
    implementation(fnlibs.financeNavigation)
    implementation(fnlibs.ohteepee)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}