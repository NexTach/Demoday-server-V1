package class4.demoday.domain.auth.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignRequest(
    val name: String? = null,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank val password: String
)