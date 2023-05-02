import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "dev.garlicbread"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Swagger
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.1.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")

    // Logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.google.truth:truth:1.1.3")

    // coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // Mockk
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    this.archiveFileName.set("TRaSH_Api.jar")
}
