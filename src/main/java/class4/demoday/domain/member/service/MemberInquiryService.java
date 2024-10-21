package class4.demoday.domain.member.service;

import class4.demoday.global.member.entity.Member;

import java.util.List;

public interface MemberInquiryService {
    List<Member> getAllMembers();
    Member getMember(Long id);
}
