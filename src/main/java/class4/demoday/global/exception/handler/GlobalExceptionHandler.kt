package class4.demoday.global.exception.handler

import class4.demoday.global.exception.MemberAlreadyExistsException
import class4.demoday.global.exception.dto.enums.Status
import class4.demoday.global.exception.dto.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MemberAlreadyExistsException::class)
    fun handleMemberAlreadyExistsException(ex: MemberAlreadyExistsException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.CONFLICT.value(),
            message = ex.message ?: "Member already exists",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }
}