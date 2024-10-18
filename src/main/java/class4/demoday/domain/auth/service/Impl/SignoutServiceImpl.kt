package class4.demoday.domain.auth.service.Impl

import class4.demoday.domain.auth.service.SignoutService
import class4.demoday.global.security.jwt.service.JwtTokenService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SignoutServiceImpl(private val jwtTokenService: JwtTokenService) : SignoutService {
    override fun signout(request: HttpServletRequest) {
        val accessToken = jwtTokenService.resolveToken(request)
        jwtTokenService.removeToken(accessToken, SecurityContextHolder.getContext().getAuthentication().getName())
    }
}
