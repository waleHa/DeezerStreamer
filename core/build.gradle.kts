plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.deezer.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.firebase:firebase-auth:23.0.0")

    // Jetpack Compose
    implementation ("androidx.compose.ui:ui:1.1.0-alpha05")
    implementation ("androidx.compose.ui:ui-tooling:1.1.0-alpha05")
    implementation ("androidx.compose.foundation:foundation:1.1.0-alpha05")
    implementation( "androidx.compose.material:material:1.1.0-alpha05")
    implementation ("androidx.compose.material:material-icons-core:1.1.0-alpha05")
    implementation ("androidx.compose.material:material-icons-extended:1.1.0-alpha05")

// Coil for image loading (for the `ImageComponent` function)
    implementation ("io.coil-kt:coil-compose:1.4.0")

// You might also need Kotlin Coroutines for asynchronous operations
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

}