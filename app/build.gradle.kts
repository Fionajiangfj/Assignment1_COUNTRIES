plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.yj.countries_yan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yj.countries_yan"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Add library imports for Retrofit, Moshi, HttpLoggingInterceptor, Kotlin Coroutines
// retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
// moshi
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
// moshi annotation processor for kotlin
    implementation("com.squareup.moshi:moshi-kotlin:1.9.3")
// optional library to debug Retrofit’s http requests/responses
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
// needed for background tasks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")

    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-firestore:24.9.1")
}