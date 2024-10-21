package class4.demoday.domain.member.service.Impl

import class4.demoday.domain.member.service.MemberInquiryService
import class4.demoday.global.member.entity.Member
import class4.demoday.global.member.repository.MemberRepository
import class4.demoday.global.security.cipher.EncryptionUtils
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MemberInquiryServiceImpl(private val memberRepository: MemberRepository) :
    MemberInquiryService {

    override fun getMember(id: Long): Member? {
        val member = memberRepository.findById(id).orElse(null)
        if (member == null) {
            throw UsernameNotFoundException("No member found")
        }
        member.phoneNumber = EncryptionUtils.decrypt(member.phoneNumber)
        return member
    }

    override fun getAllMembers(): List<Member> {
        val members = memberRepository.findAll()
        if (members.isEmpty()) {
            throw UsernameNotFoundException("No members found")
        }
        members.forEach { it.phoneNumber = EncryptionUtils.decrypt(it.phoneNumber) }
        return members
    }
}