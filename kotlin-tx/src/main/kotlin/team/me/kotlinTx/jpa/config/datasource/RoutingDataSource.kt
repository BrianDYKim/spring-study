package team.me.kotlinTx.jpa.config.datasource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class RoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): String {
        return DataSourceContextHolder.getDataSourceType().dataSourceKey
    }
}
