package class4.demoday.domain.individualmarker.service

import class4.demoday.domain.individualmarker.dto.request.PatchIndividualMarkerRequest
import class4.demoday.domain.individualmarker.dto.response.GetIndividualMarkerResponse
import jakarta.servlet.http.HttpServletRequest

interface PatchIndividualMarkerService {
    fun patchIndividualMarker(
        marker_id: Long,
        request: HttpServletRequest,
        marker: PatchIndividualMarkerRequest
    ): GetIndividualMarkerResponse
}