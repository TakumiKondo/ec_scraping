package com.demo.scraper.repository

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.boot.test.context.SpringBootTest
import kotlin.reflect.KFunction
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.isAccessible

@SpringBootTest
// TODO: @TestInstanceを指定する必要がある根拠の調査
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MensFashionPlusItemDataFileRepositoryTest {
    private val sut: MensFashionPlusItemDataFileRepository = MensFashionPlusItemDataFileRepository()
    private lateinit var method: KFunction<*>

    @BeforeAll
    fun setup() {
        method = sut::class.memberFunctions.first { it.name == "endWith" }
        method.isAccessible = true
    }

    @ParameterizedTest
    @CsvSource(
        "S9999, https://mensfashion.cc/c/trjc/cate1/cate2/cate3/S9999, true",
        "S123,  https://mensfashion.cc/c/trjc/cate1/cate2/cate3/S1234, false",
        "\"\",  https://mensfashion.cc/c/trjc/cate1/cate2/cate3/S1234, false",
        "1234,  https://mensfashion.cc/c/trjc/cate1/cate2/cate3/S1234, false",
    )
    fun endWith(code: String, line: String, expected: Boolean) {
        // given is args

        // when
        val actual = method.call(sut, code, line)

        // then
        assertThat(actual, `is`(expected))
    }

}