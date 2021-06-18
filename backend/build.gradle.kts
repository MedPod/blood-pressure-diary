plugins {
    java
    kotlin("jvm")
    kotlin("kapt")

    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.springframework.boot") version "2.3.10.RELEASE"
    kotlin("plugin.spring") version "1.4.32"
}

apply(plugin = "java")
apply(plugin = "kotlin")

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR11")
    }
}

group = "med.pod"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))

    // Spring
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    //Observability
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.4")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.4")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.5.4")

    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("io.zipkin.brave:brave-context-log4j2")
    implementation("com.vlkan.log4j2:log4j2-logstash-layout:1.0.2")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("com.lmax:disruptor:3.4.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")

    //Security
    implementation("com.google.firebase:firebase-admin:6.13.0")

    //Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.jeasy:easy-random-randomizers:5.0.0")
    testImplementation("org.awaitility:awaitility-kotlin:4.0.3")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")

    val restAssured = "4.2.0"
    testImplementation("io.rest-assured:rest-assured:$restAssured")
    testImplementation("io.rest-assured:json-path:$restAssured")
    testImplementation("io.rest-assured:xml-path:$restAssured")
    testImplementation("io.rest-assured:kotlin-extensions:$restAssured")

    //Utility
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
    implementation("javax.validation:validation-api:2.0.1.Final")
}

tasks {

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    withType<JavaCompile> {
        targetCompatibility = "11"
        sourceCompatibility = "11"
        options.encoding = "UTF-8"
    }

    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }

    test {
        testLogging {
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
        useJUnitPlatform()
    }
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}
