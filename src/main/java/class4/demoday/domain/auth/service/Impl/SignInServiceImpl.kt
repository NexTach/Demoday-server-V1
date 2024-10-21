package class4.demoday.domain.auth.service.Impl

import class4.demoday.domain.auth.component.MemberInquiry
import class4.demoday.domain.auth.component.PasswordMatchCheck
import class4.demoday.domain.auth.dto.request.SignInRequest
import class4.demoday.domain.auth.service.SignInService
import class4.demoday.global.component.PhoneNumberFormatter
import class4.demoday.global.security.cipher.EncryptionUtils
import class4.demoday.global.security.jwt.dto.TokenResponse
import class4.demoday.global.security.jwt.service.JwtTokenService
import org.springframework.stereotype.Service

@Service
class SignInServiceImpl(
    private val jwtTokenService: JwtTokenService,
    private val memberInquiry: MemberInquiry,
    private val passwordMatchCheck: PasswordMatchCheck,
    private val phoneNumberFormatter: PhoneNumberFormatter
) : SignInService {

    override fun signIn(signInRequest: SignInRequest): TokenResponse {
        var formattedPhoneNumber = signInRequest.phoneNumber
        if (!phoneNumberFormatter.formatCheck(formattedPhoneNumber)) {
            formattedPhoneNumber = phoneNumberFormatter.e164Format(formattedPhoneNumber)
        }
        jwtTokenService.invalidateRefreshToken(EncryptionUtils.encrypt(formattedPhoneNumber))
        val member = memberInquiry.findMemberByPhoneNumber(formattedPhoneNumber)
        passwordMatchCheck.checkPasswordMatch(signInRequest, member)
        return jwtTokenService.generateTokenDto(member.phoneNumber, member.role)
    }
}