plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-context:5.3.39")
    implementation("io.github.resilience4j:resilience4j-retry:1.7.1")
    implementation("io.github.resilience4j:resilience4j-circuitbreaker:1.7.1")
    implementation("io.github.resilience4j:resilience4j-bulkhead:1.7.1")
    implementation("io.github.resilience4j:resilience4j-metrics:1.7.1")
    implementation("io.github.resilience4j:resilience4j-micrometer:1.7.1")
    implementation("io.micrometer:micrometer-registry-prometheus:1.9.17")
    implementation("org.projectlombok:lombok:1.18.30")
    implementation("io.vavr:vavr:0.10.4")
    implementation("com.google.guava:guava:18.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}