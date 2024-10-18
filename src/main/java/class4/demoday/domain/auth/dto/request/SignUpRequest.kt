package class4.demoday.domain.auth.dto.request

import class4.demoday.global.security.roles.Roles
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class SignUpRequest (
    @field:NotBlank val phoneNumber: String,
    @field:NotBlank val password: String,
    @field:NotNull val role: Roles
)