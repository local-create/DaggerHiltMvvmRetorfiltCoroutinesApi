plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.app.retrofitwithcoroutines"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.app.retrofitwithcoroutines"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    val coroutines_version = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.google.code.gson:gson:2.10.1")
//    implementation("com.squareup.retrofit2:adapter-rxjava:$retrofit_version")
//    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofit_version")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-common:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
//    implementation("android.arch.lifecycle:extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
}