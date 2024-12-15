package team.me.kotlinTx.external.jpa.config.datasource

object HibernateProperties {
    fun getPropertiesMap(dialect: String): Map<String, String> {
        val properties = mutableMapOf<String, String>()

        properties["hibernate.format_sql"] = "true"
        properties["hibernate.highlight_sql"] = "true"
        properties["hibernate.hbm2ddl.auto"] = "none"
        properties["hibernate.ddl-auto"] = "none"
        properties["hibernate.dialect"] = dialect

        return properties
    }
}
