package com.kuro9.karaokespring.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
@ConfigurationPropertiesScan(basePackages = ["com.kuro9.karaokespring.config"])
class ConfigScan