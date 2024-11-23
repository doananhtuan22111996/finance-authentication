import vn.finance.buildSrc.Configs

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.Authentication.NAMESPACE_BUSINESS
}

publishing {
    publications {
        create<MavenPublication>(Configs.Artifact.ARTIFACT_BUSINESS_ID) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.GROUP_ID
            artifactId = Configs.Artifact.ARTIFACT_BUSINESS_ID
            version = Configs.Artifact.VERSION
        }
    }
}

dependencies {
    implementation(libs.provideNetworking)
    implementation(libs.coreDomain)
    implementation(libs.coreData)
}
