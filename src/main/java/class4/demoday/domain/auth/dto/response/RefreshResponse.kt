package class4.demoday.domain.auth.dto.response

import java.time.LocalDateTime

data class RefreshResponse(
    val accessToken: String,
    val accessTokenExpiresIn: LocalDateTime,
)