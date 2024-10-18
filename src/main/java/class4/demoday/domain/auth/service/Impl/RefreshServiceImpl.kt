package class4.demoday.domain.auth.service.Impl

import class4.demoday.domain.auth.dto.response.RefreshResponse
import class4.demoday.domain.auth.service.RefreshService
import class4.demoday.global.security.jwt.service.JwtTokenService
import org.springframework.stereotype.Service

@Service
class RefreshServiceImpl(
    private val jwtTokenService: JwtTokenService,
): RefreshService {
    override fun refresh(refreshToken: String): RefreshResponse {
        return jwtTokenService.reissueToken(refreshToken)
    }
}