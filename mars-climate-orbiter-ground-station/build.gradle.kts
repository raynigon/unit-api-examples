import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.spring") version "2.1.10"
    kotlin("plugin.jpa") version "2.1.10"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

group = "com.raynigon.mco"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven { url = URI.create("https://jitpack.io") }
}

dependencies {
    implementation("com.raynigon.unit-api:spring-boot-jackson-starter:3.0.8")
    implementation("com.raynigon.unit-api:spring-boot-jpa-starter:3.0.8")
    implementation("com.raynigon.unit-api:spring-boot-springdoc-starter:3.0.8")
    implementation("com.raynigon.unit-api:unit-api-kotlin:3.0.8")
    implementation("com.raynigon.unit-api:unit-api-core:3.0.8")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")

    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.flywaydb:flyway-database-postgresql")

    implementation("org.testcontainers:postgresql") // We dont want to setup a real DB here
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.testcontainers:junit-jupiter")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.20.4")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
