import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("plugin.jpa") version "1.4.21"
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin="kotlin")
    apply(plugin="org.springframework.boot")
    apply(plugin="io.spring.dependency-management")
    apply(plugin="org.jetbrains.kotlin.plugin.spring")

    group = "com.hubtwork"

    dependencies {
        // jpa
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly("com.h2database:h2")
        runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
        // webflux
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        // reactor
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        // jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        // Gson
        implementation("com.google.code.gson:gson:2.8.6")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
            dependsOn(processResources) // kotlin 에서 ConfigurationProperties
        }
        "test"(Test::class) {
            useJUnitPlatform()
        }
    }

}

project(":katarina-core") {
    val bootJar: BootJar by tasks
    val jar: Jar by tasks

    bootJar.enabled = false
    jar.enabled = true

    version = "1.0-SNAPSHOT"

    dependencies {
        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

}

project(":katarina-batch") {
    apply(plugin="org.jetbrains.kotlin.plugin.jpa")

    version = "1.0-Alpha"

    dependencies {
        implementation(project(":katarina-core"))
        // Spring Batch
        implementation("org.springframework.boot:spring-boot-starter-batch")
        testImplementation("org.springframework.batch:spring-batch-test")
        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

}

project(":katarina-web") {

    version = "1.0-SNAPSHOT"

    dependencies {
        implementation(project(":katarina-core"))
    }
}

project(":katarina-api") {

    version = "1.0-SNAPSHOT"

    dependencies {
        implementation(project(":katarina-core"))
    }
}