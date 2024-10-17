package class4.demoday.domain.auth.component

import class4.demoday.global.exception.MemberAlreadyExistsException
import class4.demoday.global.member.repository.MemberRepository
import class4.demoday.global.security.cipher.EncryptionUtils
import org.springframework.stereotype.Component

@Component
class EffectivenessCheck(
    private val memberRepository: MemberRepository,
) {
    fun checkMemberEffective(phoneNumber: String) {
        memberRepository.findByPhoneNumber(EncryptionUtils.encrypt(phoneNumber)).let {
            throw MemberAlreadyExistsException(phoneNumber)
        }
    }
}