package class4.demoday.domain.auth.service

import class4.demoday.domain.auth.dto.request.SignUpRequest
import class4.demoday.domain.auth.dto.response.SignUpResponse

interface SignUpService {
    fun signUp(signUpRequest: SignUpRequest): SignUpResponse
}