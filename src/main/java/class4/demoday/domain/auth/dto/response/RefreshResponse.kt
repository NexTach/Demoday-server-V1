package class4.demoday.domain.auth.dto.response

import class4.demoday.global.security.roles.Roles
import java.time.LocalDateTime

data class RefreshResponse(
    val accessToken: String,
    val accessTokenExpiresIn: LocalDateTime,
    val roles: Roles
)