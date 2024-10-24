package class4.demoday.domain.individualmarker.repository

import class4.demoday.domain.individualmarker.entity.IndividualMarker
import class4.demoday.global.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IndividualMarkerRepository: JpaRepository<IndividualMarker, Long> {
    fun findByOwner(user: Member): List<IndividualMarker>
}