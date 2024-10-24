package class4.demoday.domain.individualmarker.component

import class4.demoday.domain.individualmarker.repository.IndividualMarkerRepository
import class4.demoday.global.member.service.GetMemberinfoService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class MarkerOwnerCheck(
    private val individualMarkerRepository: IndividualMarkerRepository,
    private val memberinfoService: GetMemberinfoService
) {
    fun checkOwner(marker_id: Long, request: HttpServletRequest): Boolean {
        val marker = individualMarkerRepository.findById(marker_id).get()
        val member = memberinfoService.getMemberByToken(request)
        return marker.owner == member
    }
}