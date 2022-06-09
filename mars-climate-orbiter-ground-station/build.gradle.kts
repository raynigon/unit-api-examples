import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.spring") version "1.7.0"
    kotlin("plugin.jpa") version "1.6.21"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

group = "com.raynigon.mco"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven { url = URI.create("https://jitpack.io") }
}

dependencies {
    implementation("com.github.raynigon.unit-api:spring-boot-jackson-starter:2.0.1")
    implementation("com.github.raynigon.unit-api:spring-boot-jpa-starter:2.0.1")
    implementation("com.github.raynigon.unit-api:unit-api-kotlin:573c18c370")
    implementation("com.github.raynigon.unit-api:unit-api-core:573c18c370")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    /*implementation("org.springdoc:springdoc-openapi-ui:1.6.3")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.3")
    implementation("org.springdoc:springdoc-openapi-webmvc-core:1.6.5")
    implementation("io.swagger.core.v3:swagger-annotations:2.1.12")
    implementation("org.webjars:swagger-ui:4.2.1")*/

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.testcontainers:postgresql") // We dont want to setup a real DB here
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.testcontainers:junit-jupiter")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.17.2")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
