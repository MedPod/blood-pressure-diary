plugins {
    kotlin("jvm") version "1.4.32"
}

allprojects {

    group = "med.pod0"

    repositories {
        mavenCentral()
        google()
        jcenter()
    }

    tasks {

        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }
    }
}

buildscript {
    repositories {
        google()
    }
    val kotlin_version by extra("1.4.30")
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.gms:google-services:4.3.2")
    }
}

tasks {
    wrapper {
        gradleVersion = "6.8.3"
    }
}
