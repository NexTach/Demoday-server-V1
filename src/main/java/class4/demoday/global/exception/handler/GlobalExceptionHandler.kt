package class4.demoday.global.exception.handler

import class4.demoday.global.exception.ExpiredRefreshTokenException
import class4.demoday.global.exception.ExpiredTokenException
import class4.demoday.global.exception.IdorPasswordNotMatchException
import class4.demoday.global.exception.IndividualMarkerListEmptyException
import class4.demoday.global.exception.InvalidTokenException
import class4.demoday.global.exception.InvalidTokenFormatException
import class4.demoday.global.exception.JsonFormatException
import class4.demoday.global.exception.MarkersNotFoundInRangeException
import class4.demoday.global.exception.MemberAlreadyExistsException
import class4.demoday.global.exception.MemberSavedFailException
import class4.demoday.global.exception.NoMarkersFoundException
import class4.demoday.global.exception.RecodeListSizeException
import class4.demoday.global.exception.UnauthorizedMarkerAccessException
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

    @ExceptionHandler(IdorPasswordNotMatchException::class)
    fun handleIdandPasswordNotMatchException(ex: IdorPasswordNotMatchException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            message = "Id or Password not match",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(InvalidTokenException::class)
    fun handleInvalidTokenException(ex: InvalidTokenException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            message = "Invalid Token",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(InvalidTokenFormatException::class)
    fun handleInvalidTokenFormatException(ex: InvalidTokenFormatException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Invalid Token Format",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ExpiredTokenException::class)
    fun handleExpiredTokenException(ex: ExpiredTokenException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            message = "Expired Token",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(ExpiredRefreshTokenException::class)
    fun handleExpiredRefreshTokenException(ex: ExpiredRefreshTokenException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            message = "Expired Refresh Token",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(NoMarkersFoundException::class)
    fun handleNoMarkersFoundException(ex: NoMarkersFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = "No markers found",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MarkersNotFoundInRangeException::class)
    fun handleMarkersNotFoundInRangeException(ex: MarkersNotFoundInRangeException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = "No markers found in range",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(RecodeListSizeException::class)
    fun handleRecodeListSizeException(ex: RecodeListSizeException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "Recode list size is not 2",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(IndividualMarkerListEmptyException::class)
    fun handleIndividualMarkerListEmptyException(ex: IndividualMarkerListEmptyException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = "IndividualMarker list is empty",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(JsonFormatException::class)
    fun handleJsonFormatException(ex: JsonFormatException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Json format is invalid",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UnauthorizedMarkerAccessException::class)
    fun handleUnauthorizedMarkerAccessException(ex: UnauthorizedMarkerAccessException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            message = "Unauthorized marker access",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = "No such element",
            statusType = Status.ERROR
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }
}