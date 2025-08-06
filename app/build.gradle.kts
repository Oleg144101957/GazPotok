plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("com.google.gms.google-services")
}

android {
    namespace = "ru.gz.po.tok41"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.gz.po.tok41"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.navigation.compose)

    implementation(libs.hilt.core)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.browser)
    implementation(libs.firebase.config.ktx)
    implementation(platform(libs.firebase.bom))


    implementation("androidx.browser:browser:1.8.0")

    implementation("com.android.installreferrer:installreferrer:2.2")

    implementation("com.google.android.gms:play-services-ads-identifier:18.2.0")

    implementation("com.onesignal:OneSignal:5.1.35")
    
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("androidx.compose.material3:material3:1.3.2")

    implementation("com.android.installreferrer:installreferrer:2.2")

    implementation("com.adjust.sdk:adjust-android:5.4.1")
}