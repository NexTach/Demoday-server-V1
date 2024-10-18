package class4.demoday.domain.auth.service.Impl

import class4.demoday.domain.auth.component.MemberInquiry
import class4.demoday.domain.auth.component.PasswordMatchCheck
import class4.demoday.domain.auth.dto.request.SignInRequest
import class4.demoday.domain.auth.service.SignInService
import class4.demoday.global.security.cipher.EncryptionUtils
import class4.demoday.global.security.jwt.dto.TokenResponse
import class4.demoday.global.security.jwt.service.JwtTokenService
import org.springframework.stereotype.Service

@Service
class SignInServiceImpl(
    private val jwtTokenService: JwtTokenService,
    private val memberInquiry: MemberInquiry,
    private val passwordMatchCheck: PasswordMatchCheck
) : SignInService {
    override fun signIn(signInRequest: SignInRequest): TokenResponse {
        jwtTokenService.invalidateRefreshToken(EncryptionUtils.encrypt(signInRequest.phoneNumber))
        val member = memberInquiry.findMemberByPhoneNumber(signInRequest.phoneNumber)
        passwordMatchCheck.checkPasswordMatch(signInRequest, member)
        return jwtTokenService.generateTokenDto(member.phoneNumber, member.role)
    }
}