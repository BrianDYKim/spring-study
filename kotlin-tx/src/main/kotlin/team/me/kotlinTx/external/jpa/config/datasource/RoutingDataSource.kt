package team.me.kotlinTx.external.jpa.config.datasource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class RoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): String {
        val currentKey = DataSourceContextHolder.getDataSourceType()
        println("Routing to DataSource with key: $currentKey")
        return DataSourceContextHolder.getDataSourceType().dataSourceKey
    }
}
