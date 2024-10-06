plugins {
    id("java-library")
    alias(mobilex.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = Configs.javaVersion
    targetCompatibility = Configs.javaVersion
}

dependencies {
    implementation(mobilex.coreLibxDomain)
    implementation(mobilex.androidxCoreCoroutines)
    compileOnly(mobilex.javax)
}