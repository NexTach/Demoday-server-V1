package class4.demoday.global.member.repository

import class4.demoday.global.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository:JpaRepository<Member,Long> {
    fun findByEmail(phoneNumber: String): Member?
    fun existsByEmail(phoneNumber: String): Boolean
}