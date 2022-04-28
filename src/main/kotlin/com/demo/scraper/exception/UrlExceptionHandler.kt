package com.demo.scraper.exception

import org.jsoup.HttpStatusException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class UrlExceptionHandler {
    /**
     * 取得対象のURLをシステムが保持していない
     */
    @ExceptionHandler
    fun handleUrlNotFoundException(e: UrlNotFoundException) : ResponseEntity<UrlErrorResponse> {
        return handle(e)
    }

    /**
     * 取得先のURLがサイト上に存在しない
     */
    @ExceptionHandler
    fun handleHttpStatusException(e: HttpStatusException) : ResponseEntity<UrlErrorResponse> {
        return handle(e)
    }

    private fun handle(e: Throwable) : ResponseEntity<UrlErrorResponse> {
        val urlErrorResponse = UrlErrorResponse(HttpStatus.NOT_FOUND.value(),
                                                e.message,
                                                LocalDateTime.now())
        return ResponseEntity(urlErrorResponse, HttpStatus.NOT_FOUND)
    }
}