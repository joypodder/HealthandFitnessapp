// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral() // Add this line if you're using a library not in Google's Maven repo
    }
    dependencies {
        // ... other dependencies
        classpath("com.google.gms:google-services:4.3.15") // Use the latest version
    }
}
