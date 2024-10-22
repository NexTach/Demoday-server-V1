package class4.demoday.domain.auth.service

import class4.demoday.domain.auth.dto.request.SignRequest
import class4.demoday.global.security.jwt.dto.TokenResponse

interface SignInService {
    fun signIn(signRequest: SignRequest): TokenResponse
}