package class4.demoday.domain.auth.dto.request

import class4.demoday.global.security.roles.Roles

data class SignUpRequest (
    val phoneNumber: String,
    val password: String,
    val role: Roles
)