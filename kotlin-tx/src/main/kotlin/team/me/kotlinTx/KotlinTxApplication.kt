package team.me.kotlinTx

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class KotlinTxApplication

fun main(args: Array<String>) {
    runApplication<KotlinTxApplication>(*args)
}
