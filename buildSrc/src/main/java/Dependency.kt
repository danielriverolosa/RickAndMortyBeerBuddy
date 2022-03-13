object Dependency {

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"

    // Background tasks
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"

    // DI
    const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    const val javaInject = "javax.inject:javax.inject:${Version.javaInject}"

    // Framework
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    const val crypto = "androidx.security:security-crypto:${Version.crypto}"
    const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    const val fragment = "androidx.fragment:fragment-ktx:${Version.fragment}"

    // Design
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val coil = "io.coil-kt:coil:${Version.coil}"

    // Network
    const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"

    // Local data storage
    const val roomRuntime = "androidx.room:room-runtime:${Version.room}"
    const val roomKtx = "androidx.room:room-ktx:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"

    // Testing
    const val junit = "junit:junit:${Version.junit}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
    const val androidXTest = "androidx.test:core:${Version.androidXTest}"
    const val turbine = "app.cash.turbine:turbine:${Version.turbine}"
    const val mockk = "io.mockk:mockk:${Version.mockk}"
    const val koTest = "io.kotest:kotest-assertions-core:${Version.koTest}"
    const val roomTest = "androidx.room:room-testing:${Version.room}"
}