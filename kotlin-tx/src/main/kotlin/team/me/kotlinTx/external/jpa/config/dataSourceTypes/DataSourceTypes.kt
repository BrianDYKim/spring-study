package team.me.kotlinTx.external.jpa.config.dataSourceTypes

enum class DataSourceTypes(val dataSourceKey: String) {
    MAIN_WRITE("MAIN_WRITE"),
    MAIN_READ("MAIN_READ"),
    PUSH_WRITE("PUSH_WRITE"),
    PUSH_READ("PUSH_READ"),
}
