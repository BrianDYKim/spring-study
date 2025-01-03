package team.me.kotlinTx.external.jpa.config.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import team.me.kotlinTx.external.jpa.config.annotation.transactional.MainDataSourceTransactional
import team.me.kotlinTx.external.jpa.config.dataSourceTypes.DataSourceTypes
import team.me.kotlinTx.external.jpa.config.datasource.DataSourceContextHolder

@Aspect
@Component
@Order(0)
class DataSourceAspect {
    @Before("@annotation(mainDataSourceTransactional)")
    fun setMainDataSource(mainDataSourceTransactional: MainDataSourceTransactional) {
        val dataSourceType = when(mainDataSourceTransactional.readOnly) {
            true -> DataSourceTypes.MAIN_READ
            false -> DataSourceTypes.MAIN_WRITE
        }

        DataSourceContextHolder.setDataSourceType(dataSourceType)
    }
}
