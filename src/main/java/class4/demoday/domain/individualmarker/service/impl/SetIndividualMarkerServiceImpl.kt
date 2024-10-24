package class4.demoday.domain.individualmarker.service.impl

import class4.demoday.domain.individualmarker.component.SaveIndividualMarker
import class4.demoday.domain.individualmarker.dto.request.SetIndividualMarkerRequest
import class4.demoday.domain.individualmarker.dto.response.GetIndividualMarkerResponse
import class4.demoday.domain.individualmarker.service.SetIndividualMarkerService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SetIndividualMarkerServiceImpl(
    private val saveIndividualMarker: SaveIndividualMarker
) : SetIndividualMarkerService {
    override fun setIndividualMarker(
        request: HttpServletRequest,
        marker: SetIndividualMarkerRequest
    ): GetIndividualMarkerResponse {
        return saveIndividualMarker.saveIndividualMarker(request, marker)
    }
}