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
        val encryptedPhoneNumber = EncryptionUtils.encrypt(phoneNumber)
        val member = memberRepository.findByPhoneNumber(encryptedPhoneNumber)
        if (member != null) {
            throw MemberAlreadyExistsException(phoneNumber)
        }
    }
}