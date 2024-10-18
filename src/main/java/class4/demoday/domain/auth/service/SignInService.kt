package class4.demoday.domain.auth.service

import class4.demoday.domain.auth.dto.request.SignInRequest
import class4.demoday.global.security.jwt.dto.TokenResponse

interface SignInService {
    fun signIn(signInRequest: SignInRequest): TokenResponse
}