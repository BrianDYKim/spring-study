package team.me.kotlinTx.external.jpa.config.datasource

import team.me.kotlinTx.external.jpa.config.dataSourceTypes.DataSourceTypes

object DataSourceContextHolder {
    private val contextHolder = ThreadLocal<DataSourceTypes>()

    init {
        setDataSourceType(DataSourceTypes.MAIN_WRITE)
    }

    fun setDataSourceType(dataSourceType: DataSourceTypes) {
        contextHolder.set(dataSourceType)
    }

    fun getDataSourceType(): DataSourceTypes {
        return contextHolder.get()
    }

    fun clearDataSourceType() {
        contextHolder.remove()
    }
}