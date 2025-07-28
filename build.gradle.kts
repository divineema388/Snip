// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

// Define versions for common libraries directly on the 'extra' extension
// This is the most common and robust way to share simple string properties in Kotlin DSL
extra["appcompat_version"] = "1.6.1"
extra["material_version"] = "1.12.0"
extra["recyclerview_version"] = "1.3.2" // Ensure this is compatible with your SDK and Material Components