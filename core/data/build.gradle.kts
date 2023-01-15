plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.flow.data"
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
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(Dependency.KTX.CORE)
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.AndroidX.MATERIAL)
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.AndroidTest.TEST_RUNNER)
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)

    implementation(Dependency.Hilt.HILT)

    testImplementation(Dependency.Test.TRUTH)
    testImplementation(Dependency.Test.MOCKITO)
    testImplementation(Dependency.Test.CORE_TEST)
    testImplementation(Dependency.Test.MOCKK)
    testImplementation(Dependency.Test.TURBINE)
    testImplementation(Dependency.Coroutine.TEST)

    implementation(Dependency.Remote.RETROFIT)
    implementation(Dependency.Remote.CONVERTER)
    implementation(Dependency.Remote.HTTP)

    implementation(Dependency.Paging.PAGING)

    implementation(Dependency.Room.RUNTIME)
    kapt(Dependency.Room.COMPILER)
    implementation(Dependency.Room.ROOM_KTX)
}