plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")

    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.flow.searchlist"
    compileSdk = 32

    defaultConfig {
        minSdk = 24
        targetSdk = 32

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
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    implementation(Dependency.KTX.CORE)
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.AndroidX.MATERIAL)
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.AndroidTest.TEST_RUNNER)
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)

    implementation(Dependency.Hilt.HILT)
    kapt(Dependency.Hilt.HILT_KAPT)

    implementation(Dependency.LifeCycle.VM)
    implementation(Dependency.LifeCycle.EXTENSIONS)
    implementation(Dependency.LifeCycle.LIVEDATA)

    implementation(Dependency.Paging.PAGING)

    implementation(Dependency.Glide.GLIDE)
    implementation(Dependency.Glide.GLIDE_COMPILER)

    implementation(Dependency.Nav.NAV_UI)
    implementation(Dependency.Nav.NAV_FRAGMENT)
}