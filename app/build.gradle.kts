plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.ksp)

}

android {
    namespace = "com.example.jetpackcomposeproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.jetpackcomposeproject"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String","BASE_URL",project.properties["BASE_URL"].toString())
        buildConfigField("String","API_KEY",project.properties["API_KEY"].toString())
        buildConfigField("String","SWAGGER_BASE_URL",project.properties["SWAGGER_BASE_URL"].toString())
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
        buildConfig = true
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


//    val nav_version = "2.8.1"

    implementation(libs.androidx.navigation.compose)

    // Todo  ViewModel
    //noinspection GradleDependency
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // Todo LiveData
    //noinspection GradleDependency
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // todo Gson library
    implementation (libs.gson)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)

    // todo Coroutune Dependancy
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.picasso)


    implementation (libs.androidx.core.ktx)
//    implementation (libs.androidx.appcompat)
    implementation (libs.material)

    implementation (libs.androidx.constraintlayout)
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.core)
    implementation(libs.androidx.appcompat.v161)



    implementation (libs.androidx.runtime.livedata)

    implementation(libs.coil.compose)


    implementation( libs.accompanist.systemuicontroller)





}