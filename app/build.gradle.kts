import java.util.Properties

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.secrets.gradle.plugin)
}

android {
    namespace = "org.care.packie"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.care.packie"
        minSdk = 28
        targetSdk = 34
        versionCode = 5
        versionName = "0.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    signingConfigs {
        create("release") {
            storeFile = file(properties["keystore_path"].toString())
            storePassword = properties["store_password"].toString()
            keyAlias = properties["key_alias"].toString()
            keyPassword = properties["key_password"].toString()
        }
    }

    buildTypes {
        debug {
            resValue(
                "string",
                "AD_MOB_BANNER_UNIT_ID",
                "ca-app-pub-3940256099942544/9214589741"
            )
        }
        release {
            resValue(
                "string",
                "AD_MOB_BANNER_UNIT_ID",
                properties["AD_MOB_BANNER_UNIT_ID"] as String? ?: "Default"
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.navigation)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.collapsing.toolbar)
    implementation(libs.security)
    implementation(libs.hilt.android)
    implementation(libs.hilt.plugin)
    implementation(libs.compose.hilt.navigation)
    implementation(libs.play.services.ads)
    kapt(libs.hilt.kapt)
    implementation(libs.kotlin.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}