package class4.demoday.global.member.service

import class4.demoday.global.member.entity.Member
import class4.demoday.global.member.repository.MemberRepository
import class4.demoday.global.security.jwt.service.JwtAuthenticationService
import class4.demoday.global.security.jwt.service.JwtTokenService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GetMemberinfoService(
    private val jwtTokenService: JwtTokenService,
    private val jwtAuthenticationService: JwtAuthenticationService,
    private val memberRepository: MemberRepository
) {
    fun getMemberByToken(request: HttpServletRequest): Member {
        val token: String = jwtTokenService.resolveToken(request.getHeader("Authorization"))
        val userInfo = jwtAuthenticationService.getAuthentication(token)
        val member = memberRepository.findByEmail(userInfo.name)
        member?.let {
            return it
        }
        throw UsernameNotFoundException("User not found")
    }

    fun getMemberByToken(token: String): Member {
        val userInfo = jwtAuthenticationService.getAuthentication(token)
        val member = memberRepository.findByEmail(userInfo.name)
        member?.let {
            return it
        }
        throw UsernameNotFoundException("User not found")
    }
}