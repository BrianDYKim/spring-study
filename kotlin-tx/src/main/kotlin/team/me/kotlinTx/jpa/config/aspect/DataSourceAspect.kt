package team.me.kotlinTx.jpa.config.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import team.me.kotlinTx.jpa.config.annotation.transactional.MainDataSourceTransactional
import team.me.kotlinTx.jpa.config.dataSourceTypes.DataSourceTypes
import team.me.kotlinTx.jpa.config.datasource.DataSourceContextHolder

@Aspect
@Component
@Order(0)
class DataSourceAspect {
    @Before("@annotation(mainDataSourceTransactional)")
    fun setMainDataSource(mainDataSourceTransactional: MainDataSourceTransactional) {
        if (mainDataSourceTransactional.readOnly) {
            DataSourceContextHolder.setDataSourceType(DataSourceTypes.MAIN_READ)
        } else {
            DataSourceContextHolder.setDataSourceType(DataSourceTypes.MAIN_WRITE)
        }
    }
}
