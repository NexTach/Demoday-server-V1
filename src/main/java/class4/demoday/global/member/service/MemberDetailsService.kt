package class4.demoday.global.member.service

import class4.demoday.global.member.MemberDetails
import class4.demoday.global.member.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MemberDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val member = memberRepository.findByEmail(id)
        return member?.let {
            MemberDetails(it)
        } ?: throw UsernameNotFoundException("User not found with id: $id")
    }
}