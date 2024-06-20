package com.kuro9.karaokespring.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)

inline fun <reified T> T.infoLog(message: String, vararg any: Any?) =
    logger().info(message, any)

inline fun <reified T> T.warnLog(message: String, vararg any: Any?) =
    logger().warn(message, any)

inline fun <reified T> T.errorLog(message: String, vararg any: Any?) =
    logger().error(message, any)

inline fun <reified T> T.errorLog(message: String, error: Throwable?) =
    logger().error(message, error)