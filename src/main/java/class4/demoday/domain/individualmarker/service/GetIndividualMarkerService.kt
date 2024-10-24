package class4.demoday.domain.individualmarker.service

import class4.demoday.domain.individualmarker.dto.response.GetIndividualMarkerResponse
import jakarta.servlet.http.HttpServletRequest

interface GetIndividualMarkerService {
    fun getIndividualMarker(request: HttpServletRequest): List<GetIndividualMarkerResponse>
}