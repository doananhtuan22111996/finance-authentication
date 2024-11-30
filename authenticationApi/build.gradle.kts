import vn.finance.buildSrc.Configs

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidCompose
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.Authentication.NAMESPACE_API
}

publishing {
    publications {
        create<MavenPublication>(Configs.Artifact.ARTIFACT_API_ID) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.GROUP_ID
            artifactId = Configs.Artifact.ARTIFACT_API_ID
            version = Configs.Artifact.VERSION
        }
    }
}

dependencies {
}
