val queryDslVersion = "5.1.0"

dependencies {
    // Spring Data Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // JPA
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Query DSL
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion:jakarta")
    kapt("com.querydsl:querydsl-apt:$queryDslVersion:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")
}

tasks {
    named<Delete>("clean") {
        delete("src/main/generated")
    }
}