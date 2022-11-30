import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = AppConfig.namespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        val projectProperties = readProperties(file("../api.properties"))

        buildConfigField("String", "BASE_URL", projectProperties["BASE_URL"] as String)

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
        dataBinding = true
    }
}

dependencies {
    implementation(project(Dependencies.Modules.data))
    implementation(project(Dependencies.Modules.domain))

    implementation(Dependencies.AndroidComponents.ktxCore)
    implementation(Dependencies.AndroidComponents.splashScreen)
    implementation(Dependencies.AndroidComponents.appCompat)
    implementation(Dependencies.AndroidComponents.material)
    implementation(Dependencies.AndroidComponents.constraint)

    implementation(Dependencies.Navigation.navigation)
    implementation(Dependencies.Navigation.navigationUI)

    implementation(Dependencies.Lifecycle.livedata)
    implementation(Dependencies.Lifecycle.viewmodel)

    implementation(Dependencies.Coroutines.coroutines)

    implementation(Dependencies.AdapterDelegates.adapterDelegates)
    implementation(Dependencies.AdapterDelegates.adapterDelegatesViewBinding)

    implementation(Dependencies.Koin.koinCore)
    implementation(Dependencies.Koin.koinAndroid)

    implementation(Dependencies.Coil.coil)
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { fis ->
        load(fis)
    }
}