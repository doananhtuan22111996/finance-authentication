plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = Configs.javaVersion
    targetCompatibility = Configs.javaVersion
}

dependencies {
    implementation(libs.coreLibxDomain)
    implementation(libs.androidxCoreCoroutines)
    compileOnly(libs.javax)
}