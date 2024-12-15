package team.me.kotlinTx.external.jpa.config.datasource

import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

interface DataSourceConfigurationInterface {
    fun writeDataSource(): DataSource

    fun readDataSource(): DataSource

    fun routingDataSource(
        writeDataSource: DataSource,
        readDataSource: DataSource,
    ): DataSource

    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        dataSource: DataSource,
    ): LocalContainerEntityManagerFactoryBean

    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager
}
