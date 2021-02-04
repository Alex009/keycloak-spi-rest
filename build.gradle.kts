plugins {
    kotlin("jvm") version "1.4.30"
}

group = "ru.alex009"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.keycloak:keycloak-core:12.0.2")
    compileOnly("org.keycloak:keycloak-server-spi:12.0.2")
    compileOnly("org.keycloak:keycloak-server-spi-private:12.0.2")
    compileOnly("org.keycloak:keycloak-services:12.0.2")
//    compileOnly("org.keycloak:keycloak-admin-client:12.0.2")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<org.gradle.jvm.tasks.Jar> {
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
