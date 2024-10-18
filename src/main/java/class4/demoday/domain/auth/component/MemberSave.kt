package class4.demoday.domain.auth.component

import class4.demoday.domain.auth.dto.request.SignUpRequest
import class4.demoday.domain.auth.dto.response.SignUpResponse
import class4.demoday.global.exception.MemberSavedFailException
import class4.demoday.global.member.entity.Member
import class4.demoday.global.member.repository.MemberRepository
import class4.demoday.global.security.cipher.EncryptionUtils.decrypt
import class4.demoday.global.security.cipher.EncryptionUtils.encrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class MemberSave(
    private val memberRepository: MemberRepository,
    private val bycryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun saveMember(member: SignUpRequest): SignUpResponse {
        val newMember = Member(
            null,
            encrypt(member.phoneNumber),
            bycryptPasswordEncoder.encode(member.password),
            member.role
        )
        val savedMember=try {
            memberRepository.save(newMember)
        } catch (ex: Exception) {
            throw MemberSavedFailException("Failed to save ${Member::class.simpleName}: ${ex.message}")
        }
        return SignUpResponse(
            savedMember.id,
            decrypt(savedMember.phoneNumber),
            savedMember.role
        )
    }
}