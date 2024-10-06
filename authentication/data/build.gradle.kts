plugins {
    alias(mobilex.plugins.kotlinAndroid)
    alias(mobilex.plugins.androidLibrary)
    id("kotlin-kapt")
    alias(mobilex.plugins.androidHilt)
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
    implementation(mobilex.coreLibxDomain)
    implementation(mobilex.coreLibxData)

    implementation(mobilex.androidxHilt)
    kapt(mobilex.androidxHiltCompiler)
    implementation(mobilex.retrofit)
    implementation(mobilex.retrofitGson)
    implementation(mobilex.loggerOkhttp)
    implementation(mobilex.loggerTimber)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}