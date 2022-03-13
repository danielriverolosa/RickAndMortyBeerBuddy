plugins {
    id(GradlePlugin.androidApplication)
    id(GradlePlugin.kotlinAndroid)
    id(GradlePlugin.kotlinKapt)
    id(GradlePlugin.hilt)
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/\"")
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName(AppConfig.debugBuild) {
            isTestCoverageEnabled = true
        }

        getByName(AppConfig.releaseBuild) {
            isTestCoverageEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile(AppConfig.proguardFile), AppConfig.proguardRules
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(ModuleDependency.domain))
    implementation(project(ModuleDependency.data))

    implementation(Dependency.coreKtx)
    implementation(Dependency.appCompat)
    implementation(Dependency.material)
    implementation(Dependency.viewModel)
    implementation(Dependency.lifecycle)
    implementation(Dependency.constraintLayout)
    implementation(Dependency.navigationFragment)
    implementation(Dependency.navigationUi)
    implementation(Dependency.liveData)
    implementation(Dependency.fragment)
    implementation(Dependency.coroutines)

    implementation(Dependency.timber)

    implementation(Dependency.roomRuntime)
    implementation(Dependency.roomKtx)

    implementation(Dependency.coil)

    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)

    implementation(Dependency.retrofit)
    implementation(Dependency.okHttp)
    implementation(Dependency.moshiConverter)

    testImplementation(Dependency.junit)
    testImplementation(Dependency.coroutinesTest)
    testImplementation(Dependency.turbine)
    testImplementation(Dependency.mockk)
    testImplementation(Dependency.koTest)
    testImplementation(Dependency.roomTest)
}