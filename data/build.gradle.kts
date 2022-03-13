plugins {
    id(GradlePlugin.androidLibrary)
    id(GradlePlugin.kotlinAndroid)
    id(GradlePlugin.kotlinKapt)
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
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

    implementation(Dependency.kotlinStdLib)
    implementation(Dependency.coroutines)

    implementation(Dependency.javaInject)
    
    implementation(Dependency.retrofit)
    implementation(Dependency.okHttp)
    implementation(Dependency.moshiConverter)

    implementation(Dependency.roomRuntime)
    implementation(Dependency.roomKtx)
    kapt(Dependency.roomCompiler)

    testImplementation(Dependency.junit)
    testImplementation(Dependency.coroutinesTest)
    testImplementation(Dependency.turbine)
    testImplementation(Dependency.mockk)
    testImplementation(Dependency.koTest)
    testImplementation(Dependency.roomTest)
}