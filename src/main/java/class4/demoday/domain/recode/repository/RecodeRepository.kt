package class4.demoday.domain.recode.repository

import class4.demoday.domain.recode.entity.Recode
import class4.demoday.global.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecodeRepository: JpaRepository<Recode, Long> {
    fun findByUserId(userId: Member): List<Recode>
}