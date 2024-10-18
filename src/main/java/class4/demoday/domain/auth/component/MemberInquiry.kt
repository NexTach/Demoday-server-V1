package class4.demoday.domain.auth.component

import class4.demoday.global.member.repository.MemberRepository
import class4.demoday.global.security.cipher.EncryptionUtils
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class MemberInquiry(
    private val memberRepository: MemberRepository
) {
   fun findMemberByPhoneNumber(phoneNumber: String) = memberRepository.findByPhoneNumber(EncryptionUtils.encrypt(phoneNumber))?: throw UsernameNotFoundException("User not found")
}