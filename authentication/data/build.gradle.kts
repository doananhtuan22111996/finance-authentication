plugins {
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.androidLibrary)
    id("kotlin-kapt")
    alias(libs.plugins.androidHilt)
}

android {
    namespace = Configs.Authentication.namespaceData
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(Configs.BuildModule.authenticationDomain))
    implementation(libs.coreLibxDomain)
    implementation(libs.coreLibxData)

    implementation(libs.androidxHilt)
    kapt(libs.androidxHiltCompiler)
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)
    implementation(libs.loggerOkhttp)
    implementation(libs.loggerTimber)

    testImplementation(libs.bundles.testComponents)
    androidTestImplementation(libs.bundles.androidTestComponents)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}