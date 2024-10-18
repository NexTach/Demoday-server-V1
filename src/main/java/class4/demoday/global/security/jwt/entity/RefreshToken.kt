package class4.demoday.global.security.jwt.entity

import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.time.LocalDateTime

@RedisHash("refresh_token", timeToLive = 604800)
data class RefreshToken(
    @Id val refreshToken: String,
    @Indexed val username: String,
    val expiredAt: LocalDateTime
)