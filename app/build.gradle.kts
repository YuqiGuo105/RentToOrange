plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.renttoorange"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.renttoorange"
        minSdk = 30
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:0.7.6")
    implementation("io.ktor:ktor-client-cio:2.3.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")

//    implementation("io.github.jan-tennert.supabase:gotrue-kt:1.3.2")
//    implementation("io.github.jan-tennert.supabase:compose-auth:1.3.2")
//    implementation("io.github.jan-tennert.supabase:compose-auth-ui:1.3.2")
//    implementation("io.github.jan-tennert.supabase:storage-kt:1.3.2")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("io.coil-kt:coil-compose:2.4.0")
}