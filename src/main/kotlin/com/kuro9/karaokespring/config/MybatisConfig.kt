package com.kuro9.karaokespring.config

import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = ["com.kuro9.karaokespring.repository"], sqlSessionFactoryRef = "sqlSessionFactory")
class MybatisConfig(private val context: ApplicationContext) {

    @Bean
    fun sqlSessionFactory(): SqlSessionFactoryBean =
        SqlSessionFactoryBean().apply {
            setDataSource(context.getBean(DataSource::class.java))
        }

    @Bean(destroyMethod = "clearCache")
    fun sqlSession(): SqlSessionTemplate = SqlSessionTemplate(sqlSessionFactory().`object`)

    @Bean
    fun transactionManager(): PlatformTransactionManager =
        DataSourceTransactionManager(context.getBean(DataSource::class.java))
}