package class4.demoday.domain.auth.service.Impl

import class4.demoday.domain.auth.component.MemberInquiry
import class4.demoday.domain.auth.component.PasswordMatchCheck
import class4.demoday.domain.auth.dto.request.SignRequest
import class4.demoday.domain.auth.service.SignInService
import class4.demoday.global.security.cipher.EncryptionUtils
import class4.demoday.global.security.jwt.dto.TokenResponse
import class4.demoday.global.security.jwt.service.JwtTokenService
import org.springframework.stereotype.Service

@Service
class SignInServiceImpl(
    private val jwtTokenService: JwtTokenService,
    private val memberInquiry: MemberInquiry,
    private val passwordMatchCheck: PasswordMatchCheck,
) : SignInService {

    override fun signIn(signRequest: SignRequest): TokenResponse {
        jwtTokenService.invalidateRefreshToken(EncryptionUtils.encrypt(signRequest.email))
        val member = memberInquiry.findMemberByEmail(signRequest.email)
        passwordMatchCheck.checkPasswordMatch(signRequest, member)
        return jwtTokenService.generateTokenDto(member.email)
    }
}