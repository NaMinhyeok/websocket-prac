package com.example.webquiz

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

object KotestProjectConfig : AbstractProjectConfig() {

    override val parallelism = 3

    override fun extensions() = listOf(SpringExtension)

}
