package class4.demoday.domain.auth.service

import class4.demoday.domain.auth.dto.request.SignRequest
import class4.demoday.domain.auth.dto.response.SignUpResponse

interface SignUpService {
    fun signUp(signUpRequest: SignRequest): SignUpResponse
}