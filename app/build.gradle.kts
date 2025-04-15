plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.gorai.gamehok"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gorai.gamehok"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.ui:ui:1.6.7")
    implementation("androidx.compose.material:material:1.6.7")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.7")
    implementation(libs.androidx.material3.android)
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.7")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}