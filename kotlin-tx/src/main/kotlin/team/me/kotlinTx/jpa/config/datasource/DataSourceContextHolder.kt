package team.me.kotlinTx.jpa.config.datasource

import team.me.kotlinTx.jpa.config.dataSourceTypes.DataSourceTypes

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