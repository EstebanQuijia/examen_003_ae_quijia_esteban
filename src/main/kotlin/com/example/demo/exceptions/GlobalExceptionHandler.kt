package com.example.demo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus


data class ErrorDetails(val message: String?, val code: Int)

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails(ex.message, 404), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UnauthorizedActionException::class)
    fun handleUnauthorized(ex: UnauthorizedActionException): ResponseEntity<ErrorDetails> {
        return ResponseEntity(ErrorDetails(ex.message, 403), HttpStatus.FORBIDDEN)
    }
}


@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.FORBIDDEN)
class UnauthorizedActionException(message: String) : RuntimeException(message)