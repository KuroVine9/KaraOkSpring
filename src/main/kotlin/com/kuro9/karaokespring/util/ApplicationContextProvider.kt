package com.kuro9.karaokespring.util

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
object ApplicationContextProvider : ApplicationContextAware {
    private var _context: ApplicationContext? = null
    val context: ApplicationContext get() = _context!!

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        _context = applicationContext
    }

    fun <T> getBean(clazz: Class<T>): T = context.getBean(clazz)
}