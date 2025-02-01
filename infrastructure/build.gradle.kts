plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.allopen") version "2.1.0"
}

group = "com.muliyul"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation(platform("ru.vyarus.guicey:guicey-bom:7.1.4"))
    implementation("ru.vyarus:dropwizard-guicey")
    implementation("ru.vyarus.guicey:guicey-admin-rest")
    implementation("io.dropwizard:dropwizard-auth")

    implementation("ru.vyarus.guicey:guicey-jdbi3")
    implementation("ru.vyarus.guicey:guicey-eventbus")
    implementation("org.jdbi:jdbi3-jackson2:3.47.0")
    implementation("org.jdbi:jdbi3-kotlin:3.47.0")
    implementation("org.jdbi:jdbi3-kotlin-sqlobject:3.47.0")
    implementation("org.flywaydb:flyway-mysql:11.1.0")
    implementation("io.dropwizard.modules:dropwizard-flyway:4.0.1-2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.javamoney:moneta:1.4.4")
    implementation("nl.hiddewieringa:money-kotlin:2.1.0")
    implementation("org.zalando:jackson-datatype-money:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

    implementation("com.github.mtakaki:dropwizard-hikaricp:2.0.20")
    runtimeOnly("com.mysql:mysql-connector-j:9.1.0")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.14")
    testImplementation("io.dropwizard:dropwizard-testing")
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

allOpen {
    annotations(
        "ru.vyarus.guicey.jdbi3.tx.InTransaction",
        "com.google.common.eventbus.Subscribe"
    )
}
