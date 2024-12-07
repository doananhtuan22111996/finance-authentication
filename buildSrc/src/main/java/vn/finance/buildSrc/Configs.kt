package vn.finance.buildSrc

object Configs {
    object BuildModule {
        const val AUTHENTICATION_PRESENTATION = ":authentication:presentation"
        const val AUTHENTICATION_BUSINESS = ":authentication:business"
        const val AUTHENTICATION_API = ":authenticationApi"
    }

    object Demo {
        const val NAMESPACE = "vn.finance.demo"
        const val APPLICATION_ID = "vn.finance.demo"
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0.0"
    }

    object Authentication {
        const val NAMESPACE_PRESENTATION = "vn.finance.authentication.presentation"
        const val NAMESPACE_BUSINESS = "vn.finance.authentication.business"
        const val NAMESPACE_API = "vn.finance.authentication.api"
    }

    object Artifact {
        const val GROUP_ID = "vn.finance.libs"
        const val ARTIFACT_PRESENTATION_ID = "feature-authentication-presentation"
        const val ARTIFACT_BUSINESS_ID = "feature-authentication-business"
        const val ARTIFACT_API_ID = "feature-authentication-api"
        const val VERSION = "1.0.4"
    }
}
