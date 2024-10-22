package class4.demoday.global.security.jwt.dto

import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiresIn: LocalDateTime,
    val refreshTokenExpiresIn: LocalDateTime,
)