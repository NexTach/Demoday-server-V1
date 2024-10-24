package class4.demoday.domain.individualmarker.service

import jakarta.servlet.http.HttpServletRequest

interface DeleteIndividualMarkerService {
    fun deleteIndividualMarker(marker_id: Long, request: HttpServletRequest)
}