import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.projectlombok:lombok:1.18.20")
	implementation("org.junit.jupiter:junit-jupiter:5.7.0")
	testImplementation("org.hamcrest:hamcrest-all:1.3")
	testImplementation("org.mockito:mockito-core:4.5.1")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.jsoup:jsoup:1.14.3")
	implementation("com.slack.api:slack-api-client:1.22.0")
	implementation("com.slack.api:slack-api-model-kotlin-extension:1.22.0")
	implementation("com.slack.api:slack-api-client-kotlin-extension:1.22.0")
	implementation("org.apache.commons:commons-lang3:3.12.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
