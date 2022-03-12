
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(GradleDependency.android)
        classpath(GradleDependency.kotlin)
        classpath(GradleDependency.hilt)
        classpath(GradleDependency.safeArgs)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}