package class4.demoday.domain.individualmarker.service.impl

import class4.demoday.domain.individualmarker.component.DeleteIndividualMarker
import class4.demoday.domain.individualmarker.component.MarkerOwnerCheck
import class4.demoday.domain.individualmarker.service.DeleteIndividualMarkerService
import class4.demoday.global.exception.UnauthorizedMarkerAccessException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service

@Service
class DeleteIndividualMarkerServiceImpl(
    private val deleteIndividualMarker: DeleteIndividualMarker,
    private val markerOwnerCheck: MarkerOwnerCheck
) :
    DeleteIndividualMarkerService {
    override fun deleteIndividualMarker(marker_id: Long, request: HttpServletRequest) {
        if (!markerOwnerCheck.checkOwner(marker_id, request)) {
            throw UnauthorizedMarkerAccessException("You are not the owner of this marker")
        }
        deleteIndividualMarker.deleteIndividualMarker(marker_id)
    }
}