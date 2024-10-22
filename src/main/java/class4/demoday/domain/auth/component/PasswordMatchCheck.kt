package class4.demoday.domain.auth.component

import class4.demoday.domain.auth.dto.request.SignRequest
import class4.demoday.global.exception.IdorPasswordNotMatchException
import class4.demoday.global.member.entity.Member
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordMatchCheck(private val passwordEncoder: BCryptPasswordEncoder) {
    fun checkPasswordMatch(signRequest: SignRequest, member: Member) {
        if (!passwordEncoder.matches(signRequest.password, member.password)) {
            throw IdorPasswordNotMatchException("Id and Password not match")
        }
    }
}