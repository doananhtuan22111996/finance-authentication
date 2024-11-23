import vn.finance.buildSrc.Configs

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidCompose
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.Authentication.NAMESPACE_PRESENTATION
}

publishing {
    publications {
        create<MavenPublication>(Configs.Artifact.ARTIFACT_PRESENTATION_ID) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.GROUP_ID
            artifactId = Configs.Artifact.ARTIFACT_PRESENTATION_ID
            version = Configs.Artifact.VERSION
        }
    }
}

dependencies {
    implementation(project(Configs.BuildModule.AUTHENTICATION_BUSINESS))
    implementation(libs.coreCompose)
    implementation(libs.coreDomain)
    implementation(libs.coreData)
    implementation(libs.financeTheme)
    implementation(libs.financeNavigation)
    implementation(libs.ohteepee)
}
