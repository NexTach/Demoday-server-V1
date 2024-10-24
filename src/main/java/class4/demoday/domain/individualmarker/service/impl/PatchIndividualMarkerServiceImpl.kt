package class4.demoday.domain.individualmarker.service.impl

import class4.demoday.domain.individualmarker.component.MarkerOwnerCheck
import class4.demoday.domain.individualmarker.component.PatchIndividualMarker
import class4.demoday.domain.individualmarker.dto.request.PatchIndividualMarkerRequest
import class4.demoday.domain.individualmarker.dto.response.GetIndividualMarkerResponse
import class4.demoday.domain.individualmarker.entity.IndividualMarker
import class4.demoday.domain.individualmarker.service.PatchIndividualMarkerService
import class4.demoday.global.exception.JsonFormatException
import class4.demoday.global.exception.UnauthorizedMarkerAccessException
import class4.demoday.global.security.cipher.EncryptionUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service

@Service
class PatchIndividualMarkerServiceImpl(
    private val patchIndividualMarker: PatchIndividualMarker,
    private val markerOwnerCheck: MarkerOwnerCheck
) :
    PatchIndividualMarkerService {
    override fun patchIndividualMarker(
        marker_id: Long,
        request: HttpServletRequest,
        marker: PatchIndividualMarkerRequest
    ): GetIndividualMarkerResponse {
        if (!markerOwnerCheck.checkOwner(marker_id, request)) {
            throw UnauthorizedMarkerAccessException("Unauthorized marker access")
        }
        var savedMarker: IndividualMarker? = null
        if (marker.name != null) {
            savedMarker = patchIndividualMarker.patchIndividualMarker(marker_id, marker.name)
        }
        if (marker.x != null && marker.y != null) {
            savedMarker = patchIndividualMarker.patchIndividualMarker(marker_id, marker.x, marker.y)
        }
        if (marker.name != null && marker.x != null && marker.y != null) {
            savedMarker = patchIndividualMarker.patchIndividualMarker(marker_id, marker.name, marker.x, marker.y)
        }
        if (marker.name != null && marker.x != null) {
            savedMarker = patchIndividualMarker.patchIndividualMarker(marker_id, marker.name, marker.x)
        }
        if (marker.name != null && marker.y != null) {
            savedMarker = patchIndividualMarker.patchIndividualMarker(marker_id, marker.name, marker.y.toFloat())
        }
        if (marker.x != null) {
            savedMarker = patchIndividualMarker.patchIndividualMarker(marker_id, marker.x)
        }
        if (marker.y != null) {
            savedMarker = patchIndividualMarker.patchIndividualMarker(marker_id, marker.y.toFloat())
        }
        if (marker.name == null && marker.x == null && marker.y == null) {
            throw JsonFormatException("Invalid json format")
        }
        //ㄹㅇ 이거 뭐임 내가 짰지만 최악의 코드다
        return GetIndividualMarkerResponse(
            id = savedMarker!!.id,
            name = savedMarker.name,
            x = savedMarker.x,
            y = savedMarker.y,
            ownerName = savedMarker.owner.name,
            ownerEmail = EncryptionUtils.decrypt(savedMarker.owner.email)
        )
    }
}