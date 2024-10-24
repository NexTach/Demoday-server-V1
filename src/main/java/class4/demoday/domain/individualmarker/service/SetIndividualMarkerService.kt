package class4.demoday.domain.individualmarker.service

import class4.demoday.domain.individualmarker.dto.request.SetIndividualMarkerRequest
import class4.demoday.domain.individualmarker.dto.response.GetIndividualMarkerResponse
import jakarta.servlet.http.HttpServletRequest

interface SetIndividualMarkerService {
    fun setIndividualMarker(
        request: HttpServletRequest,
        marker: SetIndividualMarkerRequest
    ): GetIndividualMarkerResponse
}