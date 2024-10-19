// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidHilt) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
}

subprojects {
    configurations.all {
        resolutionStrategy.dependencySubstitution {
            exclude(group = "vn.finance.libs", module = "ui-theme")
        }
    }
}