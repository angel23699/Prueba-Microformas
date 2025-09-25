// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // ROOM database
    alias(libs.plugins.room) apply false
    alias(libs.plugins.ksp) apply false

    //hilt (inyección de dependencias)
    alias(libs.plugins.dagger.hilt) apply false
}