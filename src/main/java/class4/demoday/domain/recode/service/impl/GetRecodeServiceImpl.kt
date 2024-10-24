package class4.demoday.domain.recode.service.impl

import class4.demoday.domain.recode.component.RecodeInquiry
import class4.demoday.domain.recode.entity.Recode
import class4.demoday.domain.recode.service.GetRecodeService
import class4.demoday.global.member.entity.Member
import class4.demoday.global.member.service.GetMemberinfoService
import class4.demoday.global.security.jwt.service.JwtAuthenticationService
import class4.demoday.global.security.jwt.service.JwtTokenService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service

@Service
class GetRecodeServiceImpl(
    private val getMemberinfoService: GetMemberinfoService,
    private val recodeInquiry: RecodeInquiry
) : GetRecodeService {
    override fun getRecode(request: HttpServletRequest): List<Recode> {
        val member: Member = getMemberinfoService.getMemberByToken(request)
        return recodeInquiry.getRecodeByUser(member)
    }
}