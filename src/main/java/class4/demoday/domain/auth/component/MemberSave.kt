package class4.demoday.domain.auth.component

import class4.demoday.domain.auth.dto.request.SignRequest
import class4.demoday.domain.auth.dto.response.SignUpResponse
import class4.demoday.domain.recode.entity.Recode
import class4.demoday.domain.recode.entity.RecodeType
import class4.demoday.domain.recode.repository.RecodeRepository
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
    private val recodeRepository: RecodeRepository,
    private val bycryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun saveMember(member: SignRequest): SignUpResponse {
        val newMember = Member(
            null,
            encrypt(member.email),
            bycryptPasswordEncoder.encode(member.password),
        )
        val savedMember = try {
            memberRepository.save(newMember)
        } catch (ex: Exception) {
            throw MemberSavedFailException("Failed to save ${Member::class.simpleName}: ${ex.message}")
        }
        val timeRecode = Recode(
            id = null,
            recodeType = RecodeType.TIME,
            value = 0,
            userId = newMember
        )
        try {
            recodeRepository.save(timeRecode)
            val distanceRecode = Recode(
                id = null,
                recodeType = RecodeType.DISTANCE,
                value = 0,
                userId = newMember
            )
            recodeRepository.save(distanceRecode)
        } catch (ex: Exception) {
            throw MemberSavedFailException("Failed to save ${Recode::class.simpleName}: ${ex.message}")
        }
        return SignUpResponse(
            savedMember.id,
            decrypt(savedMember.email),
        )
    }
}