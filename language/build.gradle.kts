import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val graalVersion = "1.0.0-rc16"

plugins {
    java
    kotlin("jvm") version "1.3.30"
    kotlin("kapt") version "1.3.30"

    id("com.github.johnrengelman.shadow") version "4.0.4"
}

version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.graalvm.truffle:truffle-api:$graalVersion")
    implementation("org.graalvm.truffle:truffle-dsl-processor:$graalVersion")
    implementation("org.graalvm.truffle:truffle-tck:$graalVersion")

    kapt("org.graalvm.truffle:truffle-dsl-processor:$graalVersion")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
    baseName = "template"
    classifier = ""
    version = ""

    dependencies {
        exclude(dependency("org.graalvm.truffle:truffle-api:$graalVersion"))
        exclude(dependency("org.graalvm.truffle:truffle-dsl-processor:$graalVersion"))
        exclude(dependency("org.graalvm.truffle:truffle-tck:$graalVersion"))
    }
}

kapt {
    useBuildCache = true
    includeCompileClasspath = false
}