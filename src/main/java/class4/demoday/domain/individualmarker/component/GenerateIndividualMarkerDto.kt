package class4.demoday.domain.individualmarker.component

import class4.demoday.domain.individualmarker.dto.response.GetIndividualMarkerResponse
import class4.demoday.domain.individualmarker.repository.IndividualMarkerRepository
import class4.demoday.global.exception.IndividualMarkerListEmptyException
import class4.demoday.global.member.entity.Member
import class4.demoday.global.security.cipher.EncryptionUtils
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class GenerateIndividualMarkerDto(
    private val individualMarkerRepository: IndividualMarkerRepository
) {
    fun generateIndividualMarkerDto(user: Member): List<GetIndividualMarkerResponse> {
        val markerList = individualMarkerRepository.findByOwner(user)
        if(markerList.isEmpty()) {
            throw IndividualMarkerListEmptyException("IndividualMarker list is empty")
        }
        val responseList = mutableListOf<GetIndividualMarkerResponse>()
        for (marker in markerList) {
            val response = GetIndividualMarkerResponse(
                id = marker.id,
                name = marker.name,
                x = marker.x,
                y = marker.y,
                ownerName = marker.owner.name,
                ownerEmail = EncryptionUtils.decrypt(marker.owner.email)
            )
            responseList.add(response)
        }
        return responseList
    }
}