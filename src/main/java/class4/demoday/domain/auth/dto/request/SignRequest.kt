package class4.demoday.domain.auth.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignRequest(
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank val password: String
)