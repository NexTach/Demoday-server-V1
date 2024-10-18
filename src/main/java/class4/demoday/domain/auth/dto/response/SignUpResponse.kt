package class4.demoday.domain.auth.dto.response

import class4.demoday.global.security.roles.Roles

data class SignUpResponse(
    val id: Long?,
    val phoneNumber: String,
    val role: Roles
)