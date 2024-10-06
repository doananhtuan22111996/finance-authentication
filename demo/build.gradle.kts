plugins {
    alias(mobilex.plugins.androidApplication)
    alias(mobilex.plugins.kotlinAndroid)
    id("kotlin-kapt")
    alias(mobilex.plugins.androidHilt)
}

android {
    namespace = Configs.Demo.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.Demo.applicationId
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.Demo.versionCode
        versionName = Configs.Demo.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

dependencies {
    implementation(fnlibs.financeTheme)
    implementation(fnlibs.financeLaunch)
    implementation(fnlibs.financeNavigation)
    implementation(fnlibs.financeOnboarding)
    implementation(project(Configs.BuildModule.authenticationPresentation))

    implementation(mobilex.coreLibxUiComposex)
    implementation(mobilex.bundles.coreAndroidComponents)
    implementation(platform(mobilex.androidxComposeBom))
    implementation(mobilex.bundles.jetpackComposeComponents)
    implementation(mobilex.androidxHilt)
    kapt(mobilex.androidxHiltCompiler)
    testImplementation(mobilex.bundles.composeTestComponents)
    androidTestImplementation(mobilex.bundles.androidTestComponents)
}

kapt {
    correctErrorTypes = true
}