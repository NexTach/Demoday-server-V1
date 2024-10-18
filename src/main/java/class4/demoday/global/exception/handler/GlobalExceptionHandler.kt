package class4.demoday.global.exception.handler

import class4.demoday.global.exception.MemberAlreadyExistsException
import class4.demoday.global.exception.MemberSavedFailException
import class4.demoday.global.exception.dto.enums.Status
import class4.demoday.global.exception.dto.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MemberAlreadyExistsException::class)
    fun handleMemberAlreadyExistsException(ex: MemberAlreadyExistsException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.CONFLICT.value(),
            message = "Member already exists",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(MemberSavedFailException::class)
    fun handleMemberSavedFailException(ex: MemberSavedFailException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "Member save failed",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = "User not found",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }
}