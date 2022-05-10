package com.demo.scraper.repository

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.reflect.KFunction
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.isAccessible

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DoclasseItemDataFileRepositoryTest {
    private val sut: DoclasseItemDataFileRepository = DoclasseItemDataFileRepository()
    private lateinit var method: KFunction<*>

    @BeforeEach
    fun setUp() {
        method = sut::class.memberFunctions.first { it.name == "endWith" }
        method.isAccessible = true
    }

    @ParameterizedTest
    @CsvSource(
        "g99999, https://www.doclasse.com/shop/ladies/g/g99999, false",
        "g9999,  https://www.doclasse.com/shop/ladies/g/g99999, false",
        "99999,  https://www.doclasse.com/shop/ladies/g/g99999, true",
        "9999,   https://www.doclasse.com/shop/ladies/g/g99999, false",
        "\"\",   https://www.doclasse.com/shop/ladies/g/g99999, false",
    )
    fun endWith(code: String, line: String, expected: Boolean) {
        // given in args

        // when
        val actual = method.call(sut, code, line)

        // then
        MatcherAssert.assertThat(actual, Matchers.`is`(expected))
    }
}