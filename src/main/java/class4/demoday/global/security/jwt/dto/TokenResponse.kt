package class4.demoday.global.security.jwt.dto

import class4.demoday.global.security.roles.Roles
import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiresIn: LocalDateTime,
    val refreshTokenExpiresIn: LocalDateTime,
    val roles: Roles
)