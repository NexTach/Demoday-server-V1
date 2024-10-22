package class4.demoday.domain.member.resolver


import class4.demoday.domain.member.service.MemberInquiryService
import class4.demoday.global.member.entity.Member
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class MemberQueryResolver(
    private val memberInquiryService: MemberInquiryService
) {

    @QueryMapping
    fun getMemberById(@Argument id: Long): Member? {
        return memberInquiryService.getMember(id)
    }

    @QueryMapping
    fun getAllMembers(): List<Member> {
        return memberInquiryService.allMembers
    }
}