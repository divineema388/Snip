plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.snip.dealabs" // Your application's package name
    compileSdk = 34 // Target Android 14 (API level 34)

    defaultConfig {
        applicationId = "com.snip.dealabs"
        minSdk = 21 // Minimum Android version (Lollipop)
        targetSdk = 34 // Target Android 14 (API level 34)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false // `minifyEnabled` becomes `isMinifyEnabled` in Kotlin DSL
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    // AndroidX Core for compatibility
    implementation("androidx.core:core-ktx:1.13.1")

    // AppCompat for basic Android UI components
    // Accessing extra properties from rootProject using rootProject.extra["key"]
    implementation("androidx.appcompat:appcompat:${rootProject.extra["appcompat_version"]}")

    // Material Design components (for Theme.MaterialComponents and various UI elements)
    implementation("com.google.android.material:material:${rootProject.extra["material_version"]}")

    // RecyclerView for efficient list display
    implementation("androidx.recyclerview:recyclerview:${rootProject.extra["recyclerview_version"]}")

    // Unit tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}