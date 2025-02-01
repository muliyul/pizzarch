plugins {
    kotlin("jvm") version "2.1.0"
}

group = "com.muliyul"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.javamoney:moneta:1.4.4")
    implementation("nl.hiddewieringa:money-kotlin:2.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
