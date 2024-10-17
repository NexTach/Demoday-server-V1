package class4.demoday.global.exception.dto.response

import class4.demoday.global.exception.dto.enums.Status

data class ErrorResponse(
    val status: Int,
    val message: String,
    val statusType: Status
)