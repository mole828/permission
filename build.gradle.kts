val kotlinVersion: String = "2.1.0"
val ktorVersion: String = "3.0.2"

plugins {
    kotlin("jvm") version "2.1.0"
    id("io.ktor.plugin") version "3.0.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
}

group = "top.mole"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-sessions-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-config-yaml-jvm")
    implementation("org.ktorm:ktorm-core:4.1.1")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("ch.qos.logback:logback-classic:1.5.15")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}
