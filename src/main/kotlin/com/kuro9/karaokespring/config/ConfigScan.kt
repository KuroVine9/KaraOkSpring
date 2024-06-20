package com.kuro9.karaokespring.config

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
@MapperScan(basePackages = ["com.kuro9.karaokespring.repository"], sqlSessionFactoryRef = "sqlSessionFactory")
@ConfigurationPropertiesScan(basePackages = ["com.kuro9.karaokespring.config"])
class ConfigScan