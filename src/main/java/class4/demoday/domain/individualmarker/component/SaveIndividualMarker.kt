package class4.demoday.domain.individualmarker.component

import class4.demoday.domain.individualmarker.dto.request.SetIndividualMarkerRequest
import class4.demoday.domain.individualmarker.dto.response.GetIndividualMarkerResponse
import class4.demoday.domain.individualmarker.entity.IndividualMarker
import class4.demoday.domain.individualmarker.repository.IndividualMarkerRepository
import class4.demoday.global.member.service.GetMemberinfoService
import class4.demoday.global.security.cipher.EncryptionUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SaveIndividualMarker(
    private val individualMarkerRepository: IndividualMarkerRepository,
    private val getMemberinfoService: GetMemberinfoService
) {
    fun saveIndividualMarker(
        request: HttpServletRequest,
        marker: SetIndividualMarkerRequest
    ): GetIndividualMarkerResponse {
        val name = marker.name
        val x = marker.x
        val y = marker.y
        val owner = getMemberinfoService.getMemberByToken(request)
        val individualMarker = IndividualMarker(
            id = null,
            name = name,
            x = x,
            y = y,
            owner = owner
        )
        individualMarkerRepository.save(individualMarker)
        return GetIndividualMarkerResponse(
            id = individualMarker.id,
            name = individualMarker.name,
            x = individualMarker.x,
            y = individualMarker.y,
            ownerName = individualMarker.owner.name,
            ownerEmail = EncryptionUtils.decrypt(individualMarker.owner.email)
        )
    }
}