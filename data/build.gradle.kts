plugins {
    id(GradlePlugin.javaLibrary)
    id(GradlePlugin.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(ModuleDependency.domain))

    implementation(Dependency.kotlinStdLib)
    implementation(Dependency.coroutines)

    implementation(Dependency.javaInject)
    
    implementation(Dependency.retrofit)
    implementation(Dependency.okHttp)
    implementation(Dependency.moshiConverter)

    testImplementation(Dependency.junit)
    testImplementation(Dependency.coroutinesTest)
    testImplementation(Dependency.turbine)
    testImplementation(Dependency.mockk)
    testImplementation(Dependency.koTest)
}