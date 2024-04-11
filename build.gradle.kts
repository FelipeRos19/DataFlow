plugins {
    id("java")
    id("java-library")
    id("maven-publish")
}

group = "dev.feliperos"
version = "1.0-SNAPSHOT"

publishing {
    publications.create<MavenPublication>("DataFlowLib").from(components["java"])
    repositories.maven("https://repo.felipe.fun/snapshots") {
        name = "snapshots"
        credentials {
            username = ""
            password = ""
        }
    }

}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("redis.clients:jedis:5.1.0")
    implementation("org.slf4j:slf4j-api:2.0.12")
    implementation("org.slf4j:slf4j-simple:2.0.12")
    compileOnly("org.projectlombok:lombok:1.18.30")

    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}
