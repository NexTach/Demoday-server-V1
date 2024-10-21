package class4.demoday.domain.member.resolver


import class4.demoday.global.member.entity.Member
import class4.demoday.global.member.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class MemberQueryResolver(private val memberRepository: MemberRepository) {

    fun getMemberById(id: Long): Member? {
        return memberRepository.findById(id).orElse(null)
    }

    fun getAllMembers(): List<Member> {
        val members = memberRepository.findAll()
        return if (members.isNotEmpty()) members else emptyList()
    }
}