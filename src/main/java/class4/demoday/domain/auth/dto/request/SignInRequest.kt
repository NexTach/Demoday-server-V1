package class4.demoday.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class SignInRequest(
    @field:NotBlank val phoneNumber: String,
    @field:NotBlank val password: String
)